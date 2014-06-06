package configuration;

import starnub.StarNub;
import util.simplejson.JSONObject;
import util.simplejson.JSONPrettyPrint;
import util.simplejson.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class SbConfigurator {

    public SbConfigurator() {
    }

    public static void sbConfigConfiguration() {
        configStarboundConfiguration();
    }

    private static void configStarboundConfiguration() {
        JSONParser parser = new JSONParser();
        Integer sbPort = StarNub.configVariables.get("Starbound_Port");

        try {
            Object obj = parser.parse(new FileReader("starbound.config"));
            JSONObject jsonObject = (JSONObject) obj;
            jsonObject.put("gamePort", new Long(sbPort));
            FileWriter file = new FileWriter("starbound.config");
            file.write(JSONPrettyPrint.toJSONString(jsonObject));
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
