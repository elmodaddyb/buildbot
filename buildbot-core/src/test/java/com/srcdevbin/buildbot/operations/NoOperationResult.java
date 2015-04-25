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

}
