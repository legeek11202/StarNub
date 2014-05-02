package org.starnub.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.starnub.managment.SN_MessageFormater;

/*
 * Coming soon.
 */

public class SN_TaskTimer {
	
	private static String questionAnswer = "";
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private boolean reading = true;

	public static String inputCall(String questionString,int length)  
	{  
		try
		{  
			new SN_TaskTimer().getInput(questionString,length);  
		}  
		catch( Exception e )
		{  
			SN_MessageFormater.msgPrint("TaskTimer: Creation Error.", 0, 1);
			e.printStackTrace();
		}
		return questionAnswer;
	}  
      
    TimerTask task = new TimerTask()
    {  
		public void run()
    	{  
    		reading = false;
    		return;
    	}  
    };  
    
    private void getInput(String questionString, int length) throws Exception
    {  
    	Timer timer = new Timer();  
    	timer.schedule(task, length*1000);
    	System.out.println(questionString);
    	while (reading)
    	{
    		try 
    		{
    			if (in.ready())
    			{
    				try 
    				{
    					questionAnswer = in.readLine();
    					timer.cancel();
    					break;
    				} 
    				catch (IOException e) 
    				{
    					SN_MessageFormater.msgPrint("TaskTimer in.readLine() Error", 0, 1);
    					e.printStackTrace();
    				}
    			}
    			SN_ThreadTimer.startTimer(1);
    		} 
    		catch (IOException e) 
    		{
    			SN_MessageFormater.msgPrint("TaskTimer in.ready while loop Error", 0, 1);
    			e.printStackTrace();
    		}
    	}
    }
	
	public SN_TaskTimer() 
	{
	}

}
