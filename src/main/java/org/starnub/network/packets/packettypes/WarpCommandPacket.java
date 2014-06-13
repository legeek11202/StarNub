//package org.starnub.network.packets;
//
//import org.starnub.network.StarboundStream;
//import org.starnub.network.packets.Packet;
//
///**
// * Packet Class.
// * <p>
// * Credit goes to: <br>
// * SirCmpwn - (https://github.com/SirCmpwn/StarNet) <br>
// * Mitch528 - (https://github.com/Mitch528/SharpStar) <br>
// * Starbound-Dev - (http://starbound-dev.org/)
// *
// * @author Daniel (Underbalanced) (www.StarNub.org)
// * @version 1.0, 24 May 2014
// */
//public class WarpCommandPacket extends Packet {
//
//    private WarpType WarpType;
//    private WorldCoordinate Coordinates;
//    private String Player;
//    public WarpCommandPacket() {
//        Coordinates = new WorldCoordinate();
//    }
//
//    @Override
//    public byte getPacketId() {
//        return 10;
//    }
//
//    /**
//     * @return the warpType
//     */
//    public WarpType getWarpType() {
//        return WarpType;
//    }
//
//    /**
//     * @param warpType the warpType to set
//     */
//    public void setWarpType(WarpType warpType) {
//        WarpType = warpType;
//    }
//
//    /**
//     * @return the coordinates
//     */
//    public WorldCoordinate getCoordinates() {
//        return Coordinates;
//    }
//
//    /**
//     * @param coordinates the coordinates to set
//     */
//    public void setCoordinates(WorldCoordinate coordinates) {
//        Coordinates = coordinates;
//    }
//
//    /**
//     * @return the player
//     */
//    public String getPlayer() {
//        return Player;
//    }
//
//    /**
//     * @param player the player to set
//     */
//    public void setPlayer(String player) {
//        Player = player;
//    }
//
//    @Override
//    public void Read(StarboundStream stream) {
//        WarpType = (WarpType) stream.readUnsignedInt();
//        Coordinates = stream.ReadWorldCoordinate();
//        Player = stream.readString();
//    }
//
//    @Override
//    public void Write(StarboundStream stream) {
//        stream.writeInt((uint) WarpType);
//        stream.WriteWorldCoordinate(Coordinates);
//        stream.writeString(Player);
//    }
//
//    public enum WarpType {
//        MoveShip(1),
//        WarpUp(2),
//        WarpOtherShip(3),
//        WarpDown(4),
//        WarpHome(5)
//    }
//}
