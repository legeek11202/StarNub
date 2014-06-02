package util.os;

import util.stream.MessageFormater;

/**
 * This class will determine where Starbound Server is located at within the
 * Starbound directory and based on the system OS.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 * 
 */

public final class GetFilePath {

	/**
	 * This method will return a file path based on the OS Version and Bit
	 * Version.
	 * 
	 * @return A integer that represents the bit version.
	 *         <p>
	 *         Windows = win32/starbound_server.exe
	 *         <p>
	 *         Linux 32 = ./linux32/starbound_server
	 *         <p>
	 *         Linux 64 = ./linux64/starbound_server
	 * 
	 */

	public static String getFilePath()
	{
		return filepath;
	}

	private static String	filepath	= setFilepath();

	private static String setFilepath()
	{

		String systemOS = System.getProperty("os.name");
		Boolean osWindows = systemOS.startsWith("Windows");

		if (osWindows == true)
		{
			MessageFormater.msgPrint("Using Win32 Starbound_Server.exe.", 0, 0);
			return "win32/starbound_server.exe";
		} else
		{
			int linuxKernel = LinuxKernelBitVersion.linuxKernel();
			switch (linuxKernel)
			{
				case 1:
				{
					MessageFormater.msgPrint("Using Linux64 Starbound_Server.",
							0, 0);
					return "./linux64/starbound_server";
				}
				case 2:
				{
					MessageFormater.msgPrint("Using Linux32 Starbound_Server.",
							0, 0);
					return "./linux32/starbound_server";
				}
				default:
				{
					return "./linux32/starbound_server";
				}
			}
		}
	}
}
