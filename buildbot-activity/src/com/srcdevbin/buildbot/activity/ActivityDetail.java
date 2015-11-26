package com.srcdevbin.buildbot.activity;

import java.io.Serializable;

public class ActivityDetail implements Serializable {

	private static final long serialVersionUID = -4136439767849676551L;

	private byte[] detail;
	
	
	public byte[] getDetail(){
		return detail;
	};
	
	public void setDetail(byte[] detail){
		this.detail = detail;
	}

}
