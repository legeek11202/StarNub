package org.starnub.managment;

public class SbServerStats {

	/* Uptime Trackers */
	private static Date snOnlineTime = null; /* Plain SN Uptime date used in calculation */ //TODO get time
	private static Date sbOnlineTime = null; /* Plain SB Uptime date used in calculation */ //TODO get time
	
	/* All Time Tracker */ //TODO Load from file
	private static int sbAutoRestarts = 0;
	private static int sbCrashes = 0;
	private static int sbUnresponsive = 0;
	
	/* Since Wrapper Start */
	private static int sbAutoRestartsWrap = 0;
	private static int sbCrashesWrap = 0;
	private static int sbUnresponsiveWrap = 0;
	
	/* Since SB Last Autorestart Restart */
	private static int sbCrashesTemp = 0;
	private static int sbUnresponsiveTemp = 0;
	
	
	public SbServerStats() 
	{
	}
	
	private void statsLoader()
	{
		
	}
	
	private void statsSaver()
	{
		
	}
	
	public String getSnUptime() { return; } //TODO Math
	public String getSbUptime() { return; } //TODO Math
	
	public int getSbAutoRestarts() { return sbAutoRestarts; }
	public int getSbAutoRestartsWrap() { return sbAutoRestartsWrap; }
	
	public int getSbCrashes() { return sbCrashes; }
	public int getSbCrashesWrap() { return sbCrashesWrap; }
	public int getSbCrashesTemp() { return sbCrashesTemp; }
	
	public int getSbUnresponsive() { return sbUnresponsive; }
	public int getSbUnresponsiveWrap() { return sbUnresponsiveWrap; }
	public int getSbUnresponsiveTemp() { return sbUnresponsiveTemp; }
	
	public void setSnOnlineTime() { snOnlineTime = DateTime(); }
	public void setSbOnlineTime() { SbOnlineTime = DateTime(); }
	
	public void addSbAutoRestarts() { sbAutoRestarts += 1; sbAutoRestartsWrap += 1; }
	public void addSbCrashes() { sbCrashes += 1; sbCrashesWrap += 1; sbCrashesTemp += 1; }
	public void addSbUnresponsive()	{ sbUnresponsive += 1; sbUnresponsiveWrap += 1; sbUnresponsiveTemp += 1; }
	
	public void resetTempStats() { sbCrashesTemp = 0; sbUnresponsiveTemp = 0; Date sbOnlineTime = new DateTime(); }
}
