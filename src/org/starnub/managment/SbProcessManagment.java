package org.starnub.managment;

import org.starnub.StarNub;
import org.starnub.util.os.GetFilePath;

/**
 * This class will create and manage the Starbound_Server Process.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 * 
 */

public class SbProcessManagment {

	private static Process	sbProcess;

	public SbProcessManagment()
	{
	}

	/**
	 * This method will return a file path based on the OS Version and Bit
	 * Version.
	 * 
	 * @return A Process that represents the Starbound Server Subprocess of this
	 *         Java VM.
	 * 
	 */

	public static Process getSbProcess()
	{
		return sbProcess;
	}

	/**
	 * This method will build and start the Starbound Process and Starbound
	 * Stream Managers.
	 */

	public static void sb_ProcessStart()
	{
		try
		{
			if (StarNub.Debug.ON)
			{
				System.out
						.println("Debug: Process Managment: Building SB Server ProcessBuild.");
			}
			ProcessBuilder sbProcessBuild = new ProcessBuilder(
					GetFilePath.getFilePath());
			sbProcessBuild.redirectErrorStream(true);
			if (StarNub.Debug.ON)
			{
				System.out
						.println("Debug: Process Managment: Setting up SB Server Process.");
			}
			sbProcess = sbProcessBuild.start();
			Runnable sb_StreamInput = new SbProcessStreamManagment();
			if (StarNub.Debug.ON)
			{
				System.out
						.println("Debug: Process Managment: Running SB Server Process.");
			}
			new Thread(sb_StreamInput).start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method will kill the running Starbound Server Subprocess within this
	 * Java VM.
	 */

	public static void sb_ProcessKill()
	{
		try
		{
			if (StarNub.Debug.ON)
			{
				System.out
						.println("Debug: Process Managment: Destroying SB Server Process.");
			}
			sbProcess.destroy();
			if (StarNub.Debug.ON)
			{
				System.out
						.println("Debug: Process Managment: SB Server Process Destroyed.");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method will return a boolean whether the Starbound Server Subprocess
	 * is still alive.
	 * 
	 * @return <p>
	 *         True - Alive
	 *         <p>
	 *         False - No process
	 * 
	 */

	public static boolean sb_ProcessStatus()
	{
		try
		{
			if (StarNub.Debug.ON)
			{
				System.out
						.println("Debug: Process Managment: Checking if SB Server Process is alive.");
			}
			return sbProcess.isAlive();
		} catch (Exception e)
		{
			return false;
		}
	}
}
