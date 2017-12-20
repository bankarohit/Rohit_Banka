
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.ArrayList;




public class BPlusTree {
	public Node  root;
	public static int D ;
	public BPlusTree(int m) {
		D = m;
		root = null;
	}
	
	
	public String search(double key) {
		// Return if empty tree or key
		String out = new String();
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		if(root == null) {
			return "";
		}
		// Look for leaf node that key is pointing to
		LeafNode leaf = treeSearch(root, key);
		boolean flag = true;
		while(flag) {
		for(int i=0; i<leaf.keys.size(); i++) {
			if(key > leaf.keys.get(i)) {
				flag = false;
				break;
			}
			if(key == leaf.keys.get(i) ) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(temp.size() -1, leaf.values.get(i).toString());
				res.add(0, temp);
			}
		}
		if(flag)
			if(leaf.nextLeaf == null ) 
				flag = false;
			else
				leaf = leaf.nextLeaf;
		}
		return res.toString();
	}
	
	private LeafNode treeSearch(Node node, double key) {
		if(node.isLeafNode) {
			return (LeafNode) node;
		} 
		else {
			IndexNode index = (IndexNode) node;
			if(key < index.keys.get(0) )
				return treeSearch((Node )index.children.get(0), key);
		
			else if ( key >=  index.keys.get(node.keys.size() -1) ) 
				return treeSearch((Node )index.children.get(index.children.size()-1), key);
			else {
				for(int i=0; i<index.keys.size()-1; i++) {
					if( key >= index.keys.get(i)  && key < index.keys.get(i) )
						return treeSearch((Node) index.children.get(i+1), key);
				}
 			}
			return null;
		}
	} 
	
	public String search_range(double key1, double key2) {
		// Return if empty tree or key
		String out ="";
		
		if(root == null) {
			return "";
		}
		// Look for leaf node that key is pointing to
		LeafNode leaf = (LeafNode)treeSearch(root, key1);

		boolean flag = true;
		while(flag) {
		for(int i=0; i<leaf.keys.size(); i++) {
			if( key1 <= leaf.keys.get(i) && key2 <= leaf.keys.get(i))
				out = out + "(" + leaf.keys.get(i).toString() + ", " + leaf.values.get(i).toString();
			if( key2 < leaf.keys.get(i) ) {
				flag = false;
				break;
			}
		}
		leaf = leaf.nextLeaf;
		}
		return out;
	}

	
	public void insert(double key, String value) {
		LeafNode  newLeaf = new LeafNode (key, value);
		if(root == null || root.keys.size() == 0) {
			root = newLeaf;
			return;
		}
		LeafNode leaf = treeSearch(root, key);
		leaf.insertSorted(key, value);
		if( leaf.isOverflowed() ) { 
			IndexNode idx2 = null ;
			if(leaf.parent != null)
				idx2 = (IndexNode) leaf.parent;
			IndexNode idx = splitLeafNode(leaf);
			if(idx2 != null ) idx2.insertSorted(idx);
		}
		return;
	}

	
	private IndexNode splitLeafNode (LeafNode leafnode) {
		LeafNode newLeaf = (LeafNode) new LeafNode();
		newLeaf.isLeafNode = true;
		for(int i=D/2; i<=D; i++) {
			newLeaf.keys.add(leafnode.keys.get(i));
			newLeaf.values.add(leafnode.values.get(i));
		}
		for(int i=D/2; i<=D; i++) {
			leafnode.keys.remove(leafnode.keys.size()-1);
			leafnode.values.remove(leafnode.values.size()-1);
		}
		IndexNode newIndex = (IndexNode) new IndexNode();
		newIndex.isLeafNode = false;
		//System.out.println(newLeaf.keys.get(0).toString());
		newIndex.keys.add(0,newLeaf.keys.get(0));
		newIndex.children.add(0,newLeaf);
		leafnode.nextLeaf = newLeaf;
		newLeaf.previousLeaf = leafnode;
		return newIndex;
	}
	
	
	
}