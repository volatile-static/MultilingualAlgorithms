package src;

import java.io.IOException;
import java.io.OutputStream;

public final class HuffmanDecompress {
	public static CanonicalCode readCodeLengthTable(BitInputStream in) throws IOException {
		int[] codeLengths = new int[257];
		for (int i = 0; i < codeLengths.length; i++) {
			// 大端模式
			int val = 0;
			for (int j = 0; j < 8; j++)
				val = (val << 1) | in.readNoEof();
			codeLengths[i] = val;
		}
		return new CanonicalCode(codeLengths);
	}
	
	public static void decompress(CodeTree code, BitInputStream in, OutputStream out) throws IOException {
		HuffmanDecoder dec = new HuffmanDecoder(in);
		dec.codeTree = code;
		while (true) {
			int symbol = dec.read();
			if (symbol == 256)  // EOF 
				break;
			out.write(symbol);
		}
	}
}
