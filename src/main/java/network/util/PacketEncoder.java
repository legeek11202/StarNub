package network.util;

import network.Packet;
import network.StarboundStream;
import util.Zlib;

import static io.netty.buffer.Unpooled.buffer;

public class PacketEncoder {

    public static Object PacketToMessageEncoder(Object msg) {

        StarboundStream mainStream = new StarboundStream(buffer());
        StarboundStream payloadStream = new StarboundStream(buffer());

        ((Packet) msg).Write(payloadStream); /* Uncompressed */

        int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
        byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */

        if (payloadLength > 75) /* Compress data greater then 75 Byte */ {
            data = new Zlib().compress(data); /* Compress payload */
            payloadLength = -data.length; /* Get new payload length */
        }

        mainStream.writeByte(((Packet) msg).getPacketId()); /* Writing the Packet ID */
        mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
        mainStream.writeAllBytes(data); /* Writing the Packet ID */

        return mainStream.getBuf();
    }

}
