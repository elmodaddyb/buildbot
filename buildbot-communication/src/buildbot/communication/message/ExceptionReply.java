package buildbot.communication.message;

public class ExceptionReply implements BuildBotReply {

	private ReplyStatus status;
	
	public ExceptionReply(Throwable e) {
		status = ReplyStatus.PROCESSING;
	}
	
	public ReplyStatus getStatus(){
		return status;
	}
	
	public void setStatus(ReplyStatus status){
		this.status = status;
	}

	private static final long serialVersionUID = -7891509792237736295L;

}
