package org.starnub.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.starnub.StarNub;
import org.starnub.managment.SN_MessageFormater;

/*
 * Coming soon.
 */

public class SN_TaskTimer {
	
	private static String questionAnswer = "";
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private boolean reading = true;
	private static ResourceBundle s = StarNub.lang;
	
	public static String inputCall(String questionString,int length)  
	{  
		try
		{  
			new SN_TaskTimer().getInput(questionString,length);  
		}  
		catch( Exception e )
		{  
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
    					e.printStackTrace();
    				}
    			}
    			SN_ThreadTimer.startTimer(1);
    		} 
    		catch (IOException e) 
    		{
    			e.printStackTrace();
    		}
    	}
    }
	
	public SN_TaskTimer() 
	{
	}

}
