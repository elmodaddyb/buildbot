package buildbot.communication.message;

public class ExceptionRequest implements BuildBotRequest {

	public ExceptionRequest(Throwable e) {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -6329835817920062902L;

	@Override
	public RequestType getType() {
		return RequestType.ACTIVITY;
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(byte[] data) {
		// TODO Auto-generated method stub
		
	}

}
