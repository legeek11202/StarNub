package org.starnub.util.timers;

/**
 * This class will sleep the thread in which it was called from.
 *
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 * 
 */

public class ThreadSleep {

	public ThreadSleep()
	{
	}

	/**
	 * This method will sleep the thread in which it was called from.
	 *
	 * @param timer
	 *            A integer that represents the amount of seconds to sleep the
	 *            thread.
	 * 
	 */

	public void timer(int timer)
	{
		try
		{
			Thread.sleep(timer * 1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
