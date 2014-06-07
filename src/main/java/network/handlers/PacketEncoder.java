package network.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import network.Packet;
import network.StarboundStream;

import java.util.List;

import static io.netty.buffer.Unpooled.buffer;

public class PacketEncoder extends MessageToMessageDecoder<Object> {

    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out)
            throws Exception {

        StarboundStream mainStream = new StarboundStream(buffer());
        StarboundStream payloadStream = new StarboundStream(buffer());

        ((Packet) msg).Write(payloadStream); /* Uncompressed */

        int payloadLength = payloadStream.getBufferSize(); /* Get payload length */
        byte[] data = payloadStream.getBuf().readBytes(payloadLength).array(); /* Put data into array */

        //FIXME Probable problem with creating the VLQ. The compression works. Packet is sent but the server does not understand it. I think the sign just needs to be fliped for negative VLQ's, when the VLQ runs it makes absolute values
//        if (payloadLength > 75) /* Compress data greater then 75 Byte */ {
//            data = new Zlib().compress(data); /* Compress payload */
//            payloadLength = -data.length; /* Get new payload length */
//        }

        mainStream.writeByte(((Packet) msg).getPacketId()); /* Writing the Packet ID */
        mainStream.writeSignedVLQ(payloadLength); /* Writing the Signed VLQ */
        mainStream.writeAllBytes(data); /* Writing the Packet ID */

        out.add(mainStream.getBuf());
    }
}
