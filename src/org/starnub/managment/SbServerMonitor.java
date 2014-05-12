package org.starnub.managment;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.network.QueryServer;
import org.starnub.util.stream.MessageFormater;
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

public class SbServerMonitor implements Runnable {
	
	private static ResourceBundle s = StarNub.language; /* Language resource pack, used to reduce characters below */
	
	private static final int autoRestartTime = (StarNub.configVariables.get("Auto_Restart_Timer")*3600); /* Converts the auto restart timer into seconds */

	public SbServerMonitor() 
	{
	}
	
	public synchronized void run () 
	{
		serverMonitor();
	}
	
	//TODO Method clean up
	private void serverMonitor ()
	{
		
		s = new SbServerStats;

		processStartup();
		
		do 
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Server Check.");}
			switch (statusChecker())
			{
			case "statusOk" : new ThreadSleep().timer(20); sbCurrentUptime += 15; serverUptimeTemp += 15; break; // TODO Not Final //TODO Correct serverUptime format
			case "pCrash" : processCrashed(); break; // TODO Not Final
			case "sUnresp" : serverUnresponsive(); break;  // TODO Not Final
			}			
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Repeating Monitoring Loop.");}
		} 
		while (sbCurrentUptime <= autoRestartTime); //TODO Need to calculate current uptime on cycle.
		if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Auto Restarting Server.");}

		MessageFormater.msgPrint(s.getString("ssmar1")+" "+autoRestartTime+" "+s.getString("ssmar2"), 0, 0);
		// TODO Add a server broadcast for restart when network and packets added  //TODO Stats update Here autorestart, total uptime
		SbProcessManagment.sb_ProcessKill();
		serverMonitor();
	}
	
	private String statusChecker ()
	{
		if (!SbProcessManagment.sb_ProcessStatus())
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Server Check. Status: Process Crashed");}
			return "pCrash";
		}
		else if (!QueryServer.serverStatus())
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Server Check. Status: Unresponsive");}
			return "sUnresp";
		}
		else
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Server Check. Status: Ok");}
			return "statusOk";
		}
	}
	
	private void processStartup ()
	{
		MessageFormater.msgPrint(s.getString("ssm"), 0, 0);
		SbProcessManagment.sb_ProcessStart(); /* Start SB Server process */
		if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Sleeping for 2 Minutes while the SB Server starts Up.");}
		//TODO Convert to a response when server starts to start the monitor
		new ThreadSleep().timer(120); /* Sleep for 2 minutes while the server boots up.*/ //TODO Consistant server checker
	}
	
	private void processCrashed ()
	{
		MessageFormater.msgPrint(s.getString("ssmc"), 0, 1);
		//TODO setcrash counter and fix message
		MessageFormater.msgPrint(s.getString("ssmc1")+" "+serverCrashesTemp+" "+s.getString("ssmc2"), 0, 1);
		processStartup(); /* Start SB Server process */
	}
	
	private void serverUnresponsive ()
	{
		MessageFormater.msgPrint(s.getString("ssmu"), 0, 1);
		//TODO setunresponsice counter and fix message
		MessageFormater.msgPrint(s.getString("ssmu1")+" "+serverUnresponsiveTemp+" "+s.getString("ssmu2"), 0, 1);
		SbProcessManagment.sb_ProcessKill(); /* Kill the unresponsive SB server */
		processStartup(); /* Start up SB Server */
	}
}
