package org.starnub.managment;

import org.starnub.util.os.SN_GetFilePath;

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

public class SB_ProcessManagment {
	
	private static Process sbProcess;
	
	public static Process getSbProcess() 
	{ 
		return sbProcess; 
	}
	
	public static void sb_ProcessStart() 
	{
		try 
		{
			ProcessBuilder sbProcessBuild = new ProcessBuilder(SN_GetFilePath.getFilePath());
			sbProcessBuild.redirectErrorStream(true);
			sbProcess = sbProcessBuild.start();
			Runnable sb_StreamInput = new SB_ProcessStreamInput();
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
			sbProcess.destroy();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void sb_ProcessRestart()
	{
		sb_ProcessKill();
		sb_ProcessStart();
	}
	
	public static boolean sb_ProcessStatus ()
	{
		try 
		{
			return sbProcess.isAlive();
		} 
		catch (Exception e)
		{
			return false;
		}
	}
		
    public SB_ProcessManagment() 
    {
    }
}