package buildbot.communication.message;

import java.io.Serializable;

public interface BuildBotReply extends Serializable {
	
	ReplyStatus getStatus();
	
	void setStatus(ReplyStatus status);
	
	byte[] getData();
	
	void setData(byte[] data);

}
