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

	public static void snDirCheck() 
	{
		try
		{
			/* Directories we want to use */
			String[] snDirectories = new String[]
					{
					"StarNub",
					"StarNub/SN_Server Logs",
					"StarNub/Error Logs",
					"StarNub/Plugins"
					};
			
			/* Checks each directory to see if it exist */
			for(String strDirectory : snDirectories)
			{
				File directory = new File(strDirectory);
				if (!directory.exists()) 
				{
					boolean result = directory.mkdir();  
					if(result) 
					{   
						/* Prints if a directory was created or not */
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
