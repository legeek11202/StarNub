package org.starnub.util.timers;

public class ThreadSleep_Timer {
	
	public static void startTimer(int timer)
	{
		try 
		{
			Thread.sleep(timer*1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ThreadSleep_Timer() {
	}
}
