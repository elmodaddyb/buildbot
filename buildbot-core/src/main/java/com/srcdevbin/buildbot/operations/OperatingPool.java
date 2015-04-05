package com.srcdevbin.buildbot.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public enum OperatingPool {
	CORE(10);
	
	private PausableThreadPoolExecutor executor;
	private MonitorThread monitorThread;
	private int nThreads = 0;
	private static final int DEFAULT_MAX_POOL_SIZE = 10;
	private static final int CORE_POOL_SIZE = 2;
	private static final int QUEUE_SIZE = 10;
	private static final int KEEP_ALIVE_TIME = 10;
	
	private OperatingPool(int poolSize){
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
		executor = new PausableThreadPoolExecutor(CORE_POOL_SIZE, poolSize, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_SIZE));
		executor.prestartAllCoreThreads();
		
		// This object is shared between 2 threads
		// Synchronization is critical when accessing this object
		monitorThread = createAndStartMonitor(executor);
		
		return getStatus();
	}
	
	public void pause(){
		executor.pause();
	}
	
	public void resume(){
		executor.resume();
	}
	
	public synchronized void execute(Runnable work){
		executor.execute(work);
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
	
	private MonitorThread createAndStartMonitor(PausableThreadPoolExecutor executor){
		MonitorThread monitor = new MonitorThread(executor);
		Thread monThread = new Thread(monitor);
		monThread.setName(String.format("BuildBot-OperatingPool-Monitor-%s", this));
		monThread.start();
		return monitor;
	}

}
