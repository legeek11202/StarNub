package org.starnub.managment;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.OS_GetFilePath;
import org.starnub.managment.SB_ProcessStreamInput;

/*
* This class's methods manages the Starbound Server as a subprocess
* to the StarNub wrapper.
* 
* - Returns the process for other classes to use
* - Run the Starbound_Server.exe as a subprocess
* - Destroy the Starbound_Server.exe
* - Restarts the Starbound_Server.exe
* - Checks the status of the Starbound Server Subprocess 
* 
* These methods will return a boolean or nothing.
**/

public class SB_ProcessManagment {
	
	private static ResourceBundle s = StarNub.lang;
	
	private static Process sbProcess;
	
	public static Process getSbProcess() 
	{ 
		return sbProcess; 
	}
	
	public static void sb_ProcessStart() 
	{
		try 
		{
			sbProcess = Runtime.getRuntime().exec(OS_GetFilePath.getFilePath());
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