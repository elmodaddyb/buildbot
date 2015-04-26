package com.srcdevbin.buildbot.interaction;

import com.srcdevbin.buildbot.operations.OperationData;
import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.OperationStatus;

public class InteractionResult implements OperationResult {
	
	private OperationStatus status;
	private OperationData operationData;

	public OperationData getOperationData() {
		return operationData;
	}

	public void setOperationData(OperationData operationData) {
		this.operationData = operationData;
	}

	public OperationStatus getStatus() {
		return status;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}

}
