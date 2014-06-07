package network.query;

import datatypes.VLQ;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import network.StarboundStream;
import network.packets.ProtocolVersionPacket;
import starnub.StarNub;
import util.Zlib;

import java.util.List;


public class QueryPacketDecoder extends ByteToMessageDecoder {

    StarboundStream mainStream;
    private ProtocolVersionPacket packet;
    private long vlqvalue;
    private boolean compressed;
    private VLQ vlq;
    private byte packetId;
    private boolean firstCycle = true; /* Byte Buffer Cycle */

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object> out) throws Exception {
    	int serverVersion = StarNub.ProtocolVersionPacket.getProtocolVersion();
    	if (serverVersion == 0){
    	
    		if (bb.readableBytes() <= 1) {
    			return; /* No readable bytes */
    		}

    		if (firstCycle) { /* We only want to perform these functions once */ 
    			bb.markReaderIndex(); /* Puts the bytebuf reader at 0 */

    			mainStream = new StarboundStream(bb);

    			packetId = (byte) mainStream.getBuf().readUnsignedByte();/* Reader has moved*/

    			vlq = mainStream.readSignedVLQ();

    			vlqvalue = vlq.getValue();

    			compressed = vlq.getValue() < 0;

    			if (compressed) {
    				vlqvalue = -vlqvalue;
    			}
    		}

    		if (bb.readableBytes() < vlqvalue) { /* Did not get all the data */
            firstCycle = false; /* Not enough bytes in buffer */
            return;
    		}

    		byte[] data = mainStream.getBuf().readBytes((int) vlqvalue).array();

    		if (compressed) {
    			data = new Zlib().decompress(data);
    		}

    		ByteBuf paylaod = Unpooled.copiedBuffer(data);

    		StarboundStream stream = new StarboundStream(paylaod);

    		if (packetId == 0) {
    			packet = new ProtocolVersionPacket();
    		}
    		else{
    			firstCycle = true;
    			return;
    		}

    		packet.Read(stream);

    		serverVersion = packet.getProtocolVersion();
    		StarNub.ProtocolVersionPacket.setProtocolVersion(serverVersion);
    		
    	    //DEBUG
    	    System.out.println("Packet: " + packet);
    	    SbQueryProcessor.setStatus(true);
    	    ctx.close();
    	    firstCycle = true;
    }
}

	/* Exceptions */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
	}
}
