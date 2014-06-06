package util.os;

import util.stream.MessageFormater;

import java.io.File;

/**
 * This class will generate the directories for StarNub.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1
 */

public class Directories {

    public Directories() {
    }

    public static void snDirCheck() {
        try {
            /* Directories we want to use */
            String[] snDirectories = new String[]{""
                    + "StarNub",
                    "StarNub/Server Logs",
                    "StarNub/Error Logs",
                    "StarNub/Plugins"};

			/* Checks each directory to see if it exist */
            for (String strDirectory : snDirectories) {
                File directory = new File(strDirectory);
                if (!directory.exists()) {
                    boolean result = directory.mkdir();
                    if (result) {
						/* Prints if a directory was created or not */
                        MessageFormater.msgPrint(
                                directory + "directory created.",
                                0, 0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
