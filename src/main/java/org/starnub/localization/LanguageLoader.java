/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.starnub.localization;

import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ConsoleInput_Timer;

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
        localConfig();//TODO Search here and org.org.starnub directory
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
            MessageFormater.msgPrint("Language org.starnub.configuration creation error.", 0, 1);
            io.printStackTrace();
            langConfigCreator();
        } finally {
            if (languageOutput != null) {
                try {
                    languageOutput.close();
                } catch (IOException io) {
                    MessageFormater.msgPrint("Language org.starnub.configuration creation error.", 0, 1);
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

