package buildbot.communication;

public class BuildBotCommException extends Exception {
	
	private static final long serialVersionUID = 4638433339243431587L;

	public BuildBotCommException(String message) {
		super(message);
	}

	public BuildBotCommException(Throwable cause) {
		super(cause);
	}

	public BuildBotCommException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildBotCommException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
