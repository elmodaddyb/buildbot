package com.srcdevbin.buildbot.interaction;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.srcdevbin.buildbot.interaction.InteractionData;
import com.srcdevbin.buildbot.interaction.InteractionResult;
import com.srcdevbin.buildbot.operations.OperationStatus;

public class InteractionResultTest {
	
	private InteractionResult activityResult;
	
	@Before
	public void buildUp(){
		activityResult = new InteractionResult();
	}
	
	@Test
	public void getStatus(){
		assertThat(activityResult.getStatus(), is(nullValue()));
		
		activityResult.setStatus(OperationStatus.COMPLETE);
		
		assertThat(activityResult.getStatus(), is(OperationStatus.COMPLETE));
	}
	
	@Test
	public void getActivityData(){
		assertThat(activityResult.getOperationData(), is(nullValue()));
		
		InteractionData activityData = new InteractionData();
		activityResult.setOperationData(activityData);
		
		assertThat(activityResult.getOperationData(), is(activityData));
	}

}
