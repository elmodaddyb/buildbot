package com.srcdevbin.buildbot.operations.scheduled;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScheduledOperatingPoolTest {
	
	@Before
	public void buildUp(){
		ScheduledOperatingPool.CORE.start();
	}
	
	@After
	public void tearDown(){
		ScheduledOperatingPool.CORE.stop();
	}
	
	@Test
	public void createDefault(){
		// Verify
		assertThat(ScheduledOperatingPool.CORE.getMaxPoolSize(), is(10));
	}
	
	@Test
	public void startWith5(){
		// Test
		ScheduledOperatingPool.CORE.start(5);
		
		// Verify
		assertThat(ScheduledOperatingPool.CORE.getMaxPoolSize(), is(5));
	}
	
	/**
	 * Test whether the pool restarts with the default
	 */
	@Test
	public void restart(){
		// Mock
		ScheduledOperatingPool.CORE.start(5);
		
		// Test
		ScheduledOperatingPool.CORE.restart();
		
		// Verify
		assertThat(ScheduledOperatingPool.CORE.getMaxPoolSize(), is(10));
	}
	
	@Test
	public void schedule() throws InterruptedException{
		// Mock
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		ScheduledOperatingPool.CORE.schedule(work, 1000, TimeUnit.MILLISECONDS);
		
		// Wait for scheduled work to be completed
		Thread.sleep(2000);
		
		// Verify
		verify(work, times(1)).run();
	}
	
	@Test
	public void executeOneTask() throws InterruptedException{
		ScheduledOperatingPool pool = ScheduledOperatingPool.CORE;

		// Submit some work, which should sit and wait.
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		pool.schedule(work, 500, TimeUnit.MILLISECONDS);
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(1000);
		
		verify(work, times(1)).run();
		assertThat(pool.getStatus().getCompletedTaskCount(), is(1L));
	}
	
	@Test (expected = RejectedExecutionException.class)
	public void rejectFromQueue() throws InterruptedException{
		int count = 50; // the queue is 10!!
		ScheduledOperatingPool pool = ScheduledOperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		// Submit some work, which should get rejected
		for(int i=0; i< count; i++){
			pool.schedule(work, 10, TimeUnit.MILLISECONDS);
		}
		
		// Wait for work to be done
		Thread.sleep(5000);
	}
	
	@Test
	public void fillQueue() throws InterruptedException{
		int count = 20; // Pool is 10, queue is 10
		ScheduledOperatingPool pool = ScheduledOperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		// Submit some work, which should sit and wait.
		for(int i=0; i< count; i++){
			pool.schedule(work, 10, TimeUnit.MILLISECONDS);
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
		ScheduledOperatingPool pool = ScheduledOperatingPool.CORE;
		Runnable work = mock(Runnable.class);
		doNothing().when(work).run();
		
		// Submit some work, and wait in between
		for(int i=0; i< count; i++){
			pool.schedule(work, 5, TimeUnit.MILLISECONDS);
			Thread.sleep(buffer);
		}
		
		// Work done on other threads without Future hook
		// may complete at any time, so wait a bit.
		Thread.sleep(2000);
		
		verify(work, times(30)).run();
		assertThat(pool.getStatus().getCompletedTaskCount(), is(30L));
	}

}
