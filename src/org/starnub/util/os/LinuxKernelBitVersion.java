package org.starnub.util.os;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.starnub.util.stream.MessageFormater;

/**
 * This class will generate a Linux Kernel Bit Version.
 * 
 * @author Daniel (Underbalanced) (StarNub.org)
 * @version 1
 * 
 */

public class LinuxKernelBitVersion {

	public LinuxKernelBitVersion()
	{
	}

	/**
	 * This method will use "uname -m" to return a Linux bit version.
	 *
	 * @return A integer that represents the bit version.
	 *         <p>
	 *         1 = 32 Bit
	 *         <p>
	 *         2 = 64 Bit
	 * 
	 */

	public static int linuxKernel()
	{

		String line;
		String output = "";
		int linuxKernel = 0;

		try
		{
			Process p = Runtime.getRuntime().exec("uname -m");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((line = input.readLine()) != null)
			{
				output += (line + '\n');
			}
			input.close();
		} catch (Exception e)
		{
			linuxKernel = 0;
			MessageFormater.msgPrint(
					"Linux uname -m Java Message: " + e.getMessage(), 0, 1);
		}
		if (output.contains("x86_64"))
		{
			linuxKernel = 1;
		} else if (output.contains("i686"))
		{
			linuxKernel = 2;
		} else
		{
			linuxKernel = 2;
		}
		return linuxKernel;
	}
}
