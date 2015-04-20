package com.srcdevbin.buildbot.activity;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldActivityTest {
	
	private Activity activity;

	@Before
	public void buildUp(){
		activity = new HelloWorldActivity();
	}
	
	@Test
	public void call() throws Exception{
		ActivityResult result = activity.call();
		assertThat(result.getStatus(), is(ActivityStatus.COMPLETE));
	}

}
