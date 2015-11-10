package buildbot.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class BuildBotListener {
	
	private ServerSocket listener;
	private int port;
	
	public BuildBotListener(int port){
		this.port = port;
	}
	
	public void start() throws CommunicationException {
		try {
			listener = new ServerSocket(port);
			while (true) {
				Socket socket = listener.accept();
				try {
					BuildBotProtocol protocol = new BuildBotProtocol();
					protocol.process(socket);
				} finally {
					socket.close();
				}
			}
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				throw new CommunicationException(e);
			}
		}

	}
	
	public ListenerStatus stop(){
		IOUtils.closeQuietly(listener);
		return ListenerStatus.STOPPED;
	}

}
