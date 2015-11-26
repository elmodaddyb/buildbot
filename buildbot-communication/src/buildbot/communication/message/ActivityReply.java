package buildbot.communication.message;

public class ActivityReply implements BuildBotReply {
	protected ReplyStatus status;
	private byte[] data;
	
	private static final long serialVersionUID = 7594186807121808683L;

	@Override
	public ReplyStatus getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(ReplyStatus status){
		this.status = status;
	}

	@Override
	public byte[] getData() {
		return data;
	}

	@Override
	public void setData(byte[] data) {
		this.data = data;
	}

}
