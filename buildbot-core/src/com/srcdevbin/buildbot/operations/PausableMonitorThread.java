package com.srcdevbin.buildbot.operations;

public class PausableMonitorThread implements Runnable{
	
	private PausableThreadPoolExecutor executor;
	private boolean run = true;

	public PausableMonitorThread(PausableThreadPoolExecutor executor){
		this.executor = executor;
	}
	
	public void run() {
		while(run){
			synchronized(this){
				try {
					this.wait();
				} catch (InterruptedException e) {
					run = false;
				}
			}
		}
	}
	
	public void shutdown(){
		run = false;
	}
	
	public PoolStatus getStatus(){
		return retrieveStatus(executor);
	}
	
	private PoolStatus retrieveStatus(PausableThreadPoolExecutor executor){
		PoolStatus status = new PoolStatus();
		status.setPoolSize(executor.getPoolSize());
		status.setCorePoolSize(executor.getCorePoolSize());
		status.setMaxPoolSize(executor.getMaximumPoolSize());
		status.setActiveCount(executor.getActiveCount());
		status.setCompletedTaskCount(executor.getCompletedTaskCount());
		status.setTaskCount(executor.getTaskCount());
		status.setShutdown(executor.isShutdown());
		status.setTerminated(executor.isTerminated());
		status.setPaused(executor.isPaused());
		return status;
	}

}
