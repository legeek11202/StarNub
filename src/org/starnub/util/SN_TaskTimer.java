package org.starnub.util;
import java.util.Timer;  
import java.util.TimerTask;  
import java.io.*;  

import org.starnub.managment.SN_MessageFormater;

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
    			try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
