package org.starnub.util.os;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.starnub.util.stream.MessageFormater;

/*
* This class's method will check to see the Linux Kernel bit version.
* 
* This method will return a integer.
**/

public class LinuxKernelBitVersion {

		public static int linuxKernel() 
		{
			
			String line;
			String output = "";
			int linuxKernel = 0;

			try 
			{
				Process p = Runtime.getRuntime().exec("uname -m");
				BufferedReader input = new BufferedReader
						(new InputStreamReader(p.getInputStream()));
				while ((line = input.readLine()) != null) 
				{
					output += (line + '\n');
				}
				input.close();
			}
			catch (Exception e) 
			{
				linuxKernel = 0;
				MessageFormater.msgPrint("Linux uname -m Java Message: "+e.getMessage(), 0, 1);
			} 
			if (output.contains("x86_64"))
			{
				linuxKernel = 1;
			} 
			else if (output.contains("i686"))
			{
				linuxKernel = 2;				  
			} 
			else 
			{
				linuxKernel = 2;  
			}
			return linuxKernel;
		}

		public LinuxKernelBitVersion() 
		{
		}

}
