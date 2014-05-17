package org.starnub.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class Zlib
{
	
	public static byte[] decompress(byte[] data) throws IOException
	{
		
		Inflater decomp = new Inflater();
		decomp.setInput(data);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		
		byte[] buf = new byte[1024];
		while (!decomp.finished())
		{
			try
			{
				int count = decomp.inflate(buf);
				bos.write(buf, 0, count);
			}
			catch (DataFormatException e)
			{
			}
		}

		bos.close();
		
		return bos.toByteArray();
		
	}
	
}
