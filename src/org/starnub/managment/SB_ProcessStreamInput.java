package org.starnub.managment;

/*
* Coming Soon
* 
* 
* 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

// Multi conditional statments one to take user name and ip out and store in array
// this array will be used for ip/username banning
// TODO Future filter on or off option

public class SB_ProcessStreamInput implements Runnable {
	
	
	
	public void run()
		{  
		Process sbProcess = SB_ProcessManagment.getSbProcess();
			String line;
	      
			BufferedReader input = new BufferedReader(new InputStreamReader(sbProcess.getInputStream()));
			
			try 
			{
				while ((line = input.readLine()) != null) 
					if (line.contains("Info:  <"))
					{
						String currentTime = new DateTime().toString(DateTimeFormat.forPattern("[HH:mm:ss]"));
						System.out.println(currentTime+"[Starbound Chat]: "+line);
					}
					else if (line.contains("Client '") && line.contains("> ("))
					{
						// TODO Needs additional Formating and better filter
						String currentTime = new DateTime().toString(DateTimeFormat.forPattern("[HH:mm:ss]"));
						System.out.println(currentTime+"[Starbound Info]: "+line);
					} 
					else if (line.contains("Server version"))
					{
						String currentTime = new DateTime().toString(DateTimeFormat.forPattern("[HH:mm:ss]"));
						System.out.println(currentTime+"[Starbound Info]: "+line);
					} 
					else if (line.contains("TcpServer"))
					{
						// TODO Insert port variable
						String currentTime = new DateTime().toString(DateTimeFormat.forPattern("[HH:mm:ss]"));
						System.out.println(currentTime+"[Starbound Info]: Starbound server online and excepting connections.");
					} 
					else
					{
				      // Do Nothing. We do not want Console Spam. Starbound Server Log will contain warning or errors.
					}
			} 
			catch (IOException e) 
			{
				SN_MessageFormater.msgPrint("Starbound process stream issues: Java Message: "+e.getMessage(), 1);
				e.printStackTrace();
			}
	      }
	public SB_ProcessStreamInput() 
	{
	}
}

