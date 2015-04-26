package com.srcdevbin.buildbot.activity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.srcdevbin.buildbot.operations.Execution;
import com.srcdevbin.buildbot.operations.ExecutionData;

public class ActivityData implements Serializable, ExecutionData{

	private static final long serialVersionUID = 2441602585628822931L;
	private String name;
	private Date executionTime;
	private Execution activity;
	private UUID uniqueId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public Execution getExecution() {
		return activity;
	}

	public void setExecution(Execution activity) {
		this.activity = activity;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

}
