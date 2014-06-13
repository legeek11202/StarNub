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

package org.starnub.util.stream;

import java.io.IOException;
import java.io.OutputStream;

/*
* This class's methods override the system output to extend the functionality.
* 
* This method will return nothing.
**/

public class MultiOutputStream extends OutputStream {

    OutputStream[] outputStreams;

    public MultiOutputStream() {
    }

    public MultiOutputStream(OutputStream... outputStreams) {
        this.outputStreams = outputStreams;
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream out : outputStreams)
            out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (OutputStream out : outputStreams)
            out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream out : outputStreams)
            out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream out : outputStreams)
            out.flush();
    }

    @Override
    public void close() throws IOException {
        for (OutputStream out : outputStreams)
            out.close();
    }
}