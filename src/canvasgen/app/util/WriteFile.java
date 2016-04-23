package canvasgen.app.util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import canvasgen.app.view.canvasview.canvasObject.CanvasObject;

public class WriteFile {
	
	public static void writeHashToFile(String file, HashMap<String, String> records){
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
			
			//create file output stream
			URL resourceUrl = file.getClass().getResource(file);
			File file2 = new File(resourceUrl.toURI());
			output = new FileOutputStream(file2);
			
			//iterate over hash and set all keys and values
			Set set = records.entrySet();
			Iterator i = set.iterator();	     
		    while(i.hasNext()) {
		    	Map.Entry me = (Map.Entry)i.next();
		    	prop.setProperty((String)me.getKey(), (String)me.getValue());
		    }
		    
			// save properties to files folder
			prop.store(output, null);
		
		} catch (IOException io) {
			io.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	//writes to file using linked hash
	public static void writeLinkedHashToFile(String file, LinkedHashMap<String, String> records){
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {			
			//create file output stream
			output = new FileOutputStream(file);
		
			ArrayList<String> values = new ArrayList<String>();
			ArrayList<String> keys = new ArrayList<String>();
		    
			//extract values
			Collection c = records.values();
		    Iterator itr = c.iterator();
		    while (itr.hasNext())
		      values.add((String)itr.next());
		    
		    //extract keys
		    Set st = records.keySet();
		    Iterator itr2 = st.iterator();
		    while(itr2.hasNext())
		      keys.add((String)itr2.next());
		    
		    //write key and values to file
		    for(int i = 0; i < keys.size(); i++)
		    	prop.setProperty(keys.get(i),values.get(i));		    
		    
			// save properties to files folder
			prop.store(output, null);
		
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	public static void writeObjectsToFile(String file, HashMap<String, CanvasObject> hashMap){
		
		FileOutputStream fileStream;
		try {
			// Write to disk with FileOutputStream
			fileStream = new FileOutputStream(file);
			
			// Write object with ObjectOutputStream
			ObjectOutputStream objectStream = new
			ObjectOutputStream(fileStream);
			
			// Write object out to disk
			objectStream.writeObject(hashMap);
			
			objectStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	
	}
}
