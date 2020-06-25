package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CodeTree {
	public final InternalNode root;
	
	//存储每个字符的编码
	private List<List<Integer>> codes;

	public CodeTree(InternalNode root, int symbolLimit) {
		this.root = Objects.requireNonNull(root);
		
		codes = new ArrayList<List<Integer>>();  
		for (int i = 0; i < symbolLimit; i++)
			codes.add(null);
		buildCodeList(root, new ArrayList<Integer>()); 
	}
	
	private void buildCodeList(Node node, List<Integer> prefix) {
		if (node instanceof InternalNode) {
			InternalNode internalNode = (InternalNode)node;
			
			prefix.add(0);
			buildCodeList(internalNode.leftChild , prefix);
			prefix.remove(prefix.size() - 1);
			
			prefix.add(1);
			buildCodeList(internalNode.rightChild, prefix);
			prefix.remove(prefix.size() - 1);
			
		} else if (node instanceof Leaf) {
			Leaf leaf = (Leaf)node;
			if (leaf.symbol >= codes.size())
				throw new IllegalArgumentException("Symbol exceeds symbol limit");
			if (codes.get(leaf.symbol) != null)
				throw new IllegalArgumentException("Symbol has more than one code");
			codes.set(leaf.symbol, new ArrayList<Integer>(prefix));
			
		}
	}
	
	/**
	 * 查询一个符号的哈夫曼编码
	 * @param symbol 要查询的符号
	 * @return 哈夫曼编码
	 */
	public List<Integer> getCode(int symbol) {
		return codes.get(symbol);
	}
}
