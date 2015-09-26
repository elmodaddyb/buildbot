package com.srcdevbin.buildbot.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoolManager {
	
	private Logger logger = LoggerFactory.getLogger(PoolManager.class);

	public List<PoolStatus> stopAll() {
		List<PoolStatus> poolStatus = new ArrayList<PoolStatus>();

		for (OperatingPool pool : OperatingPool.values()) {
			poolStatus.add(pool.stop());
		}
		return poolStatus;
	}

	public List<Future<OperationResult>> submit(Operation iAction) {
		List<Callable<OperationResult>> tasks = new ArrayList<Callable<OperationResult>>();
		tasks.add(iAction);
		List<Future<OperationResult>> results;
		try {
			OperatingPool pool = getPool(iAction.getType());
			logger.trace("Invoke All tasks: {} with {} pool", tasks.size(), pool);
			results = pool.invokeAll(tasks);
		} catch (InterruptedException e) {
			logger.error("Interruped Exception occurred", e);
			results = null;
		}
		return results;
	}

	public List<Future<OperationResult>> submitAll(List<Operation> actions, OperationType type) {
		List<Future<OperationResult>> results;
		List<Callable<OperationResult>> tasks = new ArrayList<Callable<OperationResult>>();
		tasks.addAll(actions);
		try {
			logger.trace("Invoke {} operations of type {}", actions.size(), type);
			OperatingPool pool = getPool(type);
			results = pool.invokeAll(tasks);
		} catch (InterruptedException e) {
			results = null;
		}
		logger.trace("Return results {}", results.size());
		return results;
	}
	
	public void submit(Execution activity) {
		OperatingPool pool = getPool(activity.getType());
		try {
			logger.trace("Execution Activity: {} with {} pool", activity, pool);
			pool.execute(activity);
		} catch (RejectedExecutionException e) {
			logger.error("Activity {} has been rejected from pool {}",activity, pool, e);
		}
	}

	private OperatingPool getPool(OperationType type) {
		OperatingPool pool;
		switch (type) {
		case CORE:
			pool = OperatingPool.CORE;
			break;
		default:
			pool = OperatingPool.CORE;
		}
		return pool;
	}

}
