package org.starnub.util.stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/*
* This class's method will push output to the console and log file.
* 
* This method will return nothing.
**/

public class MultiOutputStreamLogger {

	
	
	public void snLogger ()
	{
		try
    	{
			String timestamp = new DateTime().toString(DateTimeFormat.forPattern("dd-MMM-yy"));
			
			/* Appends the data to one file. Each day starts a new log. */
    		FileOutputStream fout= new FileOutputStream("StarNub/SN_Server Logs/"+timestamp+".log", true);
    		FileOutputStream ferr= new FileOutputStream("StarNub/Error Logs/"+timestamp+".log", true);
    		
    		MultiOutputStream multiOut= new MultiOutputStream(System.out, fout);
    		MultiOutputStream multiErr= new MultiOutputStream(System.err, ferr);
    		
    		PrintStream stdout= new PrintStream(multiOut);
    		PrintStream stderr= new PrintStream(multiErr);
    		
    		System.setOut(stdout);
    		System.setErr(stderr);
    	}
    	catch (FileNotFoundException e)
    	{
			e.printStackTrace();
    	}
	}
		
	public MultiOutputStreamLogger() 
	{
	}
}
