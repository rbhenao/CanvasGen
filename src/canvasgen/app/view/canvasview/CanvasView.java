package canvasgen.app.view.canvasview;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.CanvasModel;
import canvasgen.app.model.Model;
import canvasgen.app.model.WorkspaceModel;
import canvasgen.app.view.View;
import canvasgen.app.view.canvasview.canvasObject.CanvasObject;
import canvasgen.app.view.canvasview.canvasObject.FreeDrawLine;

public class CanvasView extends View{
	
	private CanvasModel canvasModel;
	private WorkspaceModel workspaceModel;
	private HashMap<String, Object> workspaceModelObjects;
	private JDesktopPane canvasDesktop;
	
	//frame of the canvas
	private InternalFrame internalFrame;
	private String canvasName;
	private int canvasHeight;
	private int canvasWidth;
	
	//canvas that goes inside internal frame
	private Canvas canvas;

	public CanvasView(Model model){
		super(model);
		canvasModel = (CanvasModel) this.model;
		workspaceModel = (WorkspaceModel)mvpFactory.getModel("Workspace");
		workspaceModelObjects = workspaceModel.getModelObjects();
		canvasDesktop =	(JDesktopPane)workspaceModelObjects.get("canvasDesktop");
		canvasName = (String) canvasModel.getModelObjects().get("canvasName");

		canvasHeight = (int)canvasModel.getModelObjects().get("canvasHeight");
		System.out.println("height" + canvasHeight);
		canvasWidth = (int)canvasModel.getModelObjects().get("canvasWidth");
		System.out.println("width" + canvasWidth);
	}

	@Override
	public void updateDisplay() {
		canvas.objectQueue.clear();
		HashMap<Integer, CanvasObject> canvasObjectQueue = canvasModel.getCanvasState();
		if(canvasObjectQueue != null)
			for (CanvasObject canvasObject : canvasObjectQueue.values()) 
			    canvas.objectQueue.add(canvasObject);		
		canvas.repaint();	
	}

	@Override
	public void display(HashMap<String, Object> modelObjects) {
		internalFrame = new InternalFrame(canvasName, canvasHeight, canvasWidth);
		
		canvas = new Canvas(canvasWidth, canvasHeight);
		internalFrame.add(canvas, BorderLayout.CENTER);
		canvas.setVisible(true);
		
		internalFrame.setVisible(true);
		canvasDesktop.add(internalFrame);
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {}
		
	}
	
	public JDesktopPane getCanvasDesktop(){
		return this.canvasDesktop;
	}
	
	public InternalFrame getInternalFrame(){
		return this.internalFrame;
	}

	public Canvas getCanvas() {
		return this.canvas;
	}
}
