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

package org.starnub.datatypes;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

public class VLQ {

    private int length;
    private long value;

    public VLQ(int length, long value) {
        this.length = length;
        this.value = value;
    }

    public VLQ() {
    }

    public static VLQ signedFromBuffer(ByteBuf buf) {

        VLQ value = unsignedFromBuffer(buf);

        long val = value.getValue();

        if ((value.getValue() & 1) == 0x00)
            val = (long) val >> 1;
        else
            val = -((long) (val >> 1) + 1);

        value.setValue(val);

        return value;

    }

    public static VLQ unsignedFromBuffer(ByteBuf buf) throws IndexOutOfBoundsException {

        long value = 0L;

        int length = 0;

        /* 64 bit Integer should not be larger then 10 bytes */
        while (length <= 10) {

            byte tmp = buf.readByte();

            value = (value << 7) | (long) (tmp & 0x7f);

            length++;

            if ((tmp & 0x80) == 0)
                break;
        }

        return new VLQ(length, value);

    }

    public static byte[] createVLQ(long value) {

        List<Byte> result = new ArrayList<Byte>();

        if (value == 0)
            result.add((byte) 0);

        while (value > 0) {

            byte tmp = (byte) (value & 0x7f);

            value >>= 7;

            if (value != 0)
                tmp |= 0x80;

            result.add(0, tmp);

        }

        if (result.size() > 1) {
            result.set(0, (byte) (result.get(0) | 0x80));
            result.set(result.size() - 1, (byte) (result.get(result.size() - 1) ^ 0x80));
        }

        byte[] res = new byte[result.size()];

        int ctr = 0;
        for (byte b : result) {
            res[ctr] = b;
            ctr++;
        }

        return res;

    }

    public static byte[] createSignedVLQ(long value) {


        long result = Math.abs(value * 2);

        if (value < 0)
            result -= 1;

        return createVLQ(result);

    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
