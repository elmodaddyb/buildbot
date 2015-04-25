package com.srcdevbin.buildbot.activity;

import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.OperationStatus;

public class InteractionResult implements OperationResult {
	
	private OperationStatus status;
	private InteractionData interactionData;

	public InteractionData getActivityData() {
		return interactionData;
	}

	public void setActivityData(InteractionData interactionData) {
		this.interactionData = interactionData;
	}

	public OperationStatus getStatus() {
		return status;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}

}
