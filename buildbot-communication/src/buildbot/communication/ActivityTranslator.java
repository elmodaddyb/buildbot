package buildbot.communication;

import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.activity.ActivityManager;
import com.srcdevbin.buildbot.activity.ActivityType;
import com.srcdevbin.buildbot.activity.HelloActivity;

import buildbot.communication.message.ActivityReply;
import buildbot.communication.message.ActivitySuccessReply;

public class ActivityTranslator {
	
	private ActivityManager manager;
	
	public ActivityTranslator(){
		this.manager = new ActivityManager();
	}
	
	public ActivityReply process(ActivityData data){
		return processActivity(data);
	}
	
	private ActivityReply processActivity(ActivityData data){
		ActivityReply reply = null;
		ActivityType type = ActivityType.valueOf(data.getName());
		switch(type){
		case  HELLO:
			HelloActivity activity = new HelloActivity(data);
			manager.run(activity);
			reply = new ActivitySuccessReply("DONE");
			break;
		}
		return reply;
	}

}
