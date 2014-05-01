package org.starnub.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.starnub.managment.SN_MessageFormater;

/*
 * This class is not function correctly. May not be possible to time user input.
 */

public class SN_TaskTimer {
	
	private static String questionAnswer = "";
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static String inputCall(String questionString,int length)  
	{  
		try
		{  
			new SN_TaskTimer().getInput(questionString,length);  
		}  
		catch( Exception e )
		{  
			SN_MessageFormater.msgPrint("TaskTimer: Creation Error."+e.getMessage(), 0, 1);
		}
		return questionAnswer;
	}  
      
    TimerTask task = new TimerTask()
    {  
    	public void run()
    	{  
    		if(questionAnswer.equals(""))
    		{   
    			System.out.println("test");

    			return;
    		} 
    	}  
    };  
    		  
    private void getInput(String questionString, int length) throws Exception
    {  
    	Timer timer = new Timer();  
    	timer.schedule(task, length*1000);  
    	System.out.println(questionString);   
    	questionAnswer = in.readLine();  
    	timer.cancel();  
    }  
	
	public SN_TaskTimer() 
	{
	}

}
