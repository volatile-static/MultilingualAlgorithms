package src;

import java.io.IOException;
import java.util.Objects;


/**
 * 读取哈夫曼编码并解码输出
 * @see HuffmanEncoder
 */
public final class HuffmanDecoder {
	private BitInputStream input;
	public CodeTree codeTree;
	/**
	 * Constructs a Huffman decoder based on the specified bit input stream.
	 * @param in the bit input stream to read from
	 * @throws NullPointerException if the input stream is {@code null}
	 */
	public HuffmanDecoder(BitInputStream in) {
		input = Objects.requireNonNull(in);
	}
	/**
	 * 从输入流读取编码并还原译文
	 * @return 流
	 */
	public int read() throws IOException {
		InternalNode currentNode = codeTree.root;
		while (true) {
			int temp = input.readNoEof();
			Node nextNode;
			if      (temp == 0) nextNode = currentNode.leftChild;
			else if (temp == 1) nextNode = currentNode.rightChild;
			else throw new AssertionError("Invalid value from readNoEof()");
			
			if (nextNode instanceof Leaf)
				return ((Leaf)nextNode).symbol;
			else if (nextNode instanceof InternalNode)
				currentNode = (InternalNode)nextNode;
		}
	}
}
