package com.srcdevbin.buildbot.operations;


public class MonitorThread implements Runnable{
	
	private PausableThreadPoolExecutor executor;
	private boolean run = true;
	private int waitSeconds = 5;
	private PoolStatus status;

	public MonitorThread(PausableThreadPoolExecutor executor){
		this.executor = executor;
		status = new PoolStatus();
	}
	
	public void run() {
		while(run){
			status = retrieveStatus(executor);
			try {
                Thread.sleep(waitSeconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
	public void shutdown(){
		run = false;
	}
	
	public PoolStatus getStatus(){
		return status;
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
