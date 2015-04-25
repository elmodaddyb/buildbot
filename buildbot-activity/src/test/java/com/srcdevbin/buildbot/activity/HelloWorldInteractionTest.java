package com.srcdevbin.buildbot.activity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.srcdevbin.buildbot.operations.OperationStatus;

public class HelloWorldInteractionTest {
	
	private Interaction iAction;

	@Before
	public void buildUp(){
		iAction = new HelloWorldInteraction();
	}
	
	@Test
	public void call() throws Exception{
		InteractionResult result = (InteractionResult)iAction.call();
		assertThat(result.getStatus(), is(OperationStatus.COMPLETE));
	}

}
