package com.srcdevbin.buildbot.activity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.PoolManager;

public class InteractionManager {

	private PoolManager poolManager;
	
	private Logger logger = LoggerFactory.getLogger(InteractionManager.class);
	
	public InteractionManager(){
		poolManager = new PoolManager();
	}
	
	public InteractionResult run(Interaction iAction) throws InteractionException{
		InteractionResult result;
		
		List<Future<OperationResult>> results =  poolManager.submit(iAction);
		Future<OperationResult> pendingTask = results.get(0);

		
		try {
			result = (InteractionResult) pendingTask.get();
		} catch (InterruptedException e) {
			logger.error("InterruptedException : {}", e);
			throw new InteractionException(e);
		} catch (ExecutionException e) {
			logger.error("ExecutionException : {}", e);
			throw new InteractionException(e);
		}
		
		
		return result;
	}

}
