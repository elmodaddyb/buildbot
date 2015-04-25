package com.srcdevbin.buildbot.activity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class InteractionData implements Serializable{

	private static final long serialVersionUID = 2441602585628822931L;
	private String name;
	private Date executionTime;
	private Interaction iAction;
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

	public Interaction getInteraction() {
		return iAction;
	}

	public void setInteraction(Interaction iAction) {
		this.iAction = iAction;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

}
