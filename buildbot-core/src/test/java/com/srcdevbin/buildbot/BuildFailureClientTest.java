package com.srcdevbin.buildbot;

import org.junit.Before;
import org.junit.Test;

import com.srcdevbin.buildbot.failure.protocol.BuildFailureClient;

/**
 * The class to test the client for the build failure client
 * @author eamonn
 *
 */
public class BuildFailureClientTest {
	
	private BuildFailureClient buildFailureClient;

	@Before
	public void buildUp(){
		buildFailureClient = new BuildFailureClient();
	}
	
	@Test
	public void createMessage(){
		
		// Test
		
		// Verify
		
	}
	
	@Test
	public void openSocket(){
		// Open Socket
	}
	
	@Test
	public void sendMessage(){
		
	}

}
