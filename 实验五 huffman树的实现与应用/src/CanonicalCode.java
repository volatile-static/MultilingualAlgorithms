package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
public final class CanonicalCode {
	private int[] codeLengths;
	public CanonicalCode(int[] codeLens) {
		Objects.requireNonNull(codeLens);
		
		codeLengths = codeLens.clone();
		Arrays.sort(codeLengths);
		System.arraycopy(codeLens, 0, codeLengths, 0, codeLens.length);
	}
	
	
	// 生成范式哈夫曼编码
	public CanonicalCode(CodeTree tree, int symbolLimit) {
		Objects.requireNonNull(tree);
		codeLengths = new int[symbolLimit];
		buildCodeLengths(tree.root, 0);
	}
	
	private void buildCodeLengths(Node node, int depth) {
		if (node instanceof InternalNode) {
			InternalNode internalNode = (InternalNode)node;
			buildCodeLengths(internalNode.leftChild , depth + 1);
			buildCodeLengths(internalNode.rightChild, depth + 1);
		} else if (node instanceof Leaf) {
			int symbol = ((Leaf)node).symbol;
			codeLengths[symbol] = depth;
		} 
	}
	
	public int getSymbolLimit() {
		return codeLengths.length;
	}
	
	// 获取编码长度
	public int getCodeLength(int symbol) {
		if (symbol < 0 || symbol >= codeLengths.length)
			throw new IllegalArgumentException("Symbol out of range");
		return codeLengths[symbol];
	}
	
	//*生成哈夫曼树
	public CodeTree toCodeTree() {
		List<Node> nodes = new ArrayList<Node>();
		for (int i = max(codeLengths); i >= 0; i--) {  
			List<Node> newNodes = new ArrayList<Node>();
			
			if (i > 0) {
				for (int j = 0; j < codeLengths.length; j++) {
					if (codeLengths[j] == i)
						newNodes.add(new Leaf(j));
				}
			}
			
			for (int j = 0; j < nodes.size(); j += 2)
				newNodes.add(new InternalNode(nodes.get(j), nodes.get(j + 1)));
			nodes = newNodes;
		}
		return new CodeTree((InternalNode)nodes.get(0), codeLengths.length);
	}
	
	
	// 返回序列中的最大值
	private static int max(int[] array) {
		int result = array[0];
		for (int x : array)
			result = Math.max(x, result);
		return result;
	}
}
