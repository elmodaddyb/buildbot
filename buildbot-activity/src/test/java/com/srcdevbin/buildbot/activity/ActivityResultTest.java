package com.srcdevbin.buildbot.activity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ActivityResultTest {
	
	private ActivityResult activityResult;
	
	@Before
	public void buildUp(){
		activityResult = new ActivityResult();
	}
	
	@Test
	public void getStatus(){
		assertThat(activityResult.getStatus(), is(nullValue()));
		
		activityResult.setStatus(ActivityStatus.COMPLETE);
		
		assertThat(activityResult.getStatus(), is(ActivityStatus.COMPLETE));
	}
	
	@Test
	public void getActivityData(){
		assertThat(activityResult.getActivityData(), is(nullValue()));
		
		ActivityData activityData = new ActivityData();
		activityResult.setActivityData(activityData);
		
		assertThat(activityResult.getActivityData(), is(activityData));
	}

}
