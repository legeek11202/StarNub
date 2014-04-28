package org.starnub.managment;

import org.starnub.network.SB_Query;
import org.starnub.util.SN_Timer;

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
	
	// TODO push variables to menu, add all time counters
	/* serverUptime is in minutes */
	static int serverUptime = 0;
	static int serverRestarts = 0;
	static int serverWrapperCrashes = 0;
	static int serverWrapperUnresponsive = 0;
	
	public void run () 
	{
		int sbRestartTimer = 0;
		int serverCrashes = 0;
		int serverUnresponsive = 0;
		
		// TODO: Configurable variable in hours
		final int restartTimer = 24;
		
		SN_MessageFormater.msgPrint("Starting the Starbound Server.", 0);
		SB_ProcessManagment.sb_ProcessStart();
		// Sleep for 2 minutes while the server boots up.
		SN_Timer.startTimer(120000);
		
		do 
		{
			boolean sbStatus = SB_ProcessManagment.sb_ProcessStatus();
			boolean sbNetResponse = SB_Query.getServerResponse();
			
			if (!sbStatus)
			{
				SN_MessageFormater.msgPrint("It appears the Starbound server crashed, Starting the Starbound server...", 1);
				SB_ProcessManagment.sb_ProcessStart();
				/* Add a crash to the crash trackers */
				serverCrashes += 1; serverWrapperCrashes += 1;
				SN_MessageFormater.msgPrint("Your server has crashed "+serverCrashes+" time(s) since the last autorestart.", 1);
				// Sleep for 2 minutes while the server boots up.
				
			}
			else if (!sbNetResponse)
			{
				SN_MessageFormater.msgPrint("It appears the Starbound server is unresponsive, Starting the Starbound server...", 1);
				SB_ProcessManagment.sb_ProcessRestart();
				/* Add a unresponsive to the unresponsive trackers */
				serverUnresponsive += 1; serverWrapperUnresponsive += 1;
				// Sleep for 2 minutes while the server boots up.
				SN_Timer.startTimer(120000);
				SN_MessageFormater.msgPrint("Your server been unresponsive "+serverUnresponsive+" time(s) since the last autorestart.", 1);
			}
			SN_Timer.startTimer(15000);
			sbRestartTimer += 15; serverUptime += (15);
			//TODO Correct serverUptime format
		} 
		while (sbRestartTimer <= (restartTimer*(60*60)));
		
		/* Add a restart to the restart tracker */
		serverRestarts += 1; 
		SN_MessageFormater.msgPrint("It has been "+(restartTimer/60)+" hours since the last restart. Auto restarting server as per configuration.", 0);
		// TODO Add a server broadcast for restart when network and packets added
		SB_ProcessManagment.sb_ProcessKill();
		run();	 
	}
	
	public SB_ServerMonitor() 
	{
	}
}