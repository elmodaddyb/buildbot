package com.srcdevbin.buildbot.operations.scheduled;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import com.srcdevbin.buildbot.operations.PoolStatus;

public class ScheduleMonitorThread implements Runnable{
	
	private ScheduledThreadPoolExecutor executor;
	private boolean run = true;

	public ScheduleMonitorThread(ScheduledThreadPoolExecutor executor){
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
	
	private PoolStatus retrieveStatus(ScheduledThreadPoolExecutor executor){
		PoolStatus status = new PoolStatus();
		status.setPoolSize(executor.getPoolSize());
		status.setCorePoolSize(executor.getCorePoolSize());
		status.setMaxPoolSize(executor.getMaximumPoolSize());
		status.setActiveCount(executor.getActiveCount());
		status.setCompletedTaskCount(executor.getCompletedTaskCount());
		status.setTaskCount(executor.getTaskCount());
		status.setShutdown(executor.isShutdown());
		status.setTerminated(executor.isTerminated());
		return status;
	}

}
