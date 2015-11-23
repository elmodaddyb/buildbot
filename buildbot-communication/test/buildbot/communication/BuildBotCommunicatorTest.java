package buildbot.communication;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.srcdevbin.buildbot.activity.ActivityData;

import buildbot.communication.message.ActivityRequest;
import buildbot.communication.message.BuildBotReply;
import buildbot.communication.message.BuildBotRequest;

public class BuildBotCommunicatorTest {
	
	private BuildBotCommunicator communicator;
	
	@Before
	public void buildUp(){
		communicator = new BuildBotCommunicator("localhost", 55055);
	}
	
	
	@Test
	public void communicateRemote() throws Exception{
		ActivityData data = new ActivityData();
		data.setExecutionTime(new Date());
		data.setName("TEST ACTIVITY");
		data.setUniqueId(UUID.randomUUID());
		
		BuildBotRequest request = new ActivityRequest();
		
		request.setData(SerializationUtils.serialize(data));
		
		BuildBotReply reply = communicator.communicate(request);
		Assert.assertThat(reply, notNullValue());
	}

}
