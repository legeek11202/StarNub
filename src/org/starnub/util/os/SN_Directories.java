package org.starnub.util.os;

import java.io.File;

import org.starnub.StarNub;
import org.starnub.util.stream.SN_MessageFormater;

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
					"StarNub/SN_Server Logs",
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
						SN_MessageFormater.msgPrint(directory+StarNub.language.getString("sndc"), 0, 0);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public SN_Directories() 
	{
	}
}
