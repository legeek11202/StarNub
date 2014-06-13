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

/**
 * This class will determine where Starbound Server is located at within the
 * Starbound directory and based on the system OS.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1
 */

public final class GetFilePath {

    private static String filepath = setFilepath();

    /**
     * This method will return a file path based on the OS Version and Bit
     * Version.
     *
     * @return A integer that represents the bit version.
     * <p>
     * Windows = win32/starbound_server.exe
     * <p>
     * Linux 32 = ./linux32/starbound_server
     * <p>
     * Linux 64 = ./linux64/starbound_server
     */

    public static String getFilePath() {
        return filepath;
    }

    private static String setFilepath() {

        String systemOS = System.getProperty("os.name");
        Boolean osWindows = systemOS.startsWith("Windows");

        if (osWindows) {
            MessageFormater.msgPrint("Using Win32 Starbound_Server.exe.", 0, 0);
            return "win32/starbound_server.exe";
        } else {
            int linuxKernel = LinuxKernelBitVersion.linuxKernel();
            switch (linuxKernel) {
                case 1: {
                    MessageFormater.msgPrint("Using Linux64 Starbound_Server.",
                            0, 0);
                    return "./linux64/starbound_server";
                }
                case 2: {
                    MessageFormater.msgPrint("Using Linux32 Starbound_Server.",
                            0, 0);
                    return "./linux32/starbound_server";
                }
                default: {
                    return "./linux32/starbound_server";
                }
            }
        }
    }
}
