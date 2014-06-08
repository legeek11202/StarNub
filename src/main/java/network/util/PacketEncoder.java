package network.util;

import io.netty.buffer.ByteBuf;
import network.StarboundStream;
import network.packets.Packet;

import static io.netty.buffer.Unpooled.buffer;

public class PacketEncoder {

    public static ByteBuf PacketToMessageEncoder(Packet packet) {

        StarboundStream mainStream = new StarboundStream(buffer()); //TODO Heap Buffer
        StarboundStream payloadStream = new StarboundStream(buffer());

        packet.Write(payloadStream); /* Uncompressed */

        int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
        byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */

        //TODO Fix compression
//        if (payloadLength > 75) /* Compress data greater then 75 Byte */ {
//            data = new Zlib().compress(data); /* Compress payload */
//            payloadLength = data.length; /* Get new payload length */
//        }

        mainStream.writeByte(packet.getPacketId()); /* Writing the Packet ID */
        mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
        mainStream.writeAllBytes(data); /* Writing the Packet ID */


        return mainStream.getBuf();
    }

}
