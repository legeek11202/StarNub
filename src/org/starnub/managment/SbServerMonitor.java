package org.starnub.managment;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ThreadSleep;

/**
 * This class will monitors the Starbound Server and 
 * tracks statistics about its up time, restarts, ect.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 26 May 2014
 */

public class SbServerMonitor implements Runnable {
	
	private static ResourceBundle lang = StarNub.language; /* Language resource pack, used to reduce characters below */

	private static final int autoRestartTime = StarNub.configVariables.get("Auto_Restart_Timer"); /* Auto restart timer in hours */
	private static int snPort = StarNub.configVariables.get("StarNub_Port");		
	public static SbServerStats s = new SbServerStats();
	
	public SbServerMonitor() 
	{
	}
	
	public synchronized void run () 
	{
		s.setSnOnlineTime(); /* Set the time SN came online */
		serverMonitor();
	}
	
	private void serverMonitor ()
	{
		
		s.resetTempStats(); /* Refresh Temporary Stat's */
		processStartup();
		/* Create a restart time stamp (Hours = Restart configuration variable (((Hours * Minutes)*Seconds)*Milliseconds) ) */
		long futureAutoRestartTime = s.getSbOnlineTime()+(((autoRestartTime*60)*60)*1000);

		do 
		{
			if (StarNub.Debug.ON){System.out.println("Debug: Server Monitor: Server Check. SB uptime in milliseconds "+s.getSbUptime()+".");}
			switch (statusChecker())
			{
			case "statusOk" : new ThreadSleep().timer(30); break;
			case "pCrash" : processCrashed(); break;
			case "sUnresp" : serverUnresponsive(); break;  
			}			
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Repeating Monitoring Loop.");}
		} 
		while (System.currentTimeMillis() <= futureAutoRestartTime); //TODO - Need to calculate current up time on cycle.
		if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Auto Restarting Server.");}
		s.addSbAutoRestarts(); /* Decrement auto restart counter */
		MessageFormater.msgPrint(lang.getString("ssmar1")+" "+autoRestartTime+" "+lang.getString("ssmar2"), 0, 0);
		SbProcessManagment.sb_ProcessKill(); /* Kill the Process */
		serverMonitor(); /* Restart this method to clear the stat's */
	}
	
	private String statusChecker ()
	{
		if (!SbProcessManagment.sb_ProcessStatus())
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Server Monitor: Server Check. Status: Process Crashed");}
			return "pCrash";
		}
		else if (!SbQueryProcessor.serverStatus(1))
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
		MessageFormater.msgPrint(lang.getString("ssm"), 0, 0);
		SbProcessManagment.sb_ProcessStart(); /* Start SB Server process */
		while (!SbQueryProcessor.serverStatus(2)) {} /* We will check the server until it is online. */
		MessageFormater.msgPrint(lang.getString("sta")+" "+snPort+".", 0, 0);
		s.setSbOnlineTime(); /* Set the time SB came online */
	}
	
	private void processCrashed ()
	{
		s.addSbCrashes(); /* Decrement crash counter */
		MessageFormater.msgPrint(lang.getString("ssmc"), 0, 1);
		MessageFormater.msgPrint(lang.getString("ssmc1")+" "+s.getSbCrashesTemp()+" "+lang.getString("ssmc2"), 0, 1);
		processStartup(); /* Start SB Server process */
	}
	
	private void serverUnresponsive ()
	{
		s.addSbUnresponsive(); /* Decrement unresponsive counter */
		MessageFormater.msgPrint(lang.getString("ssmu"), 0, 1);
		MessageFormater.msgPrint(lang.getString("ssmu1")+" "+s.getSbUnresponsiveTemp()+" "+lang.getString("ssmu2"), 0, 1);
		SbProcessManagment.sb_ProcessKill(); /* Kill the unresponsive SB server */
		processStartup(); /* Start up SB Server */
	}
}
