import java.io.*;
import java.util.ArrayList;


public class Main {

	public static void intmain(String[] args) {
		String IfileName = "./input.txt";
		String line = null;
		String OfileName = "/Users/sharad/Desktop/output.txt";
		try {
			FileInputStream inputstream = new FileInputStream(IfileName);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
			BufferedWriter output = null;
			File file = new File(OfileName);
            output = new BufferedWriter(new FileWriter(file));
			int m = Integer.parseInt(bufferedReader.readLine());
			BPlusTree bpt = new BPlusTree(m);
			while ((line = bufferedReader.readLine()) != null) {
				line = line.toLowerCase();
				if( line.startsWith("insert") ) {
					line = line.replaceFirst("insert", "");
					int idx1 = line.indexOf(",");
					Double key = Double.valueOf(line.substring(1, idx1));
					int idx2 = line.indexOf(")");
					String val = line.substring(idx1+1, idx2);
					bpt.insert(key, val);

				}
				else {
					line = line.replaceFirst("search", "");
					int idx1 = line.indexOf(",");
					if( idx1 == -1) {
						int idx2 = line.indexOf(")");
						double key = Double.valueOf(line.substring(1, idx2));
						String a;
						a = bpt.search(key);
						output.write(a);
						output.newLine();
					}
					else {
						Double key1 = Double.valueOf(line.substring(1, idx1));
						int idx2 = line.indexOf(")");
						Double key2 = Double.valueOf(line.substring(idx1+1, idx2));
						String a;
						a = bpt.search_range(key1,key2);
						output.write(a);
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
};
