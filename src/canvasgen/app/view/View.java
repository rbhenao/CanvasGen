package canvasgen.app.view;

import java.util.HashMap;
import canvasgen.app.model.ModelInterface;

public abstract class View implements ViewInterface, Observer{
	
	protected ModelInterface model;
	
	public View(){	
	}
	
	public View(ModelInterface model){
		this.model = model;
		model.registerObserver(this);
	}
	
	public void update(){
		updateDisplay();
	}
	
	public abstract void updateDisplay();
	
	public abstract void display(HashMap<String, Object> modelObjects);
	
}
