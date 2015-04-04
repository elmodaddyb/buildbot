package com.srcdevbin.buildbot.protocol;

import java.util.Date;

import com.srcdevbin.buildbot.project.BuildStatus;

public class ProjectBuildMessage {
	
	private BuildStatus status;
	private String projectName;
	private Long buildNumber;
	private Date buildDate;
	private String failureMessage;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(Long buildNumber) {
		this.buildNumber = buildNumber;
	}
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	public String getFailureMessage() {
		return failureMessage;
	}
	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	public BuildStatus getStatus() {
		return status;
	}
	public void setStatus(BuildStatus status) {
		this.status = status;
	}

}
