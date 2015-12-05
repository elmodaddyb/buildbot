package com.srcdevbin.buildbot.activity.movement;

import com.srcdevbin.buildbot.activity.Activity;
import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.operations.OperationType;

public class MovementActivity implements Activity {

	private ActivityData activityData;
	
	public MovementActivity(ActivityData activityData) {
		this.activityData = activityData;
	}
	
	@Override
	public OperationType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
