package network.query;

import datatypes.VLQ;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import network.StarboundStream;
import starnub.StarNub;

import java.util.List;


public class QueryPacketDecoder extends ByteToMessageDecoder {

    private VLQ vlq;
    private byte packetId;
    private long vlqValue;

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    	if (StarNub.ProtocolVersionPacket.getProtocolVersion() == 0) {

        /* If not enough bytes in buffer - start over - Starbound >= 3 Byte Packets */
            if (in.readableBytes() <= 3) {
                return;
            }

            /* Packet ID - used to derive packet class */
                packetId = (byte) in.readUnsignedByte();
            /* VLQ is used to get VLQ.value which is payload length */
                vlq = VLQ.signedFromBuffer(in);
            /* Payload Length */
                vlqValue = vlq.getValue();

            /* Not enough bytes in buffer */
            if (in.readableBytes() < vlqValue) {
            /* Do not want to set fields over */
                return;
            }

            if (packetId == 0) {
                /* Non compressed */
                StarNub.ProtocolVersionPacket.Read((new StarboundStream(in.readBytes((int) vlqValue))));
            }
        }
    	SbQueryProcessor.setStatus(true);
        ctx.close();
    }

	/* Exceptions */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
	}
}