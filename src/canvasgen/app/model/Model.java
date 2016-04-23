package canvasgen.app.model;

import java.util.ArrayList;
import java.util.HashMap;

import canvasgen.app.view.Observer;


public abstract class Model implements ModelInterface {

	protected ArrayList<Observer> observers;
	protected HashMap<String, Object> modelObjects;
	
	public Model(){
		observers = new ArrayList<Observer>();
		modelObjects = new HashMap<String, Object>();
	}
	
	public void registerObserver(Observer o){		
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0)
			observers.remove(i);	
	}

	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer o = observers.get(i);
			o.update();
		}
	}
	
	public abstract void change(String key, Object value);
	
	public HashMap<String, Object> getModelObjects() {
		return this.modelObjects;
	}
	
	public void setModelObjects(HashMap<String, Object> modelObjects){
		this.modelObjects = modelObjects;
		notifyObservers();
	}

}
