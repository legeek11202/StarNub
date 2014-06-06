package localization;

import util.stream.MessageFormater;
import util.timers.ConsoleInput_Timer;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/*
 * These strings are static. They will not be localized.
 * All other strings will be based on local file.
 * 
 */

public class LanguageLoader {

    private static String language;
    private static Properties prop = new Properties();
    private static String filePath = "StarNub/Language.config";
    private static FileOutputStream languageOutput;

    public LanguageLoader() {
    }

    public static ResourceBundle getResources() {
        localConfig();//TODO Search here and starnub directory
        Locale locale = new Locale(language);
        MessageFormater.msgPrint("Language: " + language, 0, 0);
        return ResourceBundle.getBundle("language", locale);
    }

    private static void localConfig() {
        try {
            InputStream languageInput = new FileInputStream(filePath);
            try {
                prop.load(languageInput);
                String languagePre = prop.getProperty("Language");
                language = languagePre.toLowerCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            langConfigCreator();
        }
    }

    private static void langConfigCreator() {
        MessageFormater.msgPrint("No language configured.", 0, 0);
        String answerString = ConsoleInput_Timer.inputCall(""
                + "\n\n\n\nYou have 1 minutes to select your language. (Default English)"
                + "\nPlease type in the number and press 'Enter'."
                + "\n\nSupported Languages"
                + "\n==================="
                + "\n1. English"
                + "\n", 60);

        switch (tryParseInt(answerString)) {
            case 0:
                language = "english";
                break;
            case 1:
                language = "english";
                break;
            default: {
                MessageFormater.msgPrint("Error language selection.", 0, 0);
                langConfigCreator();
            }
        }
        try {
            languageOutput = new FileOutputStream(filePath);
            prop.setProperty("Language", language);
            prop.store(languageOutput, null);
            MessageFormater.msgPrint("Language configured.", 0, 0);
        } catch (IOException io) {
            MessageFormater.msgPrint("Language configuration creation error.", 0, 1);
            io.printStackTrace();
            langConfigCreator();
        } finally {
            if (languageOutput != null) {
                try {
                    languageOutput.close();
                } catch (IOException io) {
                    MessageFormater.msgPrint("Language configuration creation error.", 0, 1);
                    io.printStackTrace();
                }

            }
        }
    }

    //TODO Move to general library
    static int tryParseInt(String answerString) {
        try {
            return Integer.parseInt(answerString);
        } catch (NumberFormatException nfe) {
            // Log exception.
            return 0;
        }
    }
}

