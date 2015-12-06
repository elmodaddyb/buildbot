package buildbot.communication;

import com.srcdevbin.buildbot.activity.ActivityData;
import com.srcdevbin.buildbot.activity.ActivityDetail;
import com.srcdevbin.buildbot.activity.ActivityManager;
import com.srcdevbin.buildbot.activity.ActivityType;
import com.srcdevbin.buildbot.activity.HelloActivity;
import com.srcdevbin.buildbot.activity.speech.SpeechActivity;

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
		// TODO: translate inbound data into an activity.
		switch(type){
		case  HELLO:
			HelloActivity activity = new HelloActivity(data);
			manager.run(activity);
			reply = new ActivitySuccessReply("DONE");
			break;
		case  GREETING:
			ActivityDetail detail = new ActivityDetail();
			detail.setDetail("Hello How Are you?".getBytes());
			data.setDetail(detail);
			SpeechActivity activity1 = new SpeechActivity(data);
			manager.run(activity1);
			reply = new ActivitySuccessReply("DONE");
			break;
		}
		return reply;
	}

}
