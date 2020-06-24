package src;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @see BitOutputStream
 */
public final class BitInputStream implements AutoCloseable {
	private InputStream input;
	private int currentByte;
	private int numBitsRemaining;
	
	public BitInputStream(InputStream in) {
		input = Objects.requireNonNull(in);
		currentByte = 0;
		numBitsRemaining = 0;
	}
	
	/**
	 * \brief 从流中读一位，若结束返回-1
	 * @throws IOException 
	 */
	public int read() throws IOException {
		if (currentByte == -1)
			return -1;
		if (numBitsRemaining == 0) {
			currentByte = input.read();
			if (currentByte == -1)
				return -1;
			numBitsRemaining = 8;
		}
		if (numBitsRemaining <= 0)
			throw new AssertionError();
		numBitsRemaining--;
		return (currentByte >>> numBitsRemaining) & 1;
	}
	
	/**
	 * @return 下一位
	 * @throws IOException
	 * @throws EOFException 
	 */
	public int readNoEof() throws IOException {
		int result = read();
		if (result != -1)
			return result;
		else
			throw new EOFException();
	}
	
	public void close() throws IOException {
		input.close();
		currentByte = -1;
		numBitsRemaining = 0;
	}
}
