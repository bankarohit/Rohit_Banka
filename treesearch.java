import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;


class treesearch {
	
public static void main(String args[]){  
	  String IfileName = "input.txt";
		String line = null;
		String OfileName = "/Users/sharad/Desktop/output.txt";
		try {
			FileInputStream inputstream = new FileInputStream(IfileName);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
			BufferedWriter output = null;
			File file = new File(OfileName);
			TreeMap<Double,List<String>> hm = new TreeMap<Double,List<String>>();  
			output = new BufferedWriter(new FileWriter(file));
			int m = Integer.parseInt(bufferedReader.readLine());
			while ((line = bufferedReader.readLine()) != null) {
				line = line.toLowerCase();
				if( line.startsWith("insert") ) {
					line = line.replaceFirst("insert", "");
					int idx1 = line.indexOf(",");
					Double key = Double.parseDouble(line.substring(1, idx1));
					int idx2 = line.indexOf(")");
					String val = line.substring(idx1+1, idx2);
					if(hm.containsKey(key))
						hm.get(key).add(val);
					}
				else {
					line = line.replaceFirst("search", "");
					int idx1 = line.indexOf(",");
					if( idx1 == -1) {
						int idx2 = line.indexOf(")");
						double key = Double.parseDouble(line.substring(1, idx2));
						List<String> a = new ArrayList<String>();
						if( hm.containsKey(key)) {
							for(int i = 0; i < a .size();i++ ) {
							output.write(a.get(i));
							output.write(",");
						}
						}
						else
							output.write("NULL");
						output.newLine();
					}
					else {
						double key1 = Double.parseDouble(line.substring(1, idx1));
						int idx2 = line.indexOf(")");
						double key2 = Double.parseDouble(line.substring(idx1+1, idx2));
						List<String> a = new ArrayList<String>();
						Set<Double> s = new HashSet<Double>();
						NavigableMap<Double,List<String>> sub = new TreeMap<Double,List<String>>();  
						sub = hm.subMap(key1, true, key2, true);
						while(!sub.isEmpty()) {
							String key= sub.get(0).toString();
							String val = sub.get(0).toString();
							sub.remove(0);
							output.write("(" + key + "," + val+")");
						}
						output.newLine();
					}
				}
		}	
		bufferedReader.close();
		output.close();
	} 
	catch (FileNotFoundException ex) {
		ex.printStackTrace();
	} 
	catch (IOException ex) {
		ex.printStackTrace();
	}
	
}
}
 
