package com.srcdevbin.buildbot.activity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class InteractionDataTest {
	
	private InteractionData activityData;
	
	@Before
	public void buildUp(){
		activityData = new InteractionData();
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
		assertThat(activityData.getInteraction(), is(nullValue()));
		
		Interaction iAction = Mockito.mock(Interaction.class);
		activityData.setInteraction(iAction);
		
		assertThat(activityData.getInteraction(), is(iAction));
	}
	
	@Test
	public void getUniqueId(){
		assertThat(activityData.getUniqueId(), is(nullValue()));
		
		UUID uuid = UUID.randomUUID();
		activityData.setUniqueId(uuid);
		
		assertThat(activityData.getUniqueId(), is(uuid));
	}
}
