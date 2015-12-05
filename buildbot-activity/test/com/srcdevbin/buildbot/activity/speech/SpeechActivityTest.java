package com.srcdevbin.buildbot.activity.speech;

import org.junit.Test;

import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.activity.ActivityDetail;

public class SpeechActivityTest {

	@Test
	public void test() {
		// Mock
		ActivityData data = new ActivityData();
		ActivityDetail detail = new ActivityDetail();
		detail.setDetail("Test Speech Activity".getBytes());
		data.setDetail(detail);

		// Test
		SpeechActivity activity = new SpeechActivity(data);
		activity.run();
	}

}
