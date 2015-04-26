package com.srcdevbin.buildbot.interaction;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.srcdevbin.buildbot.operations.Operation;
import com.srcdevbin.buildbot.operations.OperationData;

public class InteractionData implements Serializable, OperationData{

	private static final long serialVersionUID = 2441602585628822931L;
	private String name;
	private Date executionTime;
	private Operation iAction;
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

	public Operation getOperation() {
		return iAction;
	}

	public void setOperation(Operation iAction) {
		this.iAction = iAction;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

}
