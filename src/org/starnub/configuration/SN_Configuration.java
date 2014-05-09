package org.starnub.configuration;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.os.SN_Directories;
import org.starnub.util.stream.SN_MessageFormater;
import org.starnub.configuration.SN_ConfigConfigurator;

public class SN_Configuration {
	
	private static ResourceBundle lang = StarNub.language;

	public static void snConfigurationCheck ()
	{
    	SN_MessageFormater.msgPrint(lang.getString("dc"), 0, 0);
    	SN_Directories.snDirCheck();
    	SN_MessageFormater.msgPrint(lang.getString("dcc"), 0, 0);
		SN_MessageFormater.msgPrint(lang.getString("dvc"), 0, 0);
		SN_ConfigConfigurator.configurationValidation();
		SN_MessageFormater.msgPrint("\n\n"+lang.getString("uvf")+"\n"+StarNub.configVariables.toString()+"\n", 0, 0);	
	}
	
	public SN_Configuration() 
	{
	}
}
