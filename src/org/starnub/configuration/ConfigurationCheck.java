package org.starnub.configuration;

import java.util.ResourceBundle;

import org.starnub.StarNub;
import org.starnub.util.os.Directories;
import org.starnub.util.stream.MessageFormater;

/**
 * This class manages the starbound configuration.
 *
 * @author Daniel Merwin(Underbalanced)
 * @version 1
 *              
 */

public class ConfigurationCheck {
	
	private static ResourceBundle lang = StarNub.language;
	
	public static void checkConfiguration()
	{
		snConfigurationCheck();
	}

	private static void snConfigurationCheck ()
	{
		/* Directory Check */
    	Directories.snDirCheck();
    	MessageFormater.msgPrint(lang.getString("dc"), 0, 0);
    	/* Configuration Validation */
		Configurator.validateConfig();
		MessageFormater.msgPrint("\n\n"+lang.getString("uvf")+"\n"+StarNub.configVariables.toString()+"\n", 0, 0);
		/* Configure starbound.config */
		SbConfigurator.sbConfigConfiguration();
		MessageFormater.msgPrint(lang.getString("sbcc"), 0, 0);
	}
	
	public ConfigurationCheck() 
	{
	}
}
