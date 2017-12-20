
import java.util.ArrayList;
public class IndexNode extends Node{

	// m nodes
	protected ArrayList<Node> children; // m+1 children


	IndexNode(){
		super();
		this.isLeafNode = false;
		this.children = new ArrayList<Node>(100);
	}
	
	private IndexNode splitIndexNode (IndexNode idx) {
		IndexNode newIndex = (IndexNode) new Node();
		if (idx.parent == null )
		{
			IndexNode idx1 =(IndexNode) new Node();
			idx1.isLeafNode = false;
			int size = idx.keys.size();
			for( int i = size /2 + 1; i < size; i++ ) {
				 idx1.keys.add(idx.keys.get(i) );
				 idx1.children.add(idx.children.get(i+1));
			}
			for( int i = size /2 + 1; i < size; i++ ) {
				 idx.keys.remove(idx.keys.get(i) );
				 idx.children.remove(idx.children.get(i+1));
			}
			IndexNode newroot = (IndexNode) new Node();
			newroot.isLeafNode = false;
			newroot.parent = null;
			newroot.keys.add(idx.keys.get(size/2));
			idx.keys.remove(size/2);
			newroot.children.add(0, idx);
			newroot.children.add(1,idx1);
			idx.parent = newroot;
			idx1.parent = newroot;
			return null;
		}
		else
		{
			IndexNode parent = (IndexNode)idx.parent;
			IndexNode idx1 =(IndexNode) new Node();
			idx1.isLeafNode = false;
			int size = idx.keys.size();
			for( int i = size /2 ; i < size; i++ ) {
				 idx1.keys.add(idx.keys.get(i) );
				 idx1.children.add(idx.children.get(i+1));
			}
			for( int i = size /2; i < size; i++ ) {
				 idx.keys.remove(idx.keys.get(i) );
				 idx.children.remove(idx.children.get(i+1));
			}
			parent.insertSorted(idx1);
			if (parent.isOverflowed())
				return splitIndexNode(parent);
			else
				return parent;
		}
	}

	public void insertSorted(IndexNode idx) {
		int i = 0; int j = 0;
		while(i < idx.keys.size() )
		{
			while(j < this.keys.size()) {
				if( this.keys.get(j) < idx.keys.get(i) )
					j++;
				else
					this.keys.add(j, idx.keys.get(i));
			}
			i++;j++;
		}
	}
}



