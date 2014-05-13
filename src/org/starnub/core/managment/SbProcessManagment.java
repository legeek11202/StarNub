package org.starnub.core.managment;

import org.starnub.StarNub;
import org.starnub.core.util.os.GetFilePath;

/*
* This class's methods manages the Starbound SN_Server as a subprocess
* to the StarNub wrapper.
* 
* - Returns the process for other classes to use
* - Run the Starbound_Server.exe as a subprocess
* - Destroy the Starbound_Server.exe
* - Restarts the Starbound_Server.exe
* - Checks the status of the Starbound SN_Server Subprocess 
* 
* These methods will return a boolean or nothing.
**/

public class SbProcessManagment {
	
	private static Process sbProcess;
	
    public SbProcessManagment() 
    {
    }
	
	public static Process getSbProcess() 
	{ 
		return sbProcess; 
	}
	
	public static void sb_ProcessStart() 
	{
		try 
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Process Managment: Building SB Server ProcessBuild.");}
			ProcessBuilder sbProcessBuild = new ProcessBuilder(GetFilePath.getFilePath());
			sbProcessBuild.redirectErrorStream(true);
			if (StarNub.Debug.ON) {System.out.println("Debug: Process Managment: Setting SB Server Process.");}
			sbProcess = sbProcessBuild.start();
			Runnable sb_StreamInput = new SbProcessStreamInput();
			if (StarNub.Debug.ON) {System.out.println("Debug: Process Managment: Running SB Server Process.");}
			new Thread(sb_StreamInput).start();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}  
	
	public static void sb_ProcessKill () 
	{
		try 
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Process Managment: Destroying SB Server Process.");}
			sbProcess.destroy();
			if (StarNub.Debug.ON) {System.out.println("Debug: Process Managment: SB Server Process Destroyed.");}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean sb_ProcessStatus ()
	{
		try 
		{
			if (StarNub.Debug.ON) {System.out.println("Debug: Process Managment: Checking if SB Server Process is alive.");}
			return sbProcess.isAlive();
		} 
		catch (Exception e)
		{
			return false;
		}
	}
}
