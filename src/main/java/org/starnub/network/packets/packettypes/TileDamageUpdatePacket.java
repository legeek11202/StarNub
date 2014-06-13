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

package org.starnub.network.packets.packettypes;

import org.starnub.network.packets.Packet;
import org.starnub.network.StarboundStream;

/**
 * Packet Class.
 * <p>
 * Credit goes to: <br>
 * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
 * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
 * Starbound-Dev - (http://starbound-dev.org/)
 *
 * @author Daniel (Underbalanced) (www.StarNub.org)
 * @version 1.0, 24 May 2014
 */
public class TileDamageUpdatePacket extends Packet {

    private int TileX;
    private int TileY;
    private byte Unknown;
    private float Damage;
    private float DamageEffect;
    private float SourcePosX;
    private float SourcePosY;
    private byte DamageType;
    public TileDamageUpdatePacket() {
        TileX = 0;
        TileY = 0;
        Unknown = 0;
        Damage = 0;
        DamageEffect = 0;
        SourcePosX = 0;
        SourcePosY = 0;
        DamageType = 0;
    }

    @Override
    public byte getPacketId() {
        return 19;
    }

    /**
     * @return the tileX
     */
    public long getTileX() {
        return TileX;
    }

    /**
     * @param tileX the tileX to set
     */
    public void setTileX(int tileX) {
        TileX = tileX;
    }

    /**
     * @return the tileY
     */
    public long getTileY() {
        return TileY;
    }

    /**
     * @param tileY the tileY to set
     */
    public void setTileY(int tileY) {
        TileY = tileY;
    }

    /**
     * @return the unknown
     */
    public byte getUnknown() {
        return Unknown;
    }

    /**
     * @param unknown the unknown to set
     */
    public void setUnknown(byte unknown) {
        Unknown = unknown;
    }

    /**
     * @return the damage
     */
    public float getDamage() {
        return Damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(float damage) {
        Damage = damage;
    }

    /**
     * @return the damageEffect
     */
    public float getDamageEffect() {
        return DamageEffect;
    }

    /**
     * @param damageEffect the damageEffect to set
     */
    public void setDamageEffect(float damageEffect) {
        DamageEffect = damageEffect;
    }

    /**
     * @return the sourcePosX
     */
    public float getSourcePosX() {
        return SourcePosX;
    }

    /**
     * @param sourcePosX the sourcePosX to set
     */
    public void setSourcePosX(float sourcePosX) {
        SourcePosX = sourcePosX;
    }

    /**
     * @return the sourcePosY
     */
    public float getSourcePosY() {
        return SourcePosY;
    }

    /**
     * @param sourcePosY the sourcePosY to set
     */
    public void setSourcePosY(float sourcePosY) {
        SourcePosY = sourcePosY;
    }

    /**
     * @return the damageType
     */
    public byte getDamageType() {
        return DamageType;
    }

    /**
     * @param damageType the damageType to set
     */
    public void setDamageType(byte damageType) {
        DamageType = damageType;
    }

    @Override
    public void Read(StarboundStream stream) {
        TileX = stream.readInt();
        TileY = stream.readInt();
        Unknown = stream.readUnsignedByte();
        Damage = stream.readFloatInt32();
        DamageEffect = stream.readFloatInt32();
        SourcePosX = stream.readFloatInt32();
        SourcePosY = stream.readFloatInt32();
        DamageType = stream.readUnsignedByte();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeInt(TileX);
        stream.writeInt(TileY);
        stream.writeByte(Unknown);
        stream.writeFloatInt32(Damage);
        stream.writeFloatInt32(DamageEffect);
        stream.writeFloatInt32(SourcePosX);
        stream.writeFloatInt32(SourcePosY);
        stream.writeByte(DamageType);
    }
}

