package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CodeTree {
	public final InternalNode root;
	
	//存储每个字符的编码
	private List<List<Integer>> codes;
	
	/**
	 * Constructs a code tree from the specified tree of nodes and specified symbol limit.
	 * Each symbol in the tree must have value strictly less than the symbol limit.
	 * @param root the root of the tree
	 * @param symbolLimit the symbol limit
	 * @throws NullPointerException if tree root is {@code null}
	 * @throws IllegalArgumentException if the symbol limit is less than 2, any symbol in the tree has
	 * a value greater or equal to the symbol limit, or a symbol value appears more than once in the tree
	 */
	public CodeTree(InternalNode root, int symbolLimit) {
		this.root = Objects.requireNonNull(root);
		if (symbolLimit < 2)
			throw new IllegalArgumentException("At least 2 symbols needed");
		
		codes = new ArrayList<List<Integer>>();  // Initially all null
		for (int i = 0; i < symbolLimit; i++)
			codes.add(null);
		buildCodeList(root, new ArrayList<Integer>());  // Fill 'codes' with appropriate data
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
			
		} else {
			throw new AssertionError("Illegal node type");
		}
	}
	
	/**
	 * Returns the Huffman code for the specified symbol, which is a list of 0s and 1s.
	 * @param symbol the symbol to query
	 * @return a list of 0s and 1s, of length at least 1
	 * @throws IllegalArgumentException if the symbol is negative, or no
	 * Huffman code exists for it (e.g. because it had a zero frequency)
	 */
	public List<Integer> getCode(int symbol) {
		if (symbol < 0)
			throw new IllegalArgumentException("Illegal symbol");
		else if (codes.get(symbol) == null)
			throw new IllegalArgumentException("No code for given symbol");
		else
			return codes.get(symbol);
	}
}
