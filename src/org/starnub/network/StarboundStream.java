package org.starnub.network;

import java.nio.charset.Charset;

import org.starnub.datatypes.VLQ;
import org.starnub.datatypes.Variant;

import io.netty.buffer.ByteBuf;

public class StarboundStream
{
	/* Buffer from Netty IO */
	private ByteBuf buf;
	
	public ByteBuf getBuf()
	{
		return buf;
	}

	public void setBuf(ByteBuf buf)
	{
		this.buf = buf;
	}
	
	/* Stream */
	public StarboundStream(ByteBuf buf)
	{
		this.buf = buf;
	}

	/* Boolean */
	public boolean readBoolean()
	{
		return readUnsignedByte() != 0;
	}
    public void writeBoolean(boolean value)
    {
    	buf.writeByte(value ? (byte)1 : (byte)0);
    }
	
	/* Byte 8 Int Array */
	public byte[] readInt8Array()
	{
		return buf.readBytes(1).array();
	}
	public byte[] readInt8Array(int length)
	{
		return buf.readBytes(length).array();
	}
	public void writeInt8Array(byte[] bytes)
	{
		buf.writeBytes(bytes);
	}
    public void writeInt8Array(byte[] value, boolean includeLength)
    {
        if (includeLength)
            writeVLQ((long)value.length);
        buf.writeBytes(value, 0, value.length);
    }
    
	/* Byte 16 Int Array */
	public byte[] readInt16Array()
	{
		return buf.readBytes(2).array();
	}
	public void writeInt16Array(byte[] bytes)
	{
		buf.writeBytes(bytes);
	}
	
	/* Byte 24 Int Array */
	public byte[] readInt24Array()
	{
		return buf.readBytes(3).array();
	}
	public void writeInt24Array(byte[] bytes)
	{
		buf.writeBytes(bytes);
	}
	
	/* Byte 32 Int Array */
	public byte[] readInt32Array()
	{
		return buf.readBytes(4).array();
	}
	public void writeInt32Array(byte[] bytes)
	{
		buf.writeBytes(bytes);
	}
	
	/* Byte 64 Int Array */
	public byte[] readInt64Array()
	{
		return buf.readBytes(8).array();
	}
	public void writeInt64Array(byte[] bytes)
	{
		buf.writeBytes(bytes);
	}
	
	/* Byte Array Packet Data */
	public byte[] readByteArrayPacketData()
	{
		VLQ vlq = readVLQ();
		int len = (int) vlq.getValue();
		return buf.readBytes(len).array();
	}
	public void writeByteArrayPacketData(byte[] bytes)
	{
		buf.writeBytes(VLQ.createVLQ(bytes.length));
		buf.writeBytes(bytes);
	}
	
	/* Unsigned Byte */
	public byte readUnsignedByte()
	{
		return (byte) buf.readUnsignedByte();
	}
	public void writeByte(byte value)
	{
		buf.writeByte(value);
	}
	
	/* Unsigned Short*/
	public short readUnsignedShort()
	{
		return (short) buf.readUnsignedShort();
	}
	public void writeShort(short value)
	{
		buf.writeShort(value);
	}
	
	/* Unsigned Medium*/
	public int readUnsignedMedium()
	{
		return (int) buf.readUnsignedMedium();
	}
	public void writeMedium(int value)
	{
		buf.writeMedium(value);
	}
	
	/* Int & Unsigned Int*/
	public int readUnsignedInt()
	{
		return (int) buf.readUnsignedInt();
	}
	public int readInt()
	{
		return buf.readInt();
	}
	public void writeInt(int value)
	{
		buf.writeInt(value);
	}
	
	/* Long*/
	public Long readLong()
	{
		return buf.readLong();
	}
	public void writeLong(Long value)
	{
		buf.writeLong(value);
	}
	
	/* Float */
	public float readFloatInt32()
	{
		return buf.readFloat();
	}
	public void writeFloatInt32(float value)
	{
		buf.writeFloat(value);
	}
	
	
	/* VLQ */
	public VLQ readVLQ()
	{
		return VLQ.unsignedFromBuffer(buf);
	}
	public void writeVLQ(long value)
	{
		buf.writeBytes(VLQ.createVLQ(value));
	}
	
	/* sVLQ */
	public VLQ readSignedVLQ()
	{
		return VLQ.signedFromBuffer(buf);
	}
	public void writeSignedVLQ(long value)
	{
		buf.writeBytes(VLQ.createSignedVLQ(value));
	}

	/* String */
	public String readString()
	{
		try
		{
			return new String(readByteArrayPacketData(), Charset.forName("UTF-8"));
		}
		catch (Exception e)
		{
			return null;
		}
	}
	public void writeString(String str)
	{
		writeByteArrayPacketData(str.getBytes(Charset.forName("UTF-8")));
	}
	
	/* Variant */
	public Variant readVariant() throws Exception
	{
		return Variant.fromStream(this);
	}
	public void writeVariant(Variant var)
	{
		var.writeTo(this);
	}


	
}
