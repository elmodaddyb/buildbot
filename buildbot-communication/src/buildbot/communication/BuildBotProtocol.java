package buildbot.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import buildbot.communication.message.BuildBotReply;
import buildbot.communication.message.BuildBotRequest;
import buildbot.communication.message.ExceptionRequest;

public class BuildBotProtocol {

	public void process(Socket socket) {
		BuildBotRequest request;
		BuildBotReply reply;
		try (
				// socket <-- InputStream <-- host:port
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				// socket --> OutputStream --> host:port
	            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				
	        ) {
	            request = (BuildBotRequest)in.readObject();
	            
	            reply  = CommunicationManager.process(request);
	            
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
