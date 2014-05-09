package org.starnub.managment;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.network.SB_Query;
import org.starnub.util.stream.SN_MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/*
* This class's method manages Starbound SN_Server Monitoring
* 
* - Monitor the Process and Network Reply (Coming Soon)
* if Process is not alive and/or network does not respond 
* will reboot the server.
* 
* This method returns nothing.
* */

public class SB_ServerMonitor implements Runnable {
	
	private static ResourceBundle s = StarNub.language;
	
	int serverCrashesTemp = 0;
	int serverUnresponsiveTemp = 0;
	
	
	public synchronized void run () 
	{
		// TODO: Configurable variable in hours
		//3600 is 60 Minutes x 60 Seconds.
		final int autoRestartTimer = (24*3600);
		int sbRestartTimer = StarNub.configVariables.get("Auto_Restart_Timer");
		
		/* Temporary */
		/* Since SN_Server Last Restart */

		@SuppressWarnings("unused")
		int serverUptimeTemp = 0;	
		
		SN_MessageFormater.msgPrint(s.getString("ssm"), 0, 0);
		SB_ProcessManagment.sb_ProcessStart();
		/* Sleep for 2 minutes while the server boots up.*/
		new ThreadSleep().timer(120);
		
		do 
		{
			
			if (!SB_ProcessManagment.sb_ProcessStatus())
			{
				SN_MessageFormater.msgPrint(s.getString("ssmc"), 0, 1);
				/* Add a crash to the crash trackers */
				serverCrashesTemp += 1;
				SN_MessageFormater.msgPrint(s.getString("ssmc1")+" "+serverCrashesTemp+" "+s.getString("ssmc2"), 0, 1);
				run ();
			}
			else if (!SB_Query.getServerResponse())
			{
				SN_MessageFormater.msgPrint(s.getString("ssmu"), 0, 1);
				SB_ProcessManagment.sb_ProcessRestart();
				/* Add a unresponsive to the unresponsive trackers */
				serverUnresponsiveTemp += 1;
				SN_MessageFormater.msgPrint(s.getString("ssmu1")+" "+serverUnresponsiveTemp+" "+s.getString("ssmu2"), 0, 1);
				// Sleep for 2 minutes while the server boots up.
				new ThreadSleep().timer(120);
			}
			new ThreadSleep().timer(15);
			sbRestartTimer += 15; serverUptimeTemp += 15;
			//TODO Correct serverUptime format
		} 
		while (sbRestartTimer <= autoRestartTimer);
		
		/* Add a restart to the restart tracker */
		// TODO
		SN_MessageFormater.msgPrint(s.getString("ssmar1")+" "+autoRestartTimer+" "+s.getString("ssmar2"), 0, 0);
		// TODO Add a server broadcast for restart when network and packets added
		SB_ProcessManagment.sb_ProcessKill();
		run();	 
	}
	
	public SB_ServerMonitor() 
	{
	}
}