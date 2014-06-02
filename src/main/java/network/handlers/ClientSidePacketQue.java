package network.handlers;

import java.util.concurrent.ArrayBlockingQueue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import network.StarboundStream;
import network.packets.Packet;
import util.Zlib;


public class ClientSidePacketQue implements Runnable {

	public static ArrayBlockingQueue<Packet> ClientPacketQue = new ArrayBlockingQueue<Packet>(1000);
	
	Packet packet;
	
	@Override
	public void run() {

			try {
				packet = ClientPacketQue.take();
			} catch (InterruptedException e) {
			}

		StarboundStream mainStream = new StarboundStream(buffer());
		StarboundStream payloadStream = new StarboundStream(buffer());

		packet.Write(payloadStream); /* Uncompressed */

		int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
		byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */

		if (payloadLength > 75) /* Compress data greater then 75 Byte */
		{
		data = new Zlib().compress(data); /* Compress payload */
		payloadLength = -data.length; /* Get new payload length */
		}

		mainStream.writeByte(packet.getPacketId()); /* Writing the Packet ID */
		mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
		mainStream.writeAllBytes(data); /* Writing the Packet ID */
		
		Object msg = mainStream.getBuf();
		
		packet.getServerCTX().writeAndFlush(msg);
		}
		
	

}
