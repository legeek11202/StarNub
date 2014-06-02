package network.handlers;


import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;


import java.util.List;

import network.StarboundStream;
import network.packets.Packet;
import util.Zlib;

public class PacketEncoder extends MessageToMessageDecoder<Object> {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out)
			throws Exception
	{	
		Packet packet = (Packet) msg;

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

		out.add(mainStream.getBuf());
	}
}
