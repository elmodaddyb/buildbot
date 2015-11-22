package buildbot.communication.message;

import com.srcdevbin.buildbot.activity.ActivityData;

public class ActivityRequest implements BuildBotRequest {

	private static final long serialVersionUID = -4310153028039058578L;

	private ActivityData activityData;
	
	@Override
	public RequestType getType() {
		return RequestType.ACTIVITY;
	}
	
	public ActivityData getActivityData(){
		return activityData;
	}
	
	public void setActivityData(ActivityData activityData){
		this.activityData = activityData;
	}

}
