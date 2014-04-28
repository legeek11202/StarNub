package org.starnub.util;

import org.starnub.managment.SN_MessageFormater;

public class SN_Timer {
	
	public static void startTimer(int timer)
	{
		try 
		{
			Thread.sleep(timer);
		} 
		catch (InterruptedException e) 
		{
			SN_MessageFormater.msgPrint("Timer Error: Thread Sleep Error: Java Message: "+e.getMessage(), 1);
		}
	}
	
	public SN_Timer() {
		// TODO Auto-generated constructor stub
	}

}
