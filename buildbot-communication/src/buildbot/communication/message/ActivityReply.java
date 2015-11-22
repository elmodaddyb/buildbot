package buildbot.communication.message;

public class ActivityReply implements BuildBotReply {
	private ReplyStatus status;
	
	private static final long serialVersionUID = 7594186807121808683L;

	@Override
	public ReplyStatus getStatus() {
		return status;
	}
	
	public void setStatus(ReplyStatus status){
		this.status = status;
	}

}
