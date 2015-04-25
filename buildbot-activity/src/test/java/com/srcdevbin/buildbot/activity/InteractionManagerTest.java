package com.srcdevbin.buildbot.activity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.srcdevbin.buildbot.operations.OperatingPool;
import com.srcdevbin.buildbot.operations.OperationStatus;

public class InteractionManagerTest {
	
	private InteractionManager manager;
	
	@Before
	public void buildUp(){
		manager = new InteractionManager();
		OperatingPool.CORE.start();
	}
	
	@After
	public void tearDown(){
		OperatingPool.CORE.stop();
	}
	
	@Test
	public void run() throws InteractionException{
		Interaction iAction = new HelloWorldInteraction();
		
		InteractionResult result = manager.run(iAction);
		
		assertThat(result, notNullValue());
		assertThat(result.getStatus(), is(OperationStatus.COMPLETE));
	}
	
	@Test
	public void runAndWait() throws InteractionException{
		long expDelay = 1000;
		SlowInteraction iAction = new SlowInteraction();
		iAction.setDelay(expDelay);
		
		long start = System.currentTimeMillis();
		InteractionResult result = manager.run(iAction);
		long elapsed = System.currentTimeMillis() - start;
		
		assertThat(result, notNullValue());
		assertThat(result.getStatus(), is(OperationStatus.COMPLETE));
		assertThat(elapsed >= expDelay, is(true));
	}

}
