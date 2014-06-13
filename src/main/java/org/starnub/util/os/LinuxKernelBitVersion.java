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

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class will generate a Linux Kernel Bit Version.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1
 */

public class LinuxKernelBitVersion {

    public LinuxKernelBitVersion() {
    }

    /**
     * This method will use "uname -m" to return a Linux bit version.
     *
     * @return A integer that represents the bit version.
     * <p>
     * 1 = 32 Bit
     * <p>
     * 2 = 64 Bit
     */

    public static int linuxKernel() {

        String line;
        String output = "";
        int linuxKernel = 0;

        try {
            Process p = Runtime.getRuntime().exec("uname -m");
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            while ((line = input.readLine()) != null) {
                output += (line + '\n');
            }
            input.close();
        } catch (Exception e) {
            linuxKernel = 0;
            MessageFormater.msgPrint(
                    "Linux uname -m Java Message: " + e.getMessage(), 0, 1);
        }
        if (output.contains("x86_64")) {
            linuxKernel = 1;
        } else if (output.contains("i686")) {
            linuxKernel = 2;
        } else {
            linuxKernel = 2;
        }
        return linuxKernel;
    }
}
