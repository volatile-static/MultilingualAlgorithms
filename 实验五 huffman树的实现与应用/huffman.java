import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import src.*;

public abstract class huffman {
	public static void main(String[] args) throws IOException {
		File txtin = new File("G:\\哈夫曼.txt");
		File dat = new File("G:\\哈夫曼.dat");
		File txtout = new File("G:\\译文-哈夫曼.txt");
		
		Compress(txtin, dat);
		Decompress(dat, txtout);
	}
	
	private static void Compress(File inputFile, File outputFile) throws IOException {
		// Read input file once to compute symbol frequencies.
		// The resulting generated code is optimal for static Huffman coding and also canonical.
		FrequencyTable freqs = HuffmanCompress.getFrequencies(inputFile);
		freqs.increment(256); // EOF symbol gets a frequency of 1
		CodeTree code = freqs.buildCodeTree();
		CanonicalCode canonCode = new CanonicalCode(code, freqs.getSymbolLimit());
		// Replace code tree with canonical one. For each symbol,
		// the code value may change but the code length stays the same.
		code = canonCode.toCodeTree();
// Read input file again, compress with Huffman coding, and write output file
		try (InputStream in = new BufferedInputStream(new FileInputStream(inputFile))) {
			try (BitOutputStream out = new BitOutputStream(
					new BufferedOutputStream(new FileOutputStream(outputFile)))) {
				HuffmanCompress.writeCodeLengthTable(out, canonCode);
				HuffmanCompress.compress(code, in, out);
			}
		}
		catch (IOException e) {
			
		}
	}

	private static void Decompress(File inputFile, File outputFile) {
		try (BitInputStream in = new BitInputStream(new BufferedInputStream(new FileInputStream(inputFile)))) {
			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile))) {
				CanonicalCode canonCode = HuffmanDecompress.readCodeLengthTable(in);
				CodeTree code = canonCode.toCodeTree();
				HuffmanDecompress.decompress(code, in, out);
			}
		}
		catch (IOException e) {
			
		}
	}
}
