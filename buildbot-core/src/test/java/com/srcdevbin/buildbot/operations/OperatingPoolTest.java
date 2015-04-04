package com.srcdevbin.buildbot.operations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.RejectedExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OperatingPoolTest {
	
	@Before
	public void buildUp(){
		OperatingPool.CORE.start();
	}
	
	@After
	public void tearDown(){
		OperatingPool.CORE.stop();
	}
	
	@Test
	public void createDefault(){
		// Verify
		assertThat(OperatingPool.CORE.getMaxPoolSize(), is(10));
	}
	
	@Test
	public void startWith5(){
		// Test
		PoolStatus status = OperatingPool.CORE.start(5);
		
		// Verify
		assertThat(OperatingPool.CORE.getMaxPoolSize(), is(5));
		assertThat(status.getMaxPoolSize(), is(5));
	}
	
	/**
	 * Test whether the pool restarts with the default
	 */
	@Test
	public void restart(){
		// Mock
		OperatingPool.CORE.start(5);
		
		// Test
		OperatingPool.CORE.restart();
		
		// Verify
		assertThat(OperatingPool.CORE.getMaxPoolSize(), is(10));
	}
	
	@Test
	public void pause() throws InterruptedException{
		// Verify running
		assertThat(OperatingPool.CORE.getStatus().isPaused(), is(false));
		
		// Test
		OperatingPool.CORE.pause();
		
		// Sleep to wait for a new status - should MonitorThread really sleep?
		Thread.sleep(6000);
		
		// Verify
		assertThat(OperatingPool.CORE.getStatus().isPaused(), is(true));
	}
	
	@Test
	public void resume() throws InterruptedException{
		// Verify running
		assertThat(OperatingPool.CORE.getStatus().isPaused(), is(false));
		
		// Test
		OperatingPool.CORE.pause();
		OperatingPool.CORE.resume();
		Thread.sleep(6000);
		
		
		// Verify
		assertThat(OperatingPool.CORE.getStatus().isPaused(), is(false));
	}
	
	@Test
	public void execute(){
		// Mock
		Runnable work = mock(Runnable.class);
		
		OperatingPool.CORE.execute(work);
		
		// Verify
		verify(work).run();
		verify(work, times(1));
	}
	
	@Test 
	public void pendingTasks() throws InterruptedException{
		OperatingPool pool = OperatingPool.CORE;
		
		// Some simple runnable task
		Runnable task = mock(Runnable.class);
		
		// Pause the pool
		pool.pause();
		Thread.sleep(6000);
		assertThat(pool.getStatus().isPaused(), is(true));
		
		// Submit some work, which should sit and wait.
		pool.execute(task);
		
		Thread.sleep(6000);
		PoolStatus status = pool.getStatus();
		
		// Check the number of work items waiting
		assertThat(status.getCompletedTaskCount(), is(0L));
		verify(task, times(0)).run();
	}
	
	@Test
	public void executeOneTask() throws InterruptedException{
		OperatingPool pool = OperatingPool.CORE;

		// Submit some work, which should sit and wait.
		Runnable work = mock(Runnable.class);
		pool.execute(work);
		
		verify(work).run();
		verify(work, times(1));
		Thread.sleep(6000);
		assertThat(pool.getStatus().getCompletedTaskCount(), is(1L));
	}
	
	@Test (expected = RejectedExecutionException.class)
	public void RejectedFromQueue() throws InterruptedException{
		int count = 50; // the queue is 10!!
		OperatingPool pool = OperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		
		// Submit some work, which should sit and wait.
		for(int i=0; i< count; i++){
			pool.execute(work);
		}
	}
	
	@Test
	public void fillQueue() throws InterruptedException{
		int count = 20; // Pool is 10, queue is 10
		OperatingPool pool = OperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		
		// Submit some work, which should sit and wait.
		for(int i=0; i< count; i++){
			pool.execute(work);
		}
		
		verify(work, times(20)).run();
		
		Thread.sleep(6000);
		assertThat(pool.getStatus().getCompletedTaskCount(), is(20L));
	}
	
	/**
	 * Test whether we can run more work than queue allows, if
	 * we buffer the execution in time.
	 * @throws InterruptedException
	 */
	@Test
	public void runAndSleep() throws InterruptedException{
		long buffer = 1000;
		int count = 30; // Pool is 10, queue is 10
		OperatingPool pool = OperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		
		// Submit some work, and wait in between
		for(int i=0; i< count; i++){
			if(i % 10 == 0) System.out.println("Submitting work #: " + i);
			pool.execute(work);
			Thread.sleep(buffer);
		}
		
		verify(work, times(30)).run();
		
		Thread.sleep(6000);
		assertThat(pool.getStatus().getCompletedTaskCount(), is(30L));
	}

}
