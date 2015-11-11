package buildbot.communication;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import buildbot.communication.message.BuildBotReply;
import buildbot.communication.message.BuildBotRequest;
import buildbot.communication.message.ExceptionReply;
import buildbot.communication.message.ExceptionRequest;

public class BuildBotProtocolTest {
	
	private BuildBotProtocol protocol;
	
	private Socket socket;
	private CommunicationManager communicationManager;
	private InputStream inputStream;
	private OutputStream outputStream;
	private BuildBotReply reply;
	
	@Before
	public void buildUp() throws IOException{
		protocol = new BuildBotProtocol();
		socket = mock(Socket.class);
		communicationManager = mock(CommunicationManager.class);
		reply = new ExceptionReply(new RuntimeException("REPLY"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(baos);
	    BuildBotRequest request = new ExceptionRequest(new RuntimeException("REQUEST"));
	    
		Whitebox.setInternalState(protocol, "communicationManager", communicationManager);
	    oos.writeObject(request);
	    
		inputStream = new ByteArrayInputStream(baos.toByteArray());
		outputStream = mock(OutputStream.class);
		
	}
	
	@Test
	public void process() throws IOException{
		// MOCK
		when(socket.getInputStream()).thenReturn(inputStream);
		when(socket.getOutputStream()).thenReturn(outputStream);
		when(communicationManager.process(Mockito.any(BuildBotRequest.class))).thenReturn(reply);
		doNothing().when(outputStream).write(Mockito.anyByte());
		
		// TEST
		protocol.process(socket);
		
		// VERIFY
		verify(socket, times(1)).getInputStream();
		verify(socket, times(1)).getOutputStream();
		verify(communicationManager, times(1)).process(Mockito.any(BuildBotRequest.class));
	}

}
