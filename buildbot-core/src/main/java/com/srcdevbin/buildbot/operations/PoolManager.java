package com.srcdevbin.buildbot.operations;

import java.util.ArrayList;
import java.util.List;


public class PoolManager {
	
	public List<PoolStatus> stopAll(){
		List<PoolStatus> poolStatus = new ArrayList<PoolStatus>();
		
		for (OperatingPool pool : OperatingPool.values()){
			poolStatus.add(pool.stop());
		}
		return poolStatus;
	}

}
