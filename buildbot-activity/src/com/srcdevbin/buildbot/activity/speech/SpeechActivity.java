package com.srcdevbin.buildbot.activity.speech;

import com.srcdevbin.buildbot.activity.Activity;
import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.operations.OperationType;
import com.srcdevbin.buildbot.voice.festival.Speaker;

public class SpeechActivity implements Activity {

	private ActivityData activityData;
	
	public SpeechActivity(ActivityData activityData) {
		this.activityData = activityData;
	}
	
	@Override
	public OperationType getType() {
		return OperationType.CORE;
	}

	@Override
	public void run() {
		byte[] data = activityData.getDetail().getDetail();
		String text = new String(data);
		new Speaker().speak(text);
	}

}
