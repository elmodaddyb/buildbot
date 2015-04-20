package com.srcdevbin.buildbot.activity;

public class ActivityResult {
	
	private ActivityStatus status;
	private ActivityData activityData;

	public ActivityData getActivityData() {
		return activityData;
	}

	public void setActivityData(ActivityData activityData) {
		this.activityData = activityData;
	}

	public ActivityStatus getStatus() {
		return status;
	}

	public void setStatus(ActivityStatus status) {
		this.status = status;
	}

}
