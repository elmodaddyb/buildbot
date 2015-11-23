package buildbot.communication.message;

public class ActivityRequest implements BuildBotRequest {

	private static final long serialVersionUID = -4310153028039058578L;

	private byte[] data;
	
	@Override
	public RequestType getType() {
		return RequestType.ACTIVITY;
	}
	
	@Override
	public byte[] getData(){
		return data;
	}
	
	@Override
	public void setData(byte[] data){
		this.data = data;
	}

}
