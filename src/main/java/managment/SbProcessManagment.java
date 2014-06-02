package managment;

import util.os.GetFilePath;

/**
 * This class will create and manage the Starbound_Server Process.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
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
			ProcessBuilder sbProcessBuild = new ProcessBuilder(
					GetFilePath.getFilePath());
			sbProcessBuild.redirectErrorStream(true);
			sbProcess = sbProcessBuild.start();
			Runnable sb_StreamInput = new SbProcessStreamManagment();
			new Thread(sb_StreamInput).start();
		} 
			catch (Exception e)
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
			sbProcess.destroy();
		} 
		catch (Exception e)
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
			return sbProcess.isAlive();
		} 
		catch (Exception e)
		{
			return false;
		}
	}
}
