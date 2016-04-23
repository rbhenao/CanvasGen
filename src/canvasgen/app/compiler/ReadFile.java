package canvasgen.app.compiler;

import java.io.*;
import java.util.ArrayList;

public class ReadFile {
	
	public static ArrayList<String> readFile1(String fin) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		
		//FileInputStream fis = new FileInputStream(fin);

		InputStream fis = null;
		fis = fin.getClass().getResourceAsStream(fin);
	 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = null;
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
	 
		br.close();
		
		return lines;
	}
}
