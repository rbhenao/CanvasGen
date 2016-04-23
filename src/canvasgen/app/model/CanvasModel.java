package canvasgen.app.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import canvasgen.app.view.canvasview.canvasObject.CanvasObject;
import canvasgen.app.view.canvasview.canvasObject.CanvasState;
import canvasgen.app.view.canvasview.canvasObject.Circle;
import canvasgen.app.view.workspaceview.Tool;


public class CanvasModel extends Model{
	
	//static properties. same across all canvas objects
	public static int count = 0; // stores the count of repeated names
	private static final int MAX_POINTER = 10; //maximum pointer value
	
	private String canvasName   = "CanvasGen"+count; //default name
	private int canvasHeight = 300; //default
	private int canvasWidth  = 300; //default
	
	//properties of the canvas objects
	private int pointer; // pointer that points to current snapshot
	private int key; //unique key for every hash	
	private ArrayList<HashMap<Integer,CanvasObject>> stateList; //holds all of the canvas state snapshots. Limit this to 5
	
	public CanvasModel(String name, int width, int height){
		super();
		this.modelObjects.put("canvasName", name);
		this.modelObjects.put("canvasWidth", width);
		this.modelObjects.put("canvasHeight", height);	
		
		this.key = 0;
		this.pointer = 0;
	}
	
	@Override
	public void change(String key, Object value) {
		this.modelObjects.put(key, value);
		this.notifyObservers();
	}
	
	public void addCanvasObject(CanvasObject canvasObject){		
		if(stateList == null){
			HashMap<Integer, CanvasObject> canvasObjects = 
					new HashMap<Integer, CanvasObject>();
			canvasObjects.put(key, canvasObject);
			stateList = new ArrayList<HashMap<Integer, CanvasObject>>();
			stateList.add(canvasObjects);
		}else if(pointer < CanvasModel.MAX_POINTER){
			HashMap<Integer, CanvasObject> canvasObjectsClone = getClonedMap();
			canvasObjectsClone.put(key, canvasObject);
			if(pointer+1 == stateList.size()){
				stateList.add(canvasObjectsClone);
			}else{
				stateList.set(pointer+1, canvasObjectsClone);
			}
			pointer++;
		}else{
			HashMap<Integer, CanvasObject> canvasObjectsClone = getClonedMap();
			canvasObjectsClone.put(key, canvasObject);
			stateList.remove(0);
			stateList.add(canvasObjectsClone);
		}
		key++;
	}
	
	public void undo(){
		if(pointer > 0)
			pointer--;
		this.notifyObservers();
	}
	
	public void redo(){
		if(stateList != null && pointer+1 < stateList.size() && pointer < MAX_POINTER)
			pointer++;
		this.notifyObservers();
	}
	
	public HashMap<Integer, CanvasObject> getCanvasState(){
		return stateList == null ? null : stateList.get(pointer);
	}
	
	//returns same hash but with string keys instead of integer keys
	public HashMap<String, CanvasObject> getCanvasState(boolean stringKeys){
		if(stateList != null){
			HashMap<String, CanvasObject>  canvasStateString = new HashMap<String, CanvasObject>();
			HashMap<Integer, CanvasObject> canvasStateInt = stateList.get(pointer);
			
			for (Map.Entry<Integer, CanvasObject> entry : canvasStateInt.entrySet()) {
			    String key = entry.getKey().toString();
			    CanvasObject value = entry.getValue();
			    canvasStateString.put(key, value);
			}
			
			if(stringKeys){
				String name = (String) this.modelObjects.get("canvasName");
				int width = (int) this.modelObjects.get("canvasWidth");
				int height = (int) this.modelObjects.get("canvasHeight");
				CanvasObject canvasState = new CanvasState(name, width, height);
				canvasStateString.put("-1", canvasState);
			}
			return canvasStateString;
		}
		return null;
	}

	private HashMap<Integer, CanvasObject> getClonedMap() {
		HashMap<Integer, CanvasObject> canvasObjectsClone =
		new HashMap<Integer, CanvasObject>();
		
		HashMap<Integer, CanvasObject> canvasMap = stateList.get(pointer);
		for (Map.Entry<Integer, CanvasObject> entry : canvasMap.entrySet()) {
		    int key = entry.getKey();
		    CanvasObject value = entry.getValue();
		    try {
				canvasObjectsClone.put(key, (CanvasObject) value.clone() );
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return canvasObjectsClone;
	}

	public ArrayList<HashMap<Integer, CanvasObject>> getStateList() {
		return stateList;
	}

	public void setStateList(HashMap<String, CanvasObject> stateMap) {
		stateList = new ArrayList<HashMap<Integer, CanvasObject>>();
		HashMap<Integer, CanvasObject> state = new HashMap<Integer, CanvasObject>(); 
		for (Map.Entry<String, CanvasObject> entry : stateMap.entrySet()) {
		    int key = Integer.parseInt(entry.getKey());
		    CanvasObject value = entry.getValue();
		    state.put(key, value);
		}
		stateList.add(state);
		this.notifyObservers();
	}
}
