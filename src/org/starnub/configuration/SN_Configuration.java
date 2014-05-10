package org.starnub.configuration;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.os.SN_Directories;
import org.starnub.util.stream.SN_MessageFormater;

/**
 * This class manages the starbound configuration.
 *
 * @author Daniel Merwin(Underbalanced)
 * @version 1
 *              
 */

public class SN_Configuration {
	
	private static ResourceBundle lang = StarNub.language;
	
	public static void checkConfiguration()
	{
		snConfigurationCheck();
	}

	private static void snConfigurationCheck ()
	{
    	SN_MessageFormater.msgPrint(lang.getString("dc"), 0, 0);
    	SN_Directories.snDirCheck();
    	SN_MessageFormater.msgPrint(lang.getString("dcc"), 0, 0);
		SN_MessageFormater.msgPrint(lang.getString("dvc"), 0, 0);
		SN_ConfigConfigurator.validateConfig();
		SN_MessageFormater.msgPrint("\n\n"+lang.getString("uvf")+"\n"+StarNub.configVariables.toString()+"\n", 0, 0);
		SN_MessageFormater.msgPrint(lang.getString("sbcc1"), 0, 0);
		SB_ConfigConfigurator.sbConfigConfiguration();
		SN_MessageFormater.msgPrint(lang.getString("sbcc2"), 0, 0);
	}
	
	public SN_Configuration() 
	{
	}
}
