package canvasgen.app.compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class WriteFile {
	public static void writeFile1(String file, ArrayList<String> lines) throws IOException {
		File fout = new File(file);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		for(String line: lines){
			bw.write(line);
			bw.newLine();
		}
		
		bw.close();
	}
}
