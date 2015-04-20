package com.srcdevbin.buildbot.activity;

import java.io.Serializable;

public class HelloWorldActivity implements Serializable, Activity{
	private static final long serialVersionUID = 4296097846812602884L;
	
	public ActivityResult call() throws Exception {
		System.out.println("Hello World");
		ActivityResult result = new ActivityResult();
		result.setStatus(ActivityStatus.COMPLETE);
		return result;
	}
	
}
