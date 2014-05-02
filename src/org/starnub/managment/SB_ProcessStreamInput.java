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

import org.starnub.StarNub;

public class SB_ProcessStreamInput implements Runnable {

	public synchronized void run()
		{  
			String line;
			
			BufferedReader input = new BufferedReader(new InputStreamReader(SB_ProcessManagment.getSbProcess().getInputStream()));
			
			try 
			{
				while ((line = input.readLine()) != null) 
					if (line.contains("Info:  <"))
					{
						SN_MessageFormater.msgPrint(line.substring(6, line.length()), 1, 2);
					}
					else if (line.contains("Client '") && line.contains("> ("))
					{
						String playerName = line.substring((1+line.indexOf("'")),line.lastIndexOf("'"));
						String playerIP = line.substring((1+line.indexOf("(")),line.lastIndexOf(":"));
						String activity = line.substring((2+line.indexOf(")")));
						if (activity.equals("connected"))
						{
							StarNub.playersOnline.put(playerIP, playerName);
						}
						else if (activity.equals("disconnected"))
						{
							StarNub.playersOnline.remove(playerIP);
						}
						else
						{
							SN_MessageFormater.msgPrint("StreamInput: Error with inserting or removing from array", 0, 1);
						}
						SN_MessageFormater.msgPrint(playerName+" has "+activity+" ("+playerIP+").", 1, 0);
					} 
					else if (line.contains("Server version"))
					{
						SN_MessageFormater.msgPrint(line.substring(6, line.length()), 1, 0);
					} 
					else if (line.contains("TcpServer"))
					{
						SN_MessageFormater.msgPrint("Starbound server online. Players may now connect to port "+StarNub.configVariables.get("StarNub_Port")+".", 1, 0);
					} 
					else
					{
				      // Do Nothing. We do not want Console spam. Starbound Server Log will contain warning or errors.
					}
			} 
			catch (IOException e) 
			{
				SN_MessageFormater.msgPrint("Starbound process stream issues.", 0, 1);
				e.printStackTrace();
			}
	      }
	public SB_ProcessStreamInput() 
	{
	}
}

