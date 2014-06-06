package network.handlers;

import network.Packet;
import network.StarboundStream;
import util.Zlib;

import java.util.concurrent.ArrayBlockingQueue;

import static io.netty.buffer.Unpooled.buffer;

public class ServerSidePacketQue implements Runnable {

    public static ArrayBlockingQueue<Packet> ServerPacketQue = new ArrayBlockingQueue<Packet>(1000);

    Packet packet;

    public void run() {

        try {
            packet = ServerPacketQue.take();
        } catch (InterruptedException e) {
        }

        StarboundStream mainStream = new StarboundStream(buffer());
        StarboundStream payloadStream = new StarboundStream(buffer());

        packet.Write(payloadStream); /* Uncompressed */

        int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
        byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */

        if (payloadLength > 75) /* Compress data greater then 75 Byte */ {
            data = new Zlib().compress(data); /* Compress payload */
            payloadLength = -data.length; /* Get new payload length */
        }

        mainStream.writeByte(packet.getPacketId()); /* Writing the Packet ID */
        mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
        mainStream.writeAllBytes(data); /* Writing the Packet ID */

        packet.getClientCTX().writeAndFlush(mainStream.getBuf());

    }

}
