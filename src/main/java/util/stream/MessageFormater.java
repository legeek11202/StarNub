package util.stream;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


/*
* This class's method will print out StarNub messages with a time stamp.
* 
* This method will return nothing.
**/

public class MessageFormater {

    private static String[] serverArray = new String[]
            {
                    "[StarNub ",
                    "[Starbound ",
            };
    private static String[] typeArray = new String[]
            {
                    "Info]: ",
                    "Error]: ",
                    "Chat]: ",
            };

    public MessageFormater() {
    }

    public static void msgPrint(String message, int server, int type) {
        /* Adds a fresh date for each entry to stdout */
        new DateTime();
        System.out.println(DateTime.now().toString(DateTimeFormat.forPattern("[HH:mm:ss]")) + serverArray[server] + typeArray[type] + message);
    }
}
