package com.srcdevbin.buildbot.activity;

public class InteractionException extends Exception {

	private static final long serialVersionUID = -2052916823505173238L;

	public InteractionException(String message) {
		super(message);
	}

	public InteractionException(Throwable cause) {
		super(cause);
	}

	public InteractionException(String message, Throwable cause) {
		super(message, cause);
	}

	public InteractionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
