package org.starnub.datatypes;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;

public class VLQ
{
	
	private int length;
	private long value;
	
	public VLQ(int length, long value)
	{
		this.length = length;
		this.value = value;
	}
	
	public static VLQ signedFromBuffer(ByteBuf buf)
	{
		
		VLQ value = unsignedFromBuffer(buf);
		
		long val = value.getValue();
		
		if ((value.getValue() & 1) == 0x00)
			val = (long) val >> 1;
		else
			val = -((long) (val >> 1) + 1);
	
		value.setValue(val);
		
		return value;
		
	}
	
	public static VLQ unsignedFromBuffer(ByteBuf buf)
	{
		
		long value = 0L;
		
		int length = 0;
		
		while (true)
		{
			
			byte tmp = buf.readByte();
			
			value = (value << 7) | (long) (tmp & 0x7f);
			
			length++;
			
			if ((tmp & 0x80) == 0)
				break;
			
		}
		
		return new VLQ(length, value);
		
	}
	
	public static byte[] createVLQ(long value)
    {

		List<Byte> result = new ArrayList<Byte>();

        if (value == 0)
            result.add((byte)0);

        while (value > 0)
        {

            byte tmp = (byte)(value & 0x7f);

            value >>= 7;

            if (value != 0)
                tmp |= 0x80;

            result.add(0, tmp);

        }

        if (result.size() > 1)
        {
            result.set(0, (byte)(result.get(0) | 0x80));
            result.set(result.size() - 1, (byte)(result.get(result.size() - 1) ^ 0x80));
        }
        
        byte[] res = new byte[result.size()];
        
        int ctr = 0;
        for (byte b : result)	
        {
        	res[ctr] = b;
        	ctr++;
        }
        
        return res;
        
    }
	
	public static byte[] createSignedVLQ(long value)
    {

        long result = Math.abs(value * 2);

        if (value < 0)
            result -= 1;

        return createVLQ((long)result);

    }
	
	public int getLength()
	{
		return length;
	}
	
	public void setLength(int length)
	{
		this.length = length;
	}
	
	public long getValue()
	{
		return value;
	}
	
	public void setValue(long value)
	{
		this.value = value;
	}
	
}
