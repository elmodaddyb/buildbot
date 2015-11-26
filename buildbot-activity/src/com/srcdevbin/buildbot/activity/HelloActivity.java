package com.srcdevbin.buildbot.activity;

import java.io.UnsupportedEncodingException;

import com.srcdevbin.buildbot.operations.OperationType;

public class HelloActivity implements Activity{

	private ActivityDetail detail;
	
	public HelloActivity(ActivityData activityData) {
		this.detail = activityData.getDetail();
	}

	@Override
	public OperationType getType() {
		return OperationType.CORE;
	}

	@Override
	public void run() {
		try {
			System.out.println(new String(detail.getDetail(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
