package org.starnub.configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.starnub.StarNub;
import org.starnub.util.simplejson.JSONObject;
import org.starnub.util.simplejson.JSONPrettyPrint;
import org.starnub.util.simplejson.parser.JSONParser;
import org.starnub.util.simplejson.parser.ParseException;

public class SB_ConfigConfigurator {
	
    public static void sbConfigConfiguration() 
    {
    	configStarboundConfiguration();
    }
    
	private static void configStarboundConfiguration () 
    {	
		JSONParser parser = new JSONParser();
		final int sbRemotePort = StarNub.configVariables.get("Starbound_Port");
		
		try 
		{
			Object obj = parser.parse(new FileReader("starbound.config"));
			JSONObject jsonObject = (JSONObject) obj;
			jsonObject.put("gamePort", new Long(sbRemotePort));
			FileWriter file = new FileWriter("starbound.config");
			file.write(JSONPrettyPrint.toJSONString(jsonObject));
			file.flush();
			file.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
		e.printStackTrace();
		}
    }
 
	public SB_ConfigConfigurator() {
	}
}
