package org.starnub.managment;

import org.starnub.network.SB_Query;
import org.starnub.util.SN_ThreadTimer;

/*
* This class's method manages Starbound Server Monitoring
* 
* - Monitor the Process and Network Reply (Coming Soon)
* if Process is not alive and/or network does not respond 
* will reboot the server.
* 
* This method returns nothing.
* */

public class SB_ServerMonitor implements Runnable {
	
	public synchronized void run () 
	{
		// TODO: Configurable variable in hours
		//3600 is 60 Minutes x 60 Seconds.
		final int autoRestartTimer = (24*3600);
		int sbRestartTimer = 0;
		
		/* Temporary */
		/* Since Server Last Restart */
		int serverCrashesTemp = 0;
		int serverUnresponsiveTemp = 0;
		@SuppressWarnings("unused")
		int serverUptimeTemp = 0;	
		
		SN_MessageFormater.msgPrint("Starting the Starbound Server.", 0, 0);
		SB_ProcessManagment.sb_ProcessStart();
		/* Sleep for 2 minutes while the server boots up.*/
		SN_ThreadTimer.startTimer(120);
		
		do 
		{
			if (!SB_ProcessManagment.sb_ProcessStatus())
			{
				SN_MessageFormater.msgPrint("It appears the Starbound server crashed.", 0, 1);
				/* Add a crash to the crash trackers */
				serverCrashesTemp += 1;
				SN_MessageFormater.msgPrint("Your server has crashed "+serverCrashesTemp+" time(s) since the last auto restart.", 0, 1);
				run ();
			}
			else if (!SB_Query.getServerResponse())
			{
				SN_MessageFormater.msgPrint("It appears the Starbound server is unresponsive, Starting the Starbound server...", 0, 1);
				SB_ProcessManagment.sb_ProcessRestart();
				/* Add a unresponsive to the unresponsive trackers */
				serverUnresponsiveTemp += 1;
				SN_MessageFormater.msgPrint("Your server been unresponsive "+serverUnresponsiveTemp+" time(s) since the last auto restart.", 0, 1);
				// Sleep for 2 minutes while the server boots up.
				SN_ThreadTimer.startTimer(120);
			}
			SN_ThreadTimer.startTimer(15);
			sbRestartTimer += 15; serverUptimeTemp += 15;
			//TODO Correct serverUptime format
		} 
		while (sbRestartTimer <= autoRestartTimer);
		
		/* Add a restart to the restart tracker */
		// TODO
		SN_MessageFormater.msgPrint("It has been "+autoRestartTimer+" hours since the last restart. Auto restarting server as per configuration.", 0, 0);
		// TODO Add a server broadcast for restart when network and packets added
		SB_ProcessManagment.sb_ProcessKill();
		run();	 
	}
	
	public SB_ServerMonitor() 
	{
	}
}