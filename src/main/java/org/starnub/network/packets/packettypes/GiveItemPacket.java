//package org.starnub.network.packets;
//
//import org.starnub.datatypes.Variant;
//import org.starnub.datatypes.VariantHashmap;
//import org.starnub.network.StarboundStream;
//import org.starnub.network.packets.Packet;
//
//import java.org.starnub.util.Map;
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
//public class GiveItemPacket extends Packet {
//
//    private String ItemName;
//    private long Count;
//    private Map<String, Variant> ItemProperties;
//    public GiveItemPacket() {
//        ItemProperties = new VariantHashmap().VariantHashmap;
//    }
//
//    @Override
//    public byte getPacketId() {
//        return 21;
//    }
//
//    /**
//     * @return the itemName
//     */
//    public String getItemName() {
//        return ItemName;
//    }
//
//    /**
//     * @param itemName the itemName to set
//     */
//    public void setItemName(String itemName) {
//        ItemName = itemName;
//    }
//
//    /**
//     * @return the count
//     */
//    public long getCount() {
//        return Count;
//    }
//
//    /**
//     * @param count the count to set
//     */
//    public void setCount(long count) {
//        Count = count;
//    }
//
//    /**
//     * @return the itemProperties
//     */
//    public Map<String, Variant> getItemProperties() {
//        return ItemProperties;
//    }
//
//    /**
//     * @param itemProperties the itemProperties to set
//     */
//    public void setItemProperties(Map<String, Variant> itemProperties) {
//        ItemProperties = itemProperties;
//    }
//
//    @Override
//    public void Read(StarboundStream stream) {
//        ItemName = stream.readString();
//        Count = stream.readVLQ().getValue();
//        try { ItemProperties = (Map<String, Variant>) stream.readVariant(); } catch (Exception e) {e.printStackTrace(); }
//    }
//
//    @Override
//    public void Write(StarboundStream stream) {
//        stream.writeString(ItemName);
//        stream.writeVLQ(Count);
//        try {
//            stream.writeVariant(new Variant(ItemProperties));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
