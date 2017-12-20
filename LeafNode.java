import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class LeafNode extends Node {
	protected ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
	protected LeafNode nextLeaf;
	protected LeafNode previousLeaf;

	public LeafNode() {
		super();
		this.isLeafNode = true;
		this.values = new ArrayList<ArrayList<String>>(100);
		this.nextLeaf = null;
		this.previousLeaf = null;
	}
	public LeafNode(double key, String value) {
		isLeafNode = true;
		keys.set(0,key);
		values = new ArrayList<ArrayList<String>>(100);
		ArrayList<String> temp = new ArrayList<String>(100);
		temp.add(value);
		values.add(temp);
	}
	
	public void insertSorted(double key, String value) {
		if( key < keys.get(0)) {
			keys.add(0,key);
			ArrayList<String> temp = new ArrayList<String> ();
			temp.add(value);
			values.add(0,temp);
			return;
		}
		if(key > keys.get(keys.size() -1) ) {
			keys.add(key);
			ArrayList<String> temp = new ArrayList<String> ();
			temp.add(value);
			values.add(temp);
			return;
		}
		 else {
			int i = 0;
			while(this.keys.get(i) < key )
				i++;
			keys.add(i, key);
			ArrayList<String> temp = new ArrayList<String> (100);
			temp.add(value);
			values.add(i,temp);
		}
	}




}