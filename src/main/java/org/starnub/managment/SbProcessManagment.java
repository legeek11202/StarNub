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

package org.starnub.managment;

import org.starnub.util.os.GetFilePath;

/**
 * This class will create and manage the Starbound_Server Process.
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 17 May 2014 (Incomplete)
 */

public class SbProcessManagment {

    private static Process sbProcess;

    public SbProcessManagment() {
    }

    /**
     * This method will return a file path based on the OS Version and Bit
     * Version.
     *
     * @return A Process that represents the Starbound Server Subprocess of this
     * Java VM.
     */

    public static Process getSbProcess() {
        return sbProcess;
    }

    /**
     * This method will build and start the Starbound Process and Starbound
     * Stream Managers.
     */

    public static void sb_ProcessStart() {
        try {
            ProcessBuilder sbProcessBuild = new ProcessBuilder(
                    GetFilePath.getFilePath());
            sbProcessBuild.redirectErrorStream(true);
            sbProcess = sbProcessBuild.start();
            Runnable sb_StreamInput = new SbProcessStreamManagment();
            new Thread(sb_StreamInput).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will kill the running Starbound Server Subprocess within this
     * Java VM.
     */

    public static void sb_ProcessKill() {
        try {
            sbProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will return a boolean whether the Starbound Server Subprocess
     * is still alive.
     *
     * @return <p>
     * True - Alive
     * <p>
     * False - No process
     */

    public static boolean sb_ProcessStatus() {
        try {
            return sbProcess.isAlive();
        } catch (Exception e) {
            return false;
        }
    }
}
