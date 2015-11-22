package buildbot.communication;

import com.srcdevbin.buildbot.activity.ActivityData;

import buildbot.communication.message.ActivityReply;
import buildbot.communication.message.ActivityRequest;
import buildbot.communication.message.BuildBotReply;
import buildbot.communication.message.BuildBotRequest;
import buildbot.communication.message.ExceptionReply;
import buildbot.communication.message.ReplyStatus;
/**
 * Interface with the ActivityManager
 * @author eamonn
 *
 */
public class CommunicationManager {

	public BuildBotReply process(BuildBotRequest request) {
		BuildBotReply reply;
		switch(request.getType()){
		case ACTIVITY:
			ActivityData data = ((ActivityRequest)request).getActivityData();
			reply = new ActivityReply();
			((ActivityReply)reply).setStatus(ReplyStatus.COMPLETE_SUCCESS);
			break;
		case INTERACTION:
			reply = null;
			break;
		default:
			reply = new ExceptionReply(new RuntimeException("Request Type Not Found"));
			((ExceptionReply)reply).setStatus(ReplyStatus.COMPLETE_FAILURE);
		}
		
		return reply;
	}

}
