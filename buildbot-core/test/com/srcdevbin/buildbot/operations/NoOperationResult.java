package com.srcdevbin.buildbot.operations;

public class NoOperationResult implements OperationResult {

	@Override
	public OperationStatus getStatus() {
		return OperationStatus.COMPLETE;
	}

	@Override
	public void setStatus(OperationStatus status) {
		// no op
	}

	@Override
	public OperationData getOperationData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOperationData(OperationData operationData) {
		// TODO Auto-generated method stub
		
	}

}
