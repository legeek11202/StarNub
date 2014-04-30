package org.starnub.util;

import org.starnub.managment.SN_MessageFormater;


/*
* This class's method check to see what operating system Java is installed on.
* 
* This method will return a file path in the form of a String.
**/

public final class OS_GetFilePath {
	
	private static String win32Path = "win32/starbound_server.exe";
	private static String linux32Path = "./linux32/starbound_server";
	private static String linux64Path = "./linux64/starbound_server";
	
	public static String getFilePath ()
	{	
		
    	String systemOS = System.getProperty("os.name");
    	Boolean osWindows = systemOS.startsWith("Windows");
    	
    	if (osWindows == true)
    	{
    			SN_MessageFormater.msgPrint("Using Win32 Starbound_Server.exe.", 0, 0);
    			return win32Path;
    	} 
    	else 
    	{
    		int linuxKernel = OS_GetLinuxKernel.linuxKernel();
 
    		switch (linuxKernel) 
    		{
    		case 1: 
    		{
				SN_MessageFormater.msgPrint("Using Linux64 Starbound_Server.", 0, 0);
    			return linux64Path;
    		}
    		case 2: 
    		{
    			SN_MessageFormater.msgPrint("Using Linux32 Starbound_Server.", 0, 0);
    			return linux32Path;
    		}
    		default:
    		{
    			return linux32Path;
    		}
    		}
    	}
	}	
	public OS_GetFilePath() 
	{
	}
}
