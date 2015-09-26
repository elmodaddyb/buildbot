package com.srcdevbin.buildbot.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.srcdevbin.buildbot.operations.Operation;
import com.srcdevbin.buildbot.operations.OperationData;
import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.PoolManager;

public class InteractionManager {

	private PoolManager poolManager;
	
	private Logger logger = LoggerFactory.getLogger(InteractionManager.class);
	
	public InteractionManager(){
		poolManager = new PoolManager();
	}
	
	public InteractionResult run(Operation iAction) throws InteractionException{
		InteractionResult result;
		
		List<Future<OperationResult>> results =  poolManager.submit(iAction);
		Future<OperationResult> pendingTask = results.get(0);

		
		try {
			result = (InteractionResult) pendingTask.get();  // NIO wait....
		} catch (InterruptedException e) {
			logger.error("InterruptedException : {}", e);
			throw new InteractionException(e);
		} catch (ExecutionException e) {
			logger.error("ExecutionException : {}", e);
			throw new InteractionException(e);
		}
		
		
		return result;
	}
	
	public List<InteractionResult> runUntilDone(Operation iAction) throws InteractionException{
		OperationData nextTask;
		List<InteractionResult> results = new ArrayList<InteractionResult>();
		InteractionResult firstResult;
		InteractionResult result;
		
		firstResult = run(iAction);
		results.add(firstResult);
		nextTask = firstResult.getOperationData();
		
		if(nextTask != null){
			// Iterate until all tasks are complete.
			while(nextTask != null){
				result = run(nextTask.getOperation());
				results.add(result);
				nextTask = result.getOperationData();
			}
			
		}
		return results;
	}

}
