package org.starnub.configuration;

import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_Directories_Files;
import org.starnub.configuration.SN_ConfigConfigurator;

public class SN_Configuration {

	public static void sn_CongiruationCheck ()
	{
    	SN_MessageFormater.msgPrint("Checking for StarNub Directories...", 0, 0);
    	SN_Directories_Files.snDirCheck();
    	SN_MessageFormater.msgPrint("Directory check complete.", 0, 0);
    	
    	SN_MessageFormater.msgPrint("Checking for StarNub Configuration...", 0, 0);
    	if (SN_Directories_Files.snConfigCheck())
    	{
    		SN_MessageFormater.msgPrint("Validating the StarNub Configuration...", 0, 0);
    		// TODO Config Validation Call
    		// TODO Config Load Call
    	}
    	else 
    	{
    		SN_MessageFormater.msgPrint("Creating StarNub Configuration...", 0, 0);
    		SN_ConfigConfigurator.createConfig();
    		//TODO Config Load Call
    	}
		
	}
	
	public SN_Configuration() 
	{
	}

}
