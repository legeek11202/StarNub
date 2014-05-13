package org.starnub.core.util.stream;

import java.io.IOException;
import java.io.OutputStream;

/*
* This class's methods override the system output to extend the functionality.
* 
* This method will return nothing.
**/

public class MultiOutputStream extends OutputStream {
	
		OutputStream[] outputStreams;
		
		public MultiOutputStream ()
		{
		}
		
		public MultiOutputStream(OutputStream... outputStreams)
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
	}