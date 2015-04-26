package com.srcdevbin.buildbot.activity;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.srcdevbin.buildbot.operations.OperatingPool;
import com.srcdevbin.buildbot.operations.OperationType;

public class ActivityManagerTest {
	
	private ActivityManager manager;
	
	@Before
	public void buildUp(){
		manager = new ActivityManager();
		OperatingPool.CORE.start();
	}
	
	@After
	public void tearDown(){
		OperatingPool.CORE.stop();
	}
	
	@Test
	public void run(){
		Activity activity = Mockito.mock(Activity.class);
		when(activity.getType()).thenReturn(OperationType.NOOP);
		doNothing().when(activity).run();
		
		// Test
		manager.run(activity);
	}

}
