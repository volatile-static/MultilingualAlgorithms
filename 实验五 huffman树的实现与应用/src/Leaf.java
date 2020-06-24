package src;


/**
 * 叶子结点
 */
public final class Leaf extends Node {
	public final int symbol;  // 非负
	public Leaf(int sym) {
		if (sym < 0)
			throw new IllegalArgumentException("Symbol value must be non-negative");
		symbol = sym;
	}
}
