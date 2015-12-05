package com.srcdevbin.buildbot.activity.respond;

import com.srcdevbin.buildbot.activity.Activity;
import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.activity.ActivityManager;
import com.srcdevbin.buildbot.activity.movement.MovementActivity;
import com.srcdevbin.buildbot.activity.speech.SpeechActivity;
import com.srcdevbin.buildbot.operations.OperationType;

public class RespondActivity implements Activity {

	private ActivityData activityData;
	
	public RespondActivity(ActivityData activityData) {
		this.activityData = activityData;
	}
	
	
	@Override
	public OperationType getType() {
		return OperationType.CORE;
	}

	@Override
	public void run() {
		RespondWith todo = identifyResponse(activityData);
		switch (todo) {
		case SPEECH:
			new ActivityManager().run(new SpeechActivity(activityData));
			break;
		case MOVEMENT:
			new ActivityManager().run(new MovementActivity(activityData));
		case NOTHING:
			break;
		default:
			break;
		}
	}
	
	private RespondWith identifyResponse(ActivityData activityData){
		RespondWith todo;
		String name = activityData.getName();
		if(name.equals(RespondWith.SPEECH.toString())){
			todo = RespondWith.SPEECH;
		} else {
			todo = RespondWith.NOTHING;
		}
		return todo;
	}

}
