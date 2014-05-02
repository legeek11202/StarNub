package org.starnub.util;

import java.io.File;

import org.starnub.managment.SN_MessageFormater;

/*
* This class's method will check to make sure the directories 
* are created and if not create them.
* 
* This method will return nothing.
**/

public class SN_Directories {

	// Directory Checker

	public static void snDirCheck() 
	{
		try
		{
			String[] snDirectories = new String[]
					{
					"StarNub",
					"StarNub/Server Logs",
					"StarNub/Error Logs",
					"StarNub/Plugins"
					};
			for(String strDirectory : snDirectories)
			{
				File directory = new File(strDirectory);
				if (!directory.exists()) 
				{
					boolean result = directory.mkdir();  
					if(result) 
					{    
						SN_MessageFormater.msgPrint(directory+" directory created.", 0, 0);
					}
				}
			}
		}
		catch (Exception e)
		{
			SN_MessageFormater.msgPrint("Directory creation: Java Error Message: "+e.getMessage(), 0, 1);
		}
	}
	
	public SN_Directories() 
	{
	}
}
