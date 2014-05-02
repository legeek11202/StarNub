package org.starnub.configuration;

import org.starnub.StarNub;
import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_Directories;
import org.starnub.configuration.SN_ConfigConfigurator;

public class SN_Configuration {

	public static void sn_CongiruationCheck ()
	{
    	SN_MessageFormater.msgPrint("Checking for StarNub Directories...", 0, 0);
    	SN_Directories.snDirCheck();
    	SN_MessageFormater.msgPrint("Directory check complete.", 0, 0);
		SN_MessageFormater.msgPrint("Validating the StarNub Configuration...", 0, 0);
		SN_ConfigConfigurator.configurationValidation();
		SN_MessageFormater.msgPrint("\n\nUsing variables found in the StarNub.config \n"+StarNub.configVariables.toString()+"\n", 0, 0);
		
	}
	
	public SN_Configuration() 
	{
	}
}
