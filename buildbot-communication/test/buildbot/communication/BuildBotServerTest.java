package buildbot.communication;

import org.junit.Before;

public class BuildBotServerTest {
	
	private BuildBotServer server;
	private int portNumber;
	
	@Before
	public void buildUp(){
		portNumber = 9191;
		server = new BuildBotServer(portNumber);
	}
	
	

}
