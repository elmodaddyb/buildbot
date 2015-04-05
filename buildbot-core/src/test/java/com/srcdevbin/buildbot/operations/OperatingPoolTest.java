package com.srcdevbin.buildbot.operations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

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
		OperatingPool.CORE.start(5);
		
		// Verify
		assertThat(OperatingPool.CORE.getMaxPoolSize(), is(5));
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
		
		// Verify
		assertThat(OperatingPool.CORE.getStatus().isPaused(), is(false));
	}
	
	@Test
	public void execute() throws InterruptedException{
		// Mock
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		OperatingPool.CORE.execute(work);
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(1000);
		
		// Verify
		verify(work, times(1)).run();
	}
	
	@Test 
	public void pendingTasks() throws InterruptedException{
		OperatingPool pool = OperatingPool.CORE;
		
		// Some simple runnable task
		Runnable task = mock(Runnable.class);
		doNothing().when(task).run();
		
		// Pause the pool
		pool.pause();
		assertThat(pool.getStatus().isPaused(), is(true));
		
		// Submit some work, which should sit and wait.
		pool.execute(task);
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(100);
		
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
		doNothing().when(work).run();
		
		pool.execute(work);
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(1000);
		
		verify(work, times(1)).run();
		assertThat(pool.getStatus().getCompletedTaskCount(), is(1L));
	}
	
	@Test (expected = RejectedExecutionException.class)
	public void rejectFromQueue() throws InterruptedException{
		int count = 50; // the queue is 10!!
		OperatingPool pool = OperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		// Submit some work, which should get rejected
		for(int i=0; i< count; i++){
			pool.execute(work);
		}
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(1000);
	}
	
	@Test
	public void fillQueue() throws InterruptedException{
		int count = 20; // Pool is 10, queue is 10
		OperatingPool pool = OperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		// Submit some work, which should sit and wait.
		for(int i=0; i< count; i++){
			pool.execute(work);
		}		
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(2000);
		
		verify(work, times(20)).run();
		assertThat(pool.getStatus().getCompletedTaskCount(), is(20L));
	}
	
	/**
	 * Test whether we can run more work than queue allows, if
	 * we buffer the execution in time.
	 * @throws InterruptedException
	 */
	@Test
	public void runAndSleep() throws InterruptedException{
		long buffer = 10;
		int count = 30; // Pool is 10, queue is 10
		OperatingPool pool = OperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		// Submit some work, and wait in between
		for(int i=0; i< count; i++){
			pool.execute(work);
			Thread.sleep(buffer);
		}
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(1000);
		
		verify(work, times(30)).run();
		assertThat(pool.getStatus().getCompletedTaskCount(), is(30L));
	}

}
