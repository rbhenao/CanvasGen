package canvasgen.app.model;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import canvasgen.app.util.ReadFile;
import canvasgen.app.util.WriteFile;

public class ShortcutsModel extends Model{
	
	public String name = "ShortcutsModel";
	private Color backGroundColor = Color.white;
	private Dimension minimumSize = new Dimension(450, 350);
	private String title = "Shortcut Window";
	
	//these two arrays must be same length;
	private String[] actionFields = {"New...","Open...","Save","Save as...","Undo","Redo","Clear","Ruler","Grid Lines",
			  "Coordinates", "Translate", "Scale", "Rotate", "Change Color",
			  "Add Mouse Event", "Add Keyboard Event", "Add Touch Event", "Add Link Button",
			  "Fullscreen"};
	//these are the default values if loading the file fails
	private String[] shortcutFields = {"ctrl+N","ctrl+O","ctrl+S","ctrl+shft+S","ctrl+Z","ctrl+Y","ctrl+D","ctrl+R","ctrl+G",
			  "ctrl+P", "ctrl+T", "ctrl+shft+S", "ctrl+shft+R", "ctrl+shft+C",
			  "ctrl+M", "ctrl+K", "ctrl+shft+T", "ctrl+shft+L",
			  "F11"};
	
	public ShortcutsModel(){
		super();
		this.modelObjects.put("backgroundColor", backGroundColor);
		this.modelObjects.put("minSize", minimumSize);
		this.modelObjects.put("title", title);
		this.modelObjects.put("actionFields", actionFields);
		this.modelObjects.put("shortcutFields", shortcutFields);
		
		getShortcutValues();
	}
	
	private void getShortcutValues(){
		HashMap<String, String> shortcutFields = ReadFile.getHash("/files/shortcuts.properties");
		if(shortcutFields!= null && shortcutFields.size() == this.shortcutFields.length){
			String[] actionFieldsArray = new String[this.shortcutFields.length];
			String[] shortcutFieldsArray = new String[this.shortcutFields.length];
					
			int index = 0;
			Set keys = shortcutFields.keySet();
		    for (Iterator i = keys.iterator(); i.hasNext();) {
		      String key = (String) i.next();
		      String value = (String) shortcutFields.get(key);
		      actionFieldsArray[index] = key;
		      shortcutFieldsArray[index] = value;
		      index++;
		      //System.out.println(key + ": " + value);
		    }
		    this.actionFields = actionFieldsArray;
			this.shortcutFields = shortcutFieldsArray;
			this.modelObjects.put("shortcutFields", this.shortcutFields);
			this.modelObjects.put("actionFields", this.actionFields);
		}else{
			System.out.println("error reading shortcut file");
			//will use default shortcut values
		}
	}
	
	@Override
	public void change(String key, Object value) {		
		
		if(key.equals("shortcutFields")){
			this.modelObjects.put("shortcutFields", (String[])value);
			if( ((String[])value).length == ((String[])this.modelObjects.get("actionFields")).length ){
				writeNewShortcutProperties();
			}else{
				System.out.println("the keys need to be the same length as the values");
				//the keys need to be the same length as the values
				//one key for every value
			}			
		}
		
		this.notifyObservers();
	}
	
	private void writeNewShortcutProperties(){
		LinkedHashMap<String,String> shortcutRecords = new LinkedHashMap<String,String>();
		String[] components = (String[])this.modelObjects.get("actionFields");
		String[] shortcuts = (String[])this.modelObjects.get("shortcutFields");
		
		for(int i = 0; i < components.length; i++)
			shortcutRecords.put(components[i], shortcuts[i]);
		
		WriteFile.writeLinkedHashToFile("files" +File.separatorChar+"shortcuts.properties", shortcutRecords);
	}

}

