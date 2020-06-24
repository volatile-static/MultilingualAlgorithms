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
		// 首先读取文件并统计出现次数
		FrequencyTable freqs = HuffmanCompress.getFrequencies(inputFile);
		freqs.increment(256); // EOF 出现1次
		CodeTree code = freqs.buildCodeTree();
		CanonicalCode canonCode = new CanonicalCode(code, freqs.getSymbolLimit());
		// 范式哈夫曼编码
		code = canonCode.toCodeTree();
		// 重新读取文件并生成哈夫曼编码
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
