package com.srcdevbin.buildbot.activity;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.srcdevbin.buildbot.interaction.Interaction;
import com.srcdevbin.buildbot.interaction.InteractionData;

public class InteractionDataTest {
	
	private InteractionData iData;
	
	@Before
	public void buildUp(){
		iData = new InteractionData();
	}
	
	@Test
	public void getName(){
		assertThat(iData.getName(), is(nullValue()));
		
		iData.setName("Name");
		
		assertThat(iData.getName(), is("Name"));
	}
	
	@Test
	public void getExecutionTime(){
		assertThat(iData.getExecutionTime(), is(nullValue()));
		
		Date date = new Date();
		iData.setExecutionTime(date);
		
		assertThat(iData.getExecutionTime(), is(date));
	}
	
	@Test
	public void getData(){
		assertThat(iData.getOperation(), is(nullValue()));
		
		Interaction iAction = Mockito.mock(Interaction.class);
		iData.setOperation(iAction);
		
		assertThat(iData.getOperation(), is(iAction));
	}
	
	@Test
	public void getUniqueId(){
		assertThat(iData.getUniqueId(), is(nullValue()));
		
		UUID uuid = UUID.randomUUID();
		iData.setUniqueId(uuid);
		
		assertThat(iData.getUniqueId(), is(uuid));
	}
	
	@Test
	public void isSerializable(){
		byte[] objectData = SerializationUtils.serialize(iData);
		InteractionData deserialized = (InteractionData)SerializationUtils.deserialize(objectData);
		
		assertThat(deserialized, instanceOf(InteractionData.class));
	}

}
