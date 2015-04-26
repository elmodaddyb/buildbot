package com.srcdevbin.buildbot.interaction;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.srcdevbin.buildbot.interaction.HelloWorldInteraction;
import com.srcdevbin.buildbot.interaction.Interaction;
import com.srcdevbin.buildbot.interaction.InteractionException;
import com.srcdevbin.buildbot.interaction.InteractionManager;
import com.srcdevbin.buildbot.interaction.InteractionResult;
import com.srcdevbin.buildbot.operations.OperatingPool;
import com.srcdevbin.buildbot.operations.Operation;
import com.srcdevbin.buildbot.operations.OperationData;
import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.OperationStatus;
import com.srcdevbin.buildbot.operations.OperationType;

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
	
	@Test
	public void runUntilDone() throws Exception{
		// Mock
		Interaction iAction = Mockito.mock(Interaction.class);
		OperationResult result = Mockito.mock(InteractionResult.class);
		OperationData operationData = Mockito.mock(OperationData.class);
		Operation operation = Mockito.mock(Operation.class);
		OperationResult nextResult = Mockito.mock(InteractionResult.class);
		
		// Mock Interaction
		when(iAction.call()).thenReturn(result);
		when(iAction.getType()).thenReturn(OperationType.NOOP);
		
		// Mock Result
		when(result.getOperationData()).thenReturn(operationData);
		
		// Mock OperationData
		when(operationData.getOperation()).thenReturn(operation);
		when(operation.call()).thenReturn(nextResult);
		when(operation.getType()).thenReturn(OperationType.NOOP);
		when(nextResult.getOperationData()).thenReturn(null);
		
		// Test
		List<InteractionResult> results = manager.runUntilDone(iAction);
		
		
		// Verify
		assertThat(results.size(), is(2));		
	}

}
