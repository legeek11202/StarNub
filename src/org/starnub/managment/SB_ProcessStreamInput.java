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
import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.stream.SN_MessageFormater;

public class SB_ProcessStreamInput implements Runnable {
	
	private static ResourceBundle s = StarNub.language;

	public synchronized void run()
		{  
			String line;
			String c = s.getString("spsic");
			String dc = s.getString("spsid");
			BufferedReader input = new BufferedReader(new InputStreamReader(SB_ProcessManagment.getSbProcess().getInputStream()));
			
			try 
			{
				while ((line = input.readLine()) != null) 
					if (line.contains("Info:  <"))
					{
						SN_MessageFormater.msgPrint(line.substring(7, line.length()), 1, 2);
					}
					else if (line.contains("Client '") && line.contains("> ("))
					{
						String playerName = line.substring((1+line.indexOf("'")),line.lastIndexOf("'"));
						String playerIP = line.substring((1+line.indexOf("(")),line.lastIndexOf(":"));
						String activity = line.substring((2+line.indexOf(")")));
						if (activity.equals("connected"))
						{
							StarNub.playersOnline.put(playerIP, playerName);
							activity = c;
						}
						else if (activity.equals("disconnected"))
						{
							StarNub.playersOnline.remove(playerIP);
							activity = dc;
						}
						else
						{
							SN_MessageFormater.msgPrint(s.getString("sb.psi.1"), 0, 1);
						}
						SN_MessageFormater.msgPrint(playerName+" has "+activity+" ("+playerIP+").", 1, 0);
					} 
					else if (line.contains("Server version"))
					{
						SN_MessageFormater.msgPrint(line.substring(6, line.length())+".", 1, 0);
					} 
					else if (line.contains("TcpServer"))
					{
						SN_MessageFormater.msgPrint(s.getString("spsi")+" "+StarNub.configVariables.get("StarNub_Port")+".", 1, 0);
					} 
					else
					{
				      // Do Nothing. We do not want Console spam. Starbound Server Log will contain warning or errors.
					}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	      }
	public SB_ProcessStreamInput() 
	{
	}
}

