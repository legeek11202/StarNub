package org.starnub.core.localization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.starnub.core.util.stream.MessageFormater;
import org.starnub.core.util.timers.ConsoleInput_Timer;

/*
 * These strings are static. They will not be localized.
 * All other strings will be based on local file.
 * 
 */

public class LanguageLoader {
	
	private static String language;
	private static Properties prop = new Properties();
	private static String filePath = "StarNub/Language.config";
	private static InputStream languagInput;
	private static FileOutputStream starLocalizationOutput;
	
	public LanguageLoader() 
	{
	}
	
	public static ResourceBundle getResources ()
	{
		localConfig();
		Locale locale = new Locale(language);
		MessageFormater.msgPrint("Language: "+language, 0, 0);
		return ResourceBundle.getBundle("org.starnub.localization.language", locale);
	}
	
	private static void localConfig()
	{	
		try 
		{
			languagInput = new FileInputStream(filePath);
			try 
			{
				prop.load(languagInput);
				String languagePre = prop.getProperty("Language");
				language = languagePre.toLowerCase();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e1) 
		{
			langConfigCreator();
		}
	}
		
	private static void langConfigCreator()
	{
		MessageFormater.msgPrint("No language configured.", 0, 0);
		String answerString = ConsoleInput_Timer.inputCall(""
				+ "\n\n\n\nYou have 1 minutes to select your language. (Default English)"
				+ "\nPlease type in the number and press 'Enter'."
				+ "\n\nSupported Languages"
				+ "\n==================="
				+ "\n1. English"
				+ "\n",60);
		switch (answerString)
		{
			case "": language = "english"; break;
			case "1": language = "english"; break;
			default: 
			{
			MessageFormater.msgPrint("Error language selection.", 0, 0);
			langConfigCreator();
			}
		}
		try 
		{
			FileOutputStream languageOutput = new FileOutputStream(filePath);
			prop.setProperty("Language", language);
			prop.store(languageOutput, null);
			MessageFormater.msgPrint("Language configured.", 0, 0);
		}
		catch (IOException io) 
		{
			MessageFormater.msgPrint("Language configuration creation error.", 0, 1);
			io.printStackTrace();
			langConfigCreator();
		} 
		finally 
		{
			if (starLocalizationOutput != null) 
			{
				try 
				{
					starLocalizationOutput.close();
				} 
				catch (IOException io) 
				{
					MessageFormater.msgPrint("Language configuration creation error.", 0, 1);
					io.printStackTrace();
				}

			}
		}
	}
}
		
