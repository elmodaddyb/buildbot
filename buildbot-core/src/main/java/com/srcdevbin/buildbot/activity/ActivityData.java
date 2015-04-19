package com.srcdevbin.buildbot.activity;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

public class ActivityData implements Serializable{

	private static final long serialVersionUID = 2441602585628822931L;
	private String name;
	private Date executionTime;
	private ByteBuffer data;
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

	public ByteBuffer getData() {
		return data;
	}

	public void setData(ByteBuffer data) {
		this.data = data;
	}

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

}
