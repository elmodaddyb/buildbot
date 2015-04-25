package com.srcdevbin.buildbot.activity;

import java.io.Serializable;

import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.OperationStatus;
import com.srcdevbin.buildbot.operations.OperationType;

public class HelloWorldInteraction implements Serializable, Interaction{
	private static final long serialVersionUID = 4296097846812602884L;
	
	public OperationResult call() throws Exception {
		System.out.println("Hello World");
		OperationResult result = new InteractionResult();
		result.setStatus(OperationStatus.COMPLETE);
		return result;
	}
	
	public OperationType getType(){
		return OperationType.CORE;
	}
	
}
