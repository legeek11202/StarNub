package network.handlers;

import network.Packet;
import network.StarboundStream;

import java.util.concurrent.ArrayBlockingQueue;

import static io.netty.buffer.Unpooled.buffer;


public class ClientPacketProcessor implements Runnable {

    public static ArrayBlockingQueue<Packet> ClientPacketQue = new ArrayBlockingQueue<Packet>(5000);

    private Packet packet;

    public void run() {
        while (true) {

            if (ClientPacketQue.remainingCapacity() <= 4975) {
                //DEBUG
                System.out.println("Client Packet Que: " + ClientPacketQue.remainingCapacity());
            }
            try {
                packet = ClientPacketQue.take();
            } catch (InterruptedException e) {
            }

            StarboundStream mainStream = new StarboundStream(buffer());
            StarboundStream payloadStream = new StarboundStream(buffer());

            packet.Write(payloadStream); /* Uncompressed */

            int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
            byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */

//        if (payloadLength > 75) /* Compress data greater then 75 Byte */ {
//            data = new Zlib().compress(data); /* Compress payload */
//            payloadLength = -data.length; /* Get new payload length */
//        }

            mainStream.writeByte(packet.getPacketId()); /* Writing the Packet ID */
            mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
            mainStream.writeAllBytes(data); /* Writing the Packet ID */

            packet.getServerCTX().writeAndFlush(mainStream.getBuf());
        }
    }


}
