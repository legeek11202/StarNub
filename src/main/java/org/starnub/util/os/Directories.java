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

package org.starnub.util.os;

import org.starnub.util.stream.MessageFormater;

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
