package numbers.distance;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * A PointInputStream lets an application read a point from an underlying input stream.
 * 
 * @author Fredrik Andersson
 *
 */
public class PointInputStream implements Closeable {

	private final InputStream inputStream;
	private byte[] buffer;
	
	/**
	 * Constructs a new stream using the underlying stream specified.
	 * 
	 * @param inputStream
	 */
	public PointInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void close() throws IOException {
		inputStream.close();
	}

	/**
	 * Reads a point from the underlying stream.
	 * 
	 * @return the next point or null when stream is empty
	 * @throws IOException
	 */
	public Point readPoint() throws IOException {
		if (buffer == null) {
            this.buffer = new byte[4];
        }
		int res = inputStream.read(buffer);
		if (res != -1) {
			ByteBuffer in = ByteBuffer.wrap(buffer);
			return readPoint(in);
		} 
		return null;
	}
	
	/**
	 * Reads a point from the supplied byte buffer.
	 * 
	 * @param buffer
	 * @return a point
	 */
	private Point readPoint(final ByteBuffer buffer) {
		final int x = readInt(buffer);
		final int y = readInt(buffer);
		
		return new Point(x, y);
	}
	
	/**
	 * Reads an 16 bit signed integer from the supplied buffer
	 * 
	 * @param buffer
	 * @return an integer
	 */
	private int readInt(final ByteBuffer buffer) {
		final byte byte1 = buffer.get();
		final byte byte2 = buffer.get();
		
		return (byte1 << 8) + (byte2 & 0xFF);
	}

}
