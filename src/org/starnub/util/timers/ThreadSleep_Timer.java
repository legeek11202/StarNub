package org.starnub.util.timers;

import org.starnub.util.stream.SN_MessageFormater;


public class ThreadSleep_Timer {
	
	public static void startTimer(int timer)
	{
		try 
		{
			Thread.sleep(timer*1000);
		} 
		catch (InterruptedException e) 
		{
			SN_MessageFormater.msgPrint("Timer Error: Thread Sleep Error.", 0, 1);
			e.printStackTrace();
		}
	}
	
	public ThreadSleep_Timer() {
	}
}
