package util.timers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Coming Soon.
 * 
 * @author Daniel Merwin(Underbalanced)
 * @version 1.0, 17 May 2014 (Incomplete)
 *              
 */

public class ConsoleInput_Timer {
	
	private static String questionAnswer = "";
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private boolean reading = true;
	
	public ConsoleInput_Timer() 
	{
	}
	
	public static String inputCall(String questionString,int length)  
	{  
		try
		{  
			new ConsoleInput_Timer().getInput(questionString,length);  
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
    			new ThreadSleep().timer(1);
    		} 
    		catch (IOException e) 
    		{
    			e.printStackTrace();
    		}
    	}
    }
}
