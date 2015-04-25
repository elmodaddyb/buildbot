package com.srcdevbin.buildbot.operations;

import java.util.concurrent.Callable;

public interface Operation extends Callable<OperationResult> {
	
	OperationType getType();

}
