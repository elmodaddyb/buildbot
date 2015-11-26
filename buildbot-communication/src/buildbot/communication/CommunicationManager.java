package buildbot.communication;

import org.apache.commons.lang3.SerializationUtils;

import com.srcdevbin.buildbot.activity.ActivityData;

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
			ActivityData activtyData = (ActivityData)SerializationUtils.deserialize(request.getData());
			reply = new ActivityTranslator().process(activtyData);
			reply.setStatus(ReplyStatus.COMPLETE_SUCCESS);
			break;
		case INTERACTION:
			reply = null;
			break;
		default:
			reply = new ExceptionReply(new RuntimeException("Request Type Not Found"));
			reply.setStatus(ReplyStatus.COMPLETE_FAILURE);
		}
		
		return reply;
	}

}
