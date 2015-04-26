package com.srcdevbin.buildbot.operations;

public interface OperationResult {
	
	OperationStatus getStatus();
	
	void setStatus(OperationStatus status);
	
	OperationData getOperationData();
	
	void setOperationData(OperationData operationData);

}
