package buildbot.communication.message;

public class ActivitySuccessReply extends ActivityReply {

	private static final long serialVersionUID = -9104911231811880533L;

	public ActivitySuccessReply(String message) {
		super();
		status = ReplyStatus.COMPLETE_SUCCESS;
	}

}
