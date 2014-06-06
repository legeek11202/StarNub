package network.handlers;

import network.Packet;
import network.StarboundStream;
import util.Zlib;

import java.util.concurrent.ArrayBlockingQueue;

import static io.netty.buffer.Unpooled.buffer;


public class ClientSidePacketQue implements Runnable {

    public static ArrayBlockingQueue<Packet> ClientPacketQue = new ArrayBlockingQueue<>(1000);

    Packet packet;

    public void run() {

        try {
            packet = ClientPacketQue.take();
        } catch (InterruptedException e) {
        }
        System.out.println("Point 1");
        StarboundStream mainStream = new StarboundStream(buffer());
        StarboundStream payloadStream = new StarboundStream(buffer());

        packet.Write(payloadStream); /* Uncompressed */

        int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
        byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */
        System.out.println("Point 1");
        if (payloadLength > 75) /* Compress data greater then 75 Byte */ {
            data = new Zlib().compress(data); /* Compress payload */
            payloadLength = -data.length; /* Get new payload length */
        }
        System.out.println("Point 1");
        mainStream.writeByte(packet.getPacketId()); /* Writing the Packet ID */
        mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
        mainStream.writeAllBytes(data); /* Writing the Packet ID */


        System.out.println("Point 1");
        packet.getServerCTX().channel().writeAndFlush(mainStream.getBuf());
    }


}
