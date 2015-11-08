package buildbot.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BuildBotServer {
	
	private ServerSocket listener;
	private int port;
	
	public BuildBotServer(int port){
		this.port = port;
	}
	
	public void start() throws BuildBotCommException {
		try {
			listener = new ServerSocket(port);
			while (true) {
				Socket socket = listener.accept();
				try {
					BuildBotProtocol protocol = new BuildBotProtocol();
					BuildBotReply reply = protocol.process(socket);
					protocol.respond(socket, reply);
				} finally {
					socket.close();
				}
			}
		} catch (IOException e) {
			throw new BuildBotCommException(e);
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				throw new BuildBotCommException(e);
			}
		}

	}

}
