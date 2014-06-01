package org.starnub.managment;

import org.starnub.StarNub;
import org.starnub.network.QueryServer;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/**
 * This class will manage Queryies to the Starbound Server.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 26 May 2014
 */

public class SbQueryProcessor {

	private static boolean status;
	private static int type; /* 1 = Regular Query, 2 = Server Startup */
	private static int	txAttemps = 0; /* Query Attempts */

	public SbQueryProcessor()
	{
	}

	public static void setStatus(boolean status) {
		SbQueryProcessor.status = status;
	}

	public static boolean serverStatus(int i)
	{
		type = i;
		queryProcessor();
		return status;
	}
	
	private static void queryProcessor()
	{
		while (!status)
		{
			QueryServer.serverStatus();
			if (!status && txAttemps < 12 && type == 1 )
			{
				txAttemps += 1; /* Decrement tries */
				new ThreadSleep().timer(10); /* Wait until retry */
				MessageFormater.msgPrint(StarNub.language.getString("sb.q.1") + " ("+ txAttemps + "/12).", 0, 1);	
			}
			if (!status && type == 2)
			{
				new ThreadSleep().timer(5); /* Shorter wait due to start up query */
				MessageFormater.msgPrint(StarNub.language.getString("sb.q.2"), 0, 0);
			}
		}
	}	
}
