package org.starnub.managment;

import java.io.IOException;
import java.io.OutputStream;

/*
* This class's methods override the system output to extend the functionality.
* 
* This method will return nothing.
**/

public class SN_MultiOutputStream extends OutputStream {
	
		OutputStream[] outputStreams;
		
		public SN_MultiOutputStream(OutputStream... outputStreams)
		{
			this.outputStreams= outputStreams; 
		}
		
		@Override
		public void write(int b) throws IOException
		{
			for (OutputStream out: outputStreams)
				out.write(b);			
		}
		
		@Override
		public void write(byte[] b) throws IOException
		{
			for (OutputStream out: outputStreams)
				out.write(b);
		}
	 
		@Override
		public void write(byte[] b, int off, int len) throws IOException
		{
			for (OutputStream out: outputStreams)
				out.write(b, off, len);
		}
	 
		@Override
		public void flush() throws IOException
		{
			for (OutputStream out: outputStreams)
				out.flush();
		}
	 
		@Override
		public void close() throws IOException
		{
			for (OutputStream out: outputStreams)
				out.close();
		}
		public SN_MultiOutputStream ()
		{
		}
	}