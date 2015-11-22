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
			System.out.println("Listener started on port: " + this.port);
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
			System.out.println("Listener stopped on port: " + this.port);
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
	
	public static void main(String args[]){
		try {
			new BuildBotListener(55055).start();
		} catch (CommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
