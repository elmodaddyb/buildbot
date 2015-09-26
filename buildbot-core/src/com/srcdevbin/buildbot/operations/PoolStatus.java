package com.srcdevbin.buildbot.operations;

import java.util.List;

public class PoolStatus {

	private int poolSize;
	private int corePoolSize;
	private int activeCount;
	private long completedTaskCount;
	private long taskCount;
	private boolean shutdown;
	private boolean terminated;
	private int maxPoolSize;
	private boolean isPaused;
	private List<Runnable> pendingTasks;
	
	
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public void setCompletedTaskCount(long completedTaskCount) {
		this.completedTaskCount = completedTaskCount;
	}

	public void setTaskCount(long taskCount) {
		this.taskCount = taskCount;
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public long getCompletedTaskCount() {
		return completedTaskCount;
	}

	public long getTaskCount() {
		return taskCount;
	}

	public boolean isShutdown() {
		return shutdown;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public void setPendingTasks(List<Runnable> pendingTasks) {
		this.pendingTasks = pendingTasks;
	}

	public List<Runnable> getPendingTasks() {
		return pendingTasks;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

}
