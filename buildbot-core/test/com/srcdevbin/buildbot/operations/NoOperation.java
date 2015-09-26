package com.srcdevbin.buildbot.operations;

public class NoOperation implements Operation {

	@Override
	public OperationResult call() throws Exception {
		return new NoOperationResult();
	}

	@Override
	public OperationType getType() {
		return OperationType.NOOP;
	}

	@Override
	public OperationData getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
