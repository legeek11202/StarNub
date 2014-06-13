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

package org.starnub.configuration;

import org.starnub.StarNub;
import org.starnub.util.stream.MessageFormater;
import org.starnub.util.timers.ConsoleInput_Timer;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class Configurator {

    private static ResourceBundle lang = StarNub.language;

    private static String[] snConfigQuestions = new String[]
            {
                    "\n" + lang.getString("cq1"),
                    "\n" + lang.getString("cq2"),
                    "\n" + lang.getString("cq3"),
            };

    private static String[] snConfigOptions = new String[]
            {
                    "StarNub_Port",
                    "Starbound_Port",
                    "Auto_Restart_Timer",
            };

    private static String[] snConfigValues = new String[]
            {
                    "21025",
                    "21024",
                    "4",
            };

    private static String filePath = "StarNub/StarNub.config";

    public Configurator() {
    }

    public static void validateConfig() {
        configurationValidation();
    }

    private static void configurationQuestions() {
        OutputStream starNubConfig = null;
        Properties prop = new Properties();
        int a = 0;
        String answerString;
        MessageFormater.msgPrint(lang.getString("cc"), 0, 0);
        MessageFormater.msgPrint("\n\n" + lang.getString("cc1") + "\n" + lang.getString("cc2"), 0, 0);
            /* Loop through array of org.starnub.configuration questions */
        for (String qS : snConfigQuestions) {
				/* Calls the TaskTimer and waits 30 second reply */
            answerString = ConsoleInput_Timer.inputCall(qS, 30);
            if (answerString.isEmpty()) {
                prop.setProperty(snConfigOptions[a], snConfigValues[a]);
                StarNub.configVariables.put(snConfigOptions[a], Integer.parseInt(snConfigValues[a]));
            } else {
                prop.setProperty(snConfigOptions[a], answerString);
                StarNub.configVariables.put(snConfigOptions[a], Integer.parseInt(answerString));
            }
            a += 1;
        }
        MessageFormater.msgPrint(
                "\n" + lang.getString("cqc1") + " " + prop.getProperty(snConfigOptions[0])
                        + "\n" + lang.getString("cqc2") + " " + prop.getProperty(snConfigOptions[1])
                        + "\n" + lang.getString("cqc3") + " " + prop.getProperty(snConfigOptions[2]) + " " + lang.getString("cqc4"), 0, 0);
        answerString = ConsoleInput_Timer.inputCall("\n" + lang.getString("cqc5"), 30);
        if (answerString.equalsIgnoreCase("Y") || answerString.isEmpty()) {
            try {
                starNubConfig = new FileOutputStream(filePath);
                prop.store(starNubConfig, null);
                MessageFormater.msgPrint(lang.getString("cf"), 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (starNubConfig != null) {
                    try {
                        starNubConfig.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            StarNub.configVariables.clear();
            configurationQuestions();
        }
    }

    private static void configurationValidation() {

        Properties prop = new Properties();
        try {
            InputStream starNubConfig = new FileInputStream(filePath);
            try {
                prop.load(starNubConfig);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String keys : snConfigOptions) {
                StarNub.configVariables.put(keys, Integer.parseInt(prop.getProperty(keys)));
            }

        } catch (FileNotFoundException e) {
            MessageFormater.msgPrint(lang.getString("sn.cc.3"), 0, 0);
            configurationQuestions();
        }
    }
}