package buildbot.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import buildbot.communication.message.BuildBotReply;
import buildbot.communication.message.BuildBotRequest;
import buildbot.communication.message.ExceptionRequest;

public class BuildBotProtocol {
	
	private CommunicationManager communicationManager;

	public BuildBotProtocol(){
		communicationManager = new CommunicationManager();
	}
	
	public void process(Socket socket) {
		BuildBotRequest request;
		BuildBotReply reply;
		try (
				// socket <-- InputStream <-- host:port
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				// socket --> OutputStream --> host:port
	            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				
	        ) {
				// Get the request
	            request = (BuildBotRequest)in.readObject();
	            
	            // Process the request
	            reply  = communicationManager.process(request);
	            
	            // respond with the reply
	            out.writeObject(reply);
	            
	        } catch (IOException e) {
	            request = new ExceptionRequest(e);
	        } catch (ClassNotFoundException e) {
	        	request = new ExceptionRequest(e);
				// TODO Auto-generated catch block
				e.printStackTrace();
	        }
	}
		
}
