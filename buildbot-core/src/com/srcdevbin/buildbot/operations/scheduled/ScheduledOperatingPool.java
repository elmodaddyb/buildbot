package com.srcdevbin.buildbot.operations.scheduled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.PoolStatus;

public enum ScheduledOperatingPool {

	CORE(10);

	private ScheduledThreadPoolExecutor executor;
	private ScheduleMonitorThread monitorThread;
	private int nThreads = 0;
	private static final int DEFAULT_MAX_POOL_SIZE = 10;
	private static final int CORE_POOL_SIZE = 2;

	private ScheduledOperatingPool(int poolSize){
		if(poolSize < 0){
			nThreads = DEFAULT_MAX_POOL_SIZE;
		} else {
			nThreads = poolSize;
		}
	}

	public int getMaxPoolSize(){
		return executor.getMaximumPoolSize();
	}

	public synchronized PoolStatus getStatus(){
		synchronized(monitorThread){
			monitorThread.notify();
		}
		PoolStatus status = monitorThread.getStatus();
		return status;
	}

	public void restart(){
		stop();
		start(nThreads);
	}

	public synchronized PoolStatus stop(){
		List<Runnable> pendingTasks = shutdownExecutor();
		PoolStatus status = shutdownMonitor();
		status.setPendingTasks(pendingTasks);
		return status;
	}

	public synchronized PoolStatus start(){
		return start(DEFAULT_MAX_POOL_SIZE);
	}

	public PoolStatus start(int poolSize){
		executor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
		executor.prestartAllCoreThreads();
		executor.setMaximumPoolSize(poolSize);

		// This object is shared between 2 threads
		// Synchronization is critical when accessing this object
		monitorThread = createAndStartMonitor(executor);

		return getStatus();
	}

	public synchronized void schedule(Runnable command, long timeout, TimeUnit unit) throws RejectedExecutionException{
		executor.schedule(command, timeout, unit);
	}

	public synchronized List<Future<OperationResult>> invokeAll(Collection<Callable<OperationResult>> tasks, long timeout, TimeUnit unit) throws InterruptedException{
		return executor.invokeAll(tasks, timeout, unit);
	}

	public synchronized OperationResult invokeAny(Collection<Callable<OperationResult>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException{
		return executor.invokeAny(tasks, timeout, unit);
	}


	private List<Runnable> shutdownExecutor(){
		List<Runnable> pendingTasks = new ArrayList<Runnable>();
		if(executor != null){
			if(!executor.isShutdown()){
				pendingTasks.addAll(executor.shutdownNow());
			};
		}
		return pendingTasks;
	}

	private PoolStatus shutdownMonitor(){
		PoolStatus status;
		synchronized(monitorThread){
			monitorThread.notify();
			monitorThread.shutdown();
			status = monitorThread.getStatus();
		}
		return status;
	}

	private ScheduleMonitorThread createAndStartMonitor(ScheduledThreadPoolExecutor executor){
		ScheduleMonitorThread monitor = new ScheduleMonitorThread(executor);
		Thread monThread = new Thread(monitor);
		monThread.setName(String.format("BuildBot-ScheduledOperatingPool-Monitor-%s", this));
		monThread.start();
		return monitor;
	}

}
