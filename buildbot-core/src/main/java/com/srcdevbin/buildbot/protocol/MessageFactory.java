package com.srcdevbin.buildbot.protocol;

import java.util.Date;

import com.srcdevbin.buildbot.project.BuildStatus;

public class MessageFactory {

	public static ProjectBuildMessage createMessage(BuildStatus status, Date buildDate,
			String projectName, Long buildNumber, String failureMessage) {
		ProjectBuildMessage msg = new ProjectBuildMessage();
		msg.setBuildDate(buildDate);
		msg.setBuildNumber(buildNumber);
		msg.setFailureMessage(failureMessage);
		msg.setProjectName(projectName);
		msg.setStatus(status);
		return msg;
	}

}
