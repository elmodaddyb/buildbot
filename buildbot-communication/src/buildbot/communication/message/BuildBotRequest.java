package buildbot.communication.message;

import java.io.Serializable;

public interface BuildBotRequest extends Serializable{

	RequestType getType();
	
	byte[] getData();
	
	void setData(byte[] data);
}
