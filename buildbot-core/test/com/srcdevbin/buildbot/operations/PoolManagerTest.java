package com.srcdevbin.buildbot.operations;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PoolManagerTest {
	
	private PoolManager manager;
	
	@Before
	public void buildUp(){
		manager = new PoolManager();
		OperatingPool.CORE.start();
	}
	
	@After
	public void tearDown(){
		manager.stopAll();
		OperatingPool.CORE.stop();
	}
	
	@Test
	public void shutdownAll(){
		// Mock
		
		// Test
		List<PoolStatus> status = manager.stopAll();
		
		// Verify
		assertThat(status.size(), is(1));
	}

	
	@Test
	public void submit() throws InterruptedException, ExecutionException{
		NoOperation noOp = new NoOperation();
		
		List<Future<OperationResult>> results = manager.submit(noOp);
		
		assertThat(results.size(), is(1));
		OperationResult result = (OperationResult)results.get(0).get();
		assertThat(result, notNullValue());
		assertThat(result, instanceOf(NoOperationResult.class));
	}
	
	@Test
	public void submitAll() throws InterruptedException, ExecutionException{
		int countOps = 5;
		int i;
		NoOperation noOp;
		
		// Create some operations which do nothing
		List<Operation> listOps = new ArrayList<Operation>();
		for(i = 0; i < countOps; i++){
			noOp = new NoOperation();
			listOps.add(noOp);
		}

		
		// submit operations to be run
		List<Future<OperationResult>> results = manager.submitAll(listOps, OperationType.NOOP);
		
		// verify 5 results
		assertThat(results.size(), is(5));
		
		for(i = 0; i < results.size(); i++){
			OperationResult result = (OperationResult)results.get(i).get();
			assertThat(result, notNullValue());
			assertThat(result, instanceOf(NoOperationResult.class));
		}
		
	}
}
