package canvasgen.app.view.eventview;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import canvasgen.app.model.EventModel;

public class LinkView extends EventView{

	public LinkView(EventModel eventModel) {
		super(eventModel);
		this.title = "Add Link Button";
	}
	
	@Override
	public void display(HashMap<String, Object> modelObjects) {
		eventDialog = new EventDialog(workspaceFrame, title, "message");
		eventDialog.setMinimumSize((Dimension)modelObjects.get("minSize"));
		eventDialog.getContentPane().setBackground((Color)modelObjects.get("backgroundColor"));
		eventDialog.setVisible(true);
	}
	
	@Override
	public void updateDisplay() {
	}

}
