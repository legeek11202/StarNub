package org.starnub.managment;

public class SbServerStats {

	/* Uptime Trackers */
	static Date snOnlineTime = null; /* Plain SN Uptime date used in calculation */ //TODO get time
	static Date sbOnlineTime = null; /* Plain SB Uptime date used in calculation */ //TODO get time
	
	/* All Time Tracker */ //TODO Load from file
	static int sbAutoRestarts = 0;
	static int sbCrashes = 0;
	static int sbUnresponsive = 0;
	
	/* Since Wrapper Start */
	static int sbAutoRestartsWrap = 0;
	static int sbCrashesWrap = 0;
	static int sbUnresponsiveWrap = 0;
	
	/* Since SB Last Autorestart Restart */
	static int sbCrashesTemp = 0;
	static int sbUnresponsiveTemp = 0;
	
	
	public SbServerStats() 
	{
	}
	
	private void statsLoader()
	{
		
	}
	
	private void statsSaver()
	{
		
	}
	
	private String getSnUptime()
	{
	/*Do math here to get current uptime in days, hours, minutes, seconds */
	}
	
	private String getSbUptime()
	{
	/*Do math here to get current uptime in days, hours, minutes, seconds */
	
	}
	
	public void setSbAutoRestarts()
	{
		sbAutoRestarts += 1;
		sbAutoRestartsWrap += 1;
	}
	
	public void setSbCrashes()
	{
		sbCrashes += 1;
		sbCrashesWrap += 1;
		sbCrashesTemp += 1;
	}
	
	public void setSbUnresponsive()
	{
		sbUnresponsive += 1;
		sbUnresponsiveWrap += 1;
		sbUnresponsiveTemp += 1;	
	}
	
	public void setSnOnlineTime()
	{
		Date snOnlineTime = new DateTime();
	}
	
	public void setSbOnlineTime()
	{
		Date sbOnlineTime = new DateTime();
	}
		
	public void resetTempStats()
	{
		sbCrashesTemp = 0;
	 	sbUnresponsiveTemp = 0;
	 	Date sbOnlineTime = new DateTime();
	}
	
}
