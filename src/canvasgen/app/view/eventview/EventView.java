package canvasgen.app.view.eventview;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.CanvasModel;
import canvasgen.app.model.EventModel;
import canvasgen.app.model.WorkspaceModel;
import canvasgen.app.view.View;
import canvasgen.app.view.canvasview.InternalFrame;
import canvasgen.app.view.workspaceview.WorkspaceFrame;
import canvasgen.app.view.workspaceview.WorkspaceMenuBar;
import canvasgen.app.view.workspaceview.WorkspaceToolBar;

public class EventView extends View{
	
	protected EventModel eventModel;
	protected WorkspaceModel workspaceModel;
	protected HashMap<String, Object> workspaceModelObjects;
	protected InternalFrame eventFrame;
	protected EventDialog eventDialog;
	protected JFrame workspaceFrame;
	protected String title;

	public EventView(EventModel eventModel) {
		super(eventModel);
		this.eventModel = (EventModel) this.model;
		workspaceModel = (WorkspaceModel)mvpFactory.getModel("Workspace");
		workspaceModelObjects = workspaceModel.getModelObjects();
		workspaceFrame = (JFrame)workspaceModel.getModelObjects().get("workspaceFrame");
		title = (String)eventModel.getModelObjects().get("title"); // can be overriden in subclass
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
