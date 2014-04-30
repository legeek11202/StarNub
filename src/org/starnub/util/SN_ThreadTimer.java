package org.starnub.util;

import org.starnub.managment.SN_MessageFormater;

public class SN_ThreadTimer {
	
	public static void startTimer(int timer)
	{
		try 
		{
			Thread.sleep(timer*1000);
		} 
		catch (InterruptedException e) 
		{
			SN_MessageFormater.msgPrint("Timer Error: Thread Sleep Error: Java Message: "+e.getMessage(), 0, 1);
		}
	}
	
	public SN_ThreadTimer() {
		// TODO Auto-generated constructor stub
	}
}
