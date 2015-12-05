package com.srcdevbin.buildbot.activity.respond;

import org.junit.Before;
import org.junit.Test;

import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.activity.ActivityDetail;
import com.srcdevbin.buildbot.operations.OperatingPool;

public class RespondActivityTest {
	
	@Before
	public void buildUp(){
		// This test requires async activities
		OperatingPool.CORE.start();
	}
	
	@Test
	public void test() throws InterruptedException{
		ActivityData activityData = new ActivityData();
		ActivityDetail detail = new ActivityDetail();
		detail.setDetail("Respond Activity Speech Test".getBytes());
		activityData.setName("SPEECH");
		activityData.setDetail(detail);
		
		// Test
		RespondActivity activity = new RespondActivity(activityData);
		activity.run();
		// Async speech could take time, so wait a bit
		Thread.sleep(3000);
	}

}
