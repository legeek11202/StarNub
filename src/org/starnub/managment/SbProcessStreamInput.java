package org.starnub.managment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;

/*
* Coming Soon
* TODO OPTION FOR WHEN USING NETWORKING
* This class is used to filter and format the Starbound SN_Server Console Stream into Java
* and StarNub log files.
* 
*/

public class SbProcessStreamInput implements Runnable {
	
	private static ResourceBundle lang = StarNub.language;

	public synchronized void run()
	{  
			String line;
			String c = lang.getString("spsic");
			String dc = lang.getString("spsid");
			BufferedReader input = new BufferedReader(new InputStreamReader(SbProcessManagment.getSbProcess().getInputStream()));
			// TODO Import Variable
			Boolean fullwrapper = false;
			
			if (fullwrapper)
			{
				try 
				{
					while ((line = input.readLine()) != null); 
					/* 
					 * Discard Starbound SN_Server Process Stream. 
					 * Events will be handled by Netty IO handlers 
					 * and network methods.
					 * */	
				}
				catch (IOException e) 
				{
						e.printStackTrace();
				}
			}
			else if (!fullwrapper) 
			{	
				try 
				{
					while ((line = input.readLine()) != null) 
						/* Player Chat */
						if (line.contains("Info:  <"))
						{
							MessageFormater.msgPrint(line.substring(7, line.length()), 1, 2);
						}
						else if (line.contains("Client '") && line.contains("> ("))
						{
							/* Player Names */
							String playerName = line.substring((1+line.indexOf("'")),line.lastIndexOf("'"));
							/* Player IPS */
							String playerIP = line.substring((1+line.indexOf("(")),line.lastIndexOf(":"));
							/* Player Activity (Disconnects and Connects) */
							String activity = line.substring((2+line.indexOf(")")));
							/* Inserts player into Player Name/IP HashMap */
							//TODO Re-enable Disabled for build network testing
//							if (activity.equals("connected"))
//							{
//								StarNub.playersOnline.put(playerIP, playerName);
//								activity = c;
//							}
//							/* Removes a player from Player Name/IP HashMap */
//							else if (activity.equals("disconnected"))
//							{
//								StarNub.playersOnline.remove(playerIP);
//								activity = dc;
//							}
//							else
//							{
//								/* Error with HasMap message */
//								SN_MessageFormater.msgPrint(lang.getString("sb.psi.1"), 0, 1);
//							}	
							/* Prints Player Connect and Disconnect to Console */
							MessageFormater.msgPrint(playerName+" has "+activity+" ("+playerIP+").", 1, 0);
						} 
						/* Prints Starbound SN_Server Version to console */
						else if (line.contains("SN_Server version"))
						{
							MessageFormater.msgPrint(line.substring(6, line.length())+".", 1, 0);
						} 
						/* Prints when the Starbound SN_Server is ready to accept new connections */
						else if (line.contains("TcpServer"))
						{
							MessageFormater.msgPrint(lang.getString("spsi")+" "+StarNub.configVariables.get("StarNub_Port")+".", 1, 0);
						} 
						else
						{
							/* 
							 * Discard anything that did not pass the filter. 
							 * 
							 * This removed unwanted errors and or Starbound 
							 * console spam. You may view the Starbound SN_Server
							 * log at /starbound/Starbound_Server.log if you 
							 * want to see anything not filtered about.
							 * */
						}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
	}
	
	public SbProcessStreamInput() 
	{
	}
}

