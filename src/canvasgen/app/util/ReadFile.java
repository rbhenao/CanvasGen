package canvasgen.app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import canvasgen.app.view.canvasview.canvasObject.CanvasObject;
 
public class ReadFile {
  
	public static String[] getValueArray(String file){
		
		ArrayList<String> values = new ArrayList<String>();
		Properties prop = new Properties();
		InputStream input = null;
 
		try {
 
			input = new FileInputStream(file);
 
			// load the properties file
			prop.load(input);
 
			Enumeration e = prop.propertyNames();

		    while (e.hasMoreElements()) {
		      String key = (String) e.nextElement();
		      values.add(prop.getProperty(key));
		    }
		    
		    String[] vals = new String[values.size()];
		    
		    for(int i = 0; i < vals.length; i++)
		    	vals[i] = values.get(i);
		    
		    return vals;
 
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}		
	}
	
	public static String[] getKeyArray(String file){
		
		ArrayList<String> values = new ArrayList<String>();
		Properties prop = new Properties();
		InputStream input = null;
 
		try {
 
			input = new FileInputStream(file);
 
			// load the properties file
			prop.load(input);
 
			Enumeration e = prop.propertyNames();

			while (e.hasMoreElements()) {
			      String key = (String) e.nextElement();
			      values.add(prop.getProperty(key));
			}
		    
		    String[] vals = new String[values.size()];
		    
		    for(int i = 0; i < vals.length; i++)
		    	vals[i] = values.get(i);
		    
		    return vals;
 
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}		
	}
	
	public static HashMap<String, String> getHash(String file){
		HashMap<String,String> values = new HashMap<String,String>();
		Properties prop = new Properties();
		InputStream input = null;
 
		try {


			System.out.println(file);

			input = file.getClass().getResourceAsStream(file);
 
			//input = new FileInputStream(file);
 
			// load the properties file
			prop.load(input);
			
			//place keys and values into hash
			Enumeration e = prop.propertyNames();
		    while (e.hasMoreElements()) {
		      String key = (String) e.nextElement();
		      values.put(key, prop.getProperty(key));
		    }
		    
		    return values;
 
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}
	
	public static HashMap<String, CanvasObject> getObjects(String file){
		// Read from disk using FileInputStream
		FileInputStream f_in;
		try {
			f_in = new FileInputStream(file);
			// Read object using ObjectInputStream
			ObjectInputStream obj_in = new ObjectInputStream (f_in);

			// Read an object
			Object obj = obj_in.readObject();
			
			obj_in.close();
			return (HashMap<String, CanvasObject>) obj;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
