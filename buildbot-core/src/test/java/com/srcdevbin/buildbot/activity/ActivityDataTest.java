package com.srcdevbin.buildbot.activity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class ActivityDataTest {
	
	private ActivityData activityData;
	
	@Before
	public void buildUp(){
		activityData = new ActivityData();
	}
	
	@Test
	public void getName(){
		assertThat(activityData.getName(), is(nullValue()));
		
		activityData.setName("Name");
		
		assertThat(activityData.getName(), is("Name"));
	}
	
	@Test
	public void getExecutionTime(){
		assertThat(activityData.getExecutionTime(), is(nullValue()));
		
		Date date = new Date();
		activityData.setExecutionTime(date);
		
		assertThat(activityData.getExecutionTime(), is(date));
	}
	
	@Test
	public void getData(){
		assertThat(activityData.getData(), is(nullValue()));
		
		ByteBuffer bbuf = ByteBuffer.allocate(10);
		activityData.setData(bbuf);
		
		assertThat(activityData.getData(), is(bbuf));
	}
	
	@Test
	public void getUniqueId(){
		assertThat(activityData.getUniqueId(), is(nullValue()));
		
		UUID uuid = UUID.randomUUID();
		activityData.setUniqueId(uuid);
		
		assertThat(activityData.getUniqueId(), is(uuid));
	}
}
