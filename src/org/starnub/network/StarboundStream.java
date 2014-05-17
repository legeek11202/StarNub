package org.starnub.network;

import java.nio.charset.Charset;

import org.starnub.datatypes.VLQ;
import org.starnub.datatypes.Variant;

import io.netty.buffer.ByteBuf;

public class StarboundStream
{
	
	private ByteBuf buf;
	
	public StarboundStream(ByteBuf buf)
	{
		this.buf = buf;
	}
	
	public VLQ readVLQ()
	{
		return VLQ.unsignedFromBuffer(buf);
	}
	
	public VLQ readSignedVLQ()
	{
		return VLQ.signedFromBuffer(buf);
	}
	
	public void writeVLQ(long value)
	{
		buf.writeBytes(VLQ.createVLQ(value));
	}
	
	public void writeSignedVLQ(long value)
	{
		buf.writeBytes(VLQ.createSignedVLQ(value));
	}
	
	public byte[] readByteArray()
	{
		
		VLQ vlq = readVLQ();
		
		int len = (int) vlq.getValue();
		
		return buf.readBytes(len).array();
		
	}
	
	public String readString()
	{
		try
		{
			return new String(readByteArray(), Charset.forName("UTF-8"));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public Variant readVariant() throws Exception
	{
		return Variant.fromStream(this);
	}

	public void writeVariant(Variant var)
	{
		var.writeTo(this);
	}
	
	public void writeString(String str)
	{
		writeByteArray(str.getBytes(Charset.forName("UTF-8")));
	}
	
	public void writeByteArray(byte[] bytes)
	{
		buf.writeBytes(VLQ.createVLQ(bytes.length));
		buf.writeBytes(bytes);
	}

	public ByteBuf getBuf()
	{
		return buf;
	}

	public void setBuf(ByteBuf buf)
	{
		this.buf = buf;
	}
	
}
