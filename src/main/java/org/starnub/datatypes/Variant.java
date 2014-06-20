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

import org.starnub.network.StarboundStream;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Variant {

    private Object value;

    private Variant() {
    }

    public Variant(Object value) throws Exception {
        if (!(value == null ||
                value instanceof String ||
                value instanceof Double ||
                value instanceof Boolean ||
                value instanceof Byte ||
                value instanceof Variant[] ||
                value instanceof Map<?, ?>)) {
            throw new Exception("Variants are unable to represent " + value.getClass().getName() + ".");
        }

        this.value = value;
    }

    public static Variant fromStream(StarboundStream stream) throws Exception {
        Variant variant = new Variant();
        byte type = stream.readUnsignedByte();
        switch (type) {
            case 1:
                variant.value = null;
                break;
            case 2:
                variant.value = stream.readDoubleInt64();
                break;
            case 3:
                variant.value = stream.readBoolean();
                break;
            case 4:
                variant.value = stream.readVLQ().getValue();
                break;
            case 5:
                variant.value = stream.readString();
                break;
            case 6:
                Variant[] array = new Variant[(int) stream.readVLQ().getValue()];
                for (int i = 0; i < array.length; i++)
                    array[i] = Variant.fromStream(stream);
                variant.value = array;
                break;
            case 7:
                Map<String, Variant> dict = new HashMap<String, Variant>();
                int length = (int) stream.readVLQ().getValue();
                while (length-- > 0)
                    dict.put(stream.readString(), Variant.fromStream(stream));
                variant.value = dict;
                break;
            default:
                System.err.println("Unknown Variant Type: "+type);
                throw new Exception("Unknown Variant type");
        }
        return variant;
    }

    @SuppressWarnings("unchecked")
    public void writeTo(StarboundStream stream) {
        if (value == null) {
            stream.writeByte(1);
        } else if (value instanceof Double) {
            stream.writeByte(2);
            stream.getBuf().writeDouble((double) value);
        } else if (value instanceof Boolean) {
            stream.writeByte(3);
            stream.getBuf().writeBoolean((boolean) value);
        } else if (value instanceof Long) {
            stream.writeByte(4);
            stream.writeVLQ((long) value);
        } else if (value instanceof String) {
            stream.getBuf().writeByte(5);
            stream.writeString((String) value);
        } else if (value instanceof Variant[]) {
            stream.getBuf().writeByte(6);
            Variant[] array = (Variant[]) value;
            stream.writeVLQ((long) array.length);
            for (int i = 0; i < array.length; i++)
                array[i].writeTo(stream);
        } else if (value instanceof Map<?, ?>) {
            stream.getBuf().writeByte(7);
            Map<String, Variant> dict = (Map<String, Variant>) value;
            stream.writeVLQ((long) dict.size());
            for (Entry<String, Variant> kvp : dict.entrySet()) {
                stream.writeString(kvp.getKey());
                kvp.getValue().writeTo(stream);
            }
        }
    }

    public Object getValue() {
        return value;
    }

}