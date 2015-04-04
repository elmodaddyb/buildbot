package com.srcdevbin.buildbot.protocol;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.srcdevbin.buildbot.project.BuildStatus;

public class MessageFactoryTest {
	
	@Test
	public void createFailureMessage(){
		BuildStatus status = BuildStatus.FAILURE;
		Date buildDate = new Date();
		String projectName = "Test Project";
		Long buildNumber = 1L;
		String failureMessage = "A Failure Message";
		
		ProjectBuildMessage msg = MessageFactory.createMessage(status, buildDate, projectName, buildNumber, failureMessage);
		
		// Verify
		assertThat(msg.getBuildDate(), is(buildDate));
		assertThat(msg.getBuildNumber(), is(buildNumber));
		assertThat(msg.getFailureMessage(), is(failureMessage));
		assertThat(msg.getProjectName(), is(projectName));
		assertThat(msg.getStatus(), is(BuildStatus.FAILURE));
	}
	
	@Test
	public void createSuccessMessage(){
		BuildStatus status = BuildStatus.SUCCESS;
		Date buildDate = new Date();
		String projectName = "Test Project";
		Long buildNumber = 1L;
		String failureMessage = "A Success Message";
		
		ProjectBuildMessage msg = MessageFactory.createMessage(status, buildDate, projectName, buildNumber, failureMessage);
		
		// Verify
		assertThat(msg.getBuildDate(), is(buildDate));
		assertThat(msg.getBuildNumber(), is(buildNumber));
		assertThat(msg.getFailureMessage(), is(failureMessage));
		assertThat(msg.getProjectName(), is(projectName));
		assertThat(msg.getStatus(), is(BuildStatus.SUCCESS));
	}

}
