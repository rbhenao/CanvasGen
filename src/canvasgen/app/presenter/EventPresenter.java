package canvasgen.app.presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.EventModel;
import canvasgen.app.model.Model;
import canvasgen.app.view.View;
import canvasgen.app.view.eventview.EventView;

public class EventPresenter extends Presenter{
	
	private  EventView view;
	private  Model model;
	private Component frame;

	public EventPresenter(Model model, View view) {
		super(model, view);
		this.model = (EventModel)model;
		this.view = (EventView)view;
		this.view.display(this.model.getModelObjects());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}
