import java.util.ArrayList;

public class Node {
	protected boolean isLeafNode;
	protected ArrayList<Double> keys;
	Node parent;
	Node(){
		keys = new ArrayList<Double>(100);
		parent = null;
		isLeafNode = false;
	}
	public boolean isOverflowed() {
		return ( keys.size() > BPlusTree.D) ;
	}
}