package org.starnub.managment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;

/**
 * This class will direct the Starbound Subprocess Stream which must be dealt
 * with or it will I/O Block. While it has formatted output this is for partial
 * wrapper functionality. While when the wrapper is fully functional we will
 * just discard the stream as we make our own from the network stack.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1 (Incomplete)
 * 
 */

public class SbProcessStreamManagment implements Runnable {

	public SbProcessStreamManagment()
	{
	}

	public synchronized void run()
	{
		String line;
		BufferedReader input = new BufferedReader(new InputStreamReader(
				SbProcessManagment.getSbProcess().getInputStream()));

		if (StarNub.DebugFullWrapper.ON) /* Full wrapper, stream will not be used */
		{
			try
			{
				while ((line = input.readLine()) != null)
					;
				/*
				 * Discard Starbound SN_Server Process Stream. Events will be
				 * handled by Netty IO handlers and network methods.
				 */
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		/* Server manager only, console streams */
		{
			try
			{
				while ((line = input.readLine()) != null)
					/* Player Chat */
					if (line.contains("Info:  <"))
					{
						MessageFormater.msgPrint(
								line.substring(7, line.length()), 1, 2);
					} else if (line.contains("Client '")
							&& line.contains("> ("))
					{
						/* Player Names */
						String playerName = line.substring(
								(1 + line.indexOf("'")), line.lastIndexOf("'"));
						/* Player IPS */
						String playerIP = line.substring(
								(1 + line.indexOf("(")), line.lastIndexOf(":"));
						/* Player Activity (Disconnects and Connects) */
						String activity = line
								.substring((2 + line.indexOf(")")));
						/* Prints Player Connect and Disconnect to Console */
						MessageFormater.msgPrint(playerName + " has "
								+ activity + " (" + playerIP + ").", 1, 0);
					}
					/* Prints Starbound SN_Server Version to console */
					else if (line.contains("SN_Server version"))
					{
						MessageFormater.msgPrint(
								line.substring(6, line.length()) + ".", 1, 0);
					} else
					{
						/*
						 * Discard anything that did not pass the filter.
						 * 
						 * This removed unwanted errors and or Starbound console
						 * spam. You may view the Starbound SN_Server log at
						 * /starbound/Starbound_Server.log if you want to see
						 * anything not filtered about.
						 */
					}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
