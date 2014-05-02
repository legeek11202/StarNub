package org.starnub.configuration;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.managment.SN_MessageFormater;
import org.starnub.util.SN_Directories;
import org.starnub.configuration.SN_ConfigConfigurator;

public class SN_Configuration {
	
	private static ResourceBundle s = StarNub.lang;

	public static void snConfigurationCheck ()
	{
    	SN_MessageFormater.msgPrint(s.getString("dc"), 0, 0);
    	SN_Directories.snDirCheck();
    	SN_MessageFormater.msgPrint(s.getString("dcc"), 0, 0);
		SN_MessageFormater.msgPrint(s.getString("dvc"), 0, 0);
		SN_ConfigConfigurator.configurationValidation();
		SN_MessageFormater.msgPrint("\n\n"+s.getString("uvf")+"\n"+StarNub.configVariables.toString()+"\n", 0, 0);	
	}
	
	public SN_Configuration() 
	{
	}
}
