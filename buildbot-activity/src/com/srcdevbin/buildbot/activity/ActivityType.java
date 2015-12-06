package com.srcdevbin.buildbot.activity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum ActivityType {
	HELLO("com.srcdevbin.buildbot.activity.HelloActivity"),
	GREETING("com.srcdevbin.buildbot.activity.SpeechActivity");
	
	private String implementation;
	
	private ActivityType(String impl){
		this.implementation = impl;
	}
	
	public Activity getImplementation(){
		Activity activity;
		Class<?> clazz;
		try {
			clazz = Class.forName(this.implementation);
			Constructor<?> ctor = clazz.getConstructor(String.class);
			activity = (Activity) ctor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
				IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			activity = null;
		}
		return activity;
	}
	


}
