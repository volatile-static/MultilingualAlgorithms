package src;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 产生编码并写入字节流
 * @see HuffmanDecoder
 */
public final class HuffmanEncoder {
	private BitOutputStream output;
	public CodeTree codeTree;
	/**
	 * 构造哈夫曼编码
	 * @param out 输出流
	 * @throws NullPointerException 流为null时抛出异常
	 */
	public HuffmanEncoder(BitOutputStream out) {
		output = Objects.requireNonNull(out);
	}
	
	/**
	 * 编码并写入字节流
	 * @param symbol 编码的输入
	 * @throws IOException 
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException 
	 */
	public void write(int symbol) throws IOException {
		if (codeTree == null)
			throw new NullPointerException("Code tree is null");
		List<Integer> bits = codeTree.getCode(symbol);
		for (int b : bits)
			output.write(b);
	}
}
