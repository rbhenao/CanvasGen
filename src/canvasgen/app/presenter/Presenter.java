package canvasgen.app.presenter;

import java.awt.event.ActionListener;

import canvasgen.app.model.Model;
import canvasgen.app.view.Observer;
import canvasgen.app.view.View;

public abstract class Presenter implements Observer, ActionListener {
    protected  View view;
    protected  Model model;
    
    public Presenter(){  	
    }
    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
    public void update(){
    	
    }
}
