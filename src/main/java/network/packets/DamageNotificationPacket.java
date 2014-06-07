package network.packets;

import network.StarboundStream;
import network.Packet;

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
public class DamageNotificationPacket extends Packet {

    private long CauseEntityId;
    private long TargetEntityId;
    private long PositionX;
    private long PositionY;
    private long Damage;
    private byte DamageKind;
    private String DamageSourceKind;
    private String TargetMaterialKind;
    private byte HitResultKind;

    @Override
    public byte getPacketId() {
        return 45;
    }

    /**
     * @return the causeEntityId
     */
    public long getCauseEntityId() {
        return CauseEntityId;
    }

    /**
     * @param causeEntityId the causeEntityId to set
     */
    public void setCauseEntityId(long causeEntityId) {
        CauseEntityId = causeEntityId;
    }

    /**
     * @return the targetEntityId
     */
    public long getTargetEntityId() {
        return TargetEntityId;
    }

    /**
     * @param targetEntityId the targetEntityId to set
     */
    public void setTargetEntityId(long targetEntityId) {
        TargetEntityId = targetEntityId;
    }

    /**
     * @return the positionX
     */
    public long getPositionX() {
        return PositionX;
    }

    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(long positionX) {
        PositionX = positionX;
    }

    /**
     * @return the positionY
     */
    public long getPositionY() {
        return PositionY;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(long positionY) {
        PositionY = positionY;
    }

    /**
     * @return the damage
     */
    public long getDamage() {
        return Damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(long damage) {
        Damage = damage;
    }

    /**
     * @return the damageKind
     */
    public byte getDamageKind() {
        return DamageKind;
    }

    /**
     * @param damageKind the damageKind to set
     */
    public void setDamageKind(byte damageKind) {
        DamageKind = damageKind;
    }

    /**
     * @return the damageSourceKind
     */
    public String getDamageSourceKind() {
        return DamageSourceKind;
    }

    /**
     * @param damageSourceKind the damageSourceKind to set
     */
    public void setDamageSourceKind(String damageSourceKind) {
        DamageSourceKind = damageSourceKind;
    }

    /**
     * @return the targetMaterialKind
     */
    public String getTargetMaterialKind() {
        return TargetMaterialKind;
    }

    /**
     * @param targetMaterialKind the targetMaterialKind to set
     */
    public void setTargetMaterialKind(String targetMaterialKind) {
        TargetMaterialKind = targetMaterialKind;
    }

    /**
     * @return the hitResultKind
     */
    public byte getHitResultKind() {
        return HitResultKind;
    }

    /**
     * @param hitResultKind the hitResultKind to set
     */
    public void setHitResultKind(byte hitResultKind) {
        HitResultKind = hitResultKind;
    }

    @Override
    public void Read(StarboundStream stream) {
        CauseEntityId = stream.readSignedVLQ().getValue();
        TargetEntityId = stream.readSignedVLQ().getLength();
        PositionX = stream.readSignedVLQ().getValue() / 100;
        PositionY = stream.readSignedVLQ().getValue() / 100;
        Damage = stream.readSignedVLQ().getValue() / 100;
        DamageKind = stream.readUnsignedByte();
        DamageSourceKind = stream.readString();
        TargetMaterialKind = stream.readString();
        HitResultKind = stream.readUnsignedByte();
    }

    @Override
    public void Write(StarboundStream stream) {
        stream.writeSignedVLQ(CauseEntityId);
        stream.writeSignedVLQ(TargetEntityId);
        stream.writeSignedVLQ(PositionX * 100);
        stream.writeSignedVLQ(PositionY * 100);
        stream.writeSignedVLQ(Damage * 100);
        stream.writeByte(DamageKind);
        stream.writeString(DamageSourceKind);
        stream.writeString(TargetMaterialKind);
        stream.writeByte(HitResultKind);
    }
}
