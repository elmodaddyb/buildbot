package buildbot.communication.message;

import java.io.Serializable;

public interface BuildBotReply extends Serializable {
	
	ReplyStatus getStatus(); 

}
