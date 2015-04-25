package com.srcdevbin.buildbot.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class PoolManager {

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
			results = pool.invokeAll(tasks);
		} catch (InterruptedException e) {
			results = null;
		}
		return results;
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
