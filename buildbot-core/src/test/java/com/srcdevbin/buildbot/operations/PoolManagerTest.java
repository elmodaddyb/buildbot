package com.srcdevbin.buildbot.operations;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PoolManagerTest {
	
	private PoolManager manager;
	
	@Before
	public void buildUp(){
		manager = new PoolManager();
	}
	
	@After
	public void tearDown(){
		manager.stopAll();
	}
	
	@Test
	public void shutdownAll(){
		// Mock
		
		// Test
		List<PoolStatus> status = manager.stopAll();
		
		// Verify
		assertThat(status.size(), is(1));
	}

}
