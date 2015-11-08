package com.srcdevbin.buildbot.activity;

import com.srcdevbin.buildbot.interaction.Interaction;
import com.srcdevbin.buildbot.interaction.InteractionResult;
import com.srcdevbin.buildbot.operations.OperationData;
import com.srcdevbin.buildbot.operations.OperationResult;
import com.srcdevbin.buildbot.operations.OperationStatus;
import com.srcdevbin.buildbot.operations.OperationType;

public class SlowInteraction implements Interaction {

	private long delay;
	
	public SlowInteraction() {
		// TODO Auto-generated constructor stub
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public OperationResult call() throws Exception {
		Thread.sleep(delay);
		OperationResult result = new InteractionResult();
		result.setStatus(OperationStatus.COMPLETE);
		return result;
	}
	
	public OperationType getType(){
		return OperationType.CORE;
	}

	@Override
	public OperationData getData() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
