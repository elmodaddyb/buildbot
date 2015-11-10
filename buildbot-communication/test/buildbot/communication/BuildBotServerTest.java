package buildbot.communication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuildBotServerTest {
	
	private BuildBotListener server;
	private int portNumber;
	
	@Before
	public void buildUp() throws CommunicationException{
		portNumber = 9191;
		server = new BuildBotListener(portNumber);
		server.start();
	}
	
	@After
	public void tearDown(){
		server.stop();
	}
	
	@Test
	public void noop(){
		
	}
	
	

}
