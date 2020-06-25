package src;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 码表
 * @see CodeTree
 */
public final class FrequencyTable {
	private int[] frequencies;
	public FrequencyTable(int[] freqs) {
		Objects.requireNonNull(freqs);
		if (freqs.length < 2)
			throw new IllegalArgumentException("At least 2 symbols needed");
		frequencies = freqs.clone(); 
		for (int x : frequencies) {
			if (x < 0)
				throw new IllegalArgumentException("Negative frequency");
		}
	}
	
	public int getSymbolLimit() {
		return frequencies.length;
	}

	/**
	 * 增加字符出现次数
	 * @throws IllegalArgumentException 
	 * @throws IllegalStateException 
	 */
	public void increment(int symbol) {
		checkSymbol(symbol);
		if (frequencies[symbol] == Integer.MAX_VALUE)
			throw new IllegalStateException("Maximum frequency reached");
		frequencies[symbol]++;
	}
	
	private void checkSymbol(int symbol) {
		if (symbol < 0 || symbol >= frequencies.length)
			throw new IllegalArgumentException("Symbol out of range");
	}
	/**
	 * @return 生成码表对应的哈夫曼树
	 */
	public CodeTree buildCodeTree() {
		Queue<NodeWithFrequency> pqueue = new PriorityQueue<NodeWithFrequency>();
		
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] > 0)
				pqueue.add(new NodeWithFrequency(new Leaf(i), i, frequencies[i]));
		}
		
		for (int i = 0; i < frequencies.length && pqueue.size() < 2; i++) {
			if (frequencies[i] == 0)
				pqueue.add(new NodeWithFrequency(new Leaf(i), i, 0));
		}
		if (pqueue.size() < 2)
			throw new AssertionError();
		
		while (pqueue.size() > 1) {
			NodeWithFrequency x = pqueue.remove();
			NodeWithFrequency y = pqueue.remove();
			pqueue.add(new NodeWithFrequency(
				new InternalNode(x.node, y.node),
				Math.min(x.lowestSymbol, y.lowestSymbol),
				x.frequency + y.frequency));
		}
		
		return new CodeTree((InternalNode)pqueue.remove().node, frequencies.length);
	}
	
	private static class NodeWithFrequency implements Comparable<NodeWithFrequency> {
		public final Node node;
		public final int lowestSymbol;
		public final long frequency;
		
		public NodeWithFrequency(Node nd, int lowSym, long freq) {
			node = nd;
			lowestSymbol = lowSym;
			frequency = freq;
		}
		
		public int compareTo(NodeWithFrequency other) {
			if (frequency < other.frequency)
				return -1;
			else if (frequency > other.frequency)
				return 1;
			else if (lowestSymbol < other.lowestSymbol)
				return -1;
			else if (lowestSymbol > other.lowestSymbol)
				return 1;
			else
				return 0;
		}
	}
}
