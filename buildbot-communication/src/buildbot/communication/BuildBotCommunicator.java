package buildbot.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import buildbot.communication.message.BuildBotReply;
import buildbot.communication.message.BuildBotRequest;
import buildbot.communication.message.ExceptionReply;

public class BuildBotCommunicator {

	private int port;
	private String host;

	public BuildBotReply communicate(BuildBotRequest request) throws Exception{
		BuildBotReply reply;
		try (
				// socket
				Socket socket = new Socket(host, port);
				
				// socket --> OutputStream --> host:port
	            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				
				// socket <-- InputStream <-- host:port
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
	        ) {
	            out.writeObject(request);
	            reply = (BuildBotReply)in.readObject();
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host " + host);
	            reply = new ExceptionReply(e);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for the connection to host");
	            reply = new ExceptionReply(e);
	        }
		return reply;
	}

}
