package com.srcdevbin.buildbot.activity;

import com.srcdevbin.buildbot.operations.PoolManager;

public class ActivityManager {
	
	private PoolManager poolManager;
	
	public ActivityManager(){
		poolManager = new PoolManager();
	}
	
	public void run(Activity activity){
		poolManager.submit(activity);
	}

}
