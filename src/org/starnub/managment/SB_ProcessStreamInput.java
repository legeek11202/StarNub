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

// TODO Multi conditional statments one to take user name and ip out and store in array
// this array will be used for ip/username banning

public class SB_ProcessStreamInput implements Runnable {

	public synchronized void run()
		{  
			String line;
			
			BufferedReader input = new BufferedReader(new InputStreamReader(SB_ProcessManagment.getSbProcess().getInputStream()));
			
			//TODO Configuration options
			//Menu Default, All console, filtered
			try 
			{
				while ((line = input.readLine()) != null) 
					if (line.contains("Info:  <"))
					{
						SN_MessageFormater.msgPrint(line, 1, 2);
					}
					else if (line.contains("Client '") && line.contains("> ("))
					{
						SN_MessageFormater.msgPrint(line, 1, 0);
					} 
					else if (line.contains("Server version"))
					{
						SN_MessageFormater.msgPrint(line, 1, 0);
					} 
					else if (line.contains("TcpServer"))
					{
						// TODO Insert port variable
						SN_MessageFormater.msgPrint("Starbound server online and excepting connections.", 1, 0);
					} 
					else
					{
				      // Do Nothing. We do not want Console spam. Starbound Server Log will contain warning or errors.
					}
			} 
			catch (IOException e) 
			{
				SN_MessageFormater.msgPrint("Starbound process stream issues: Java Message: "+e.getMessage(), 0, 1);
				e.printStackTrace();
			}
	      }
	public SB_ProcessStreamInput() 
	{
	}
}

