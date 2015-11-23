package buildbot.communication.message;

public class ExceptionReply implements BuildBotReply {

	private ReplyStatus status;
	private byte[] data;
	
	public ExceptionReply(Throwable e) {
		status = ReplyStatus.PROCESSING;
	}
	
	@Override
	public ReplyStatus getStatus(){
		return status;
	}
	
	@Override
	public void setStatus(ReplyStatus status){
		this.status = status;
	}

	private static final long serialVersionUID = -7891509792237736295L;

	@Override
	public byte[] getData() {
		return data;
	}

	@Override
	public void setData(byte[] data) {
		this.data = data;
	}

}
