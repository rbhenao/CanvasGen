package canvasgen.app.presenter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.CanvasModel;
import canvasgen.app.view.canvasview.Canvas;
import canvasgen.app.view.canvasview.CanvasView;
import canvasgen.app.view.canvasview.InternalFrame;
import canvasgen.app.view.canvasview.canvasObject.CanvasObject;
import canvasgen.app.view.canvasview.canvasObject.Circle;
import canvasgen.app.view.canvasview.canvasObject.EraseLine;
import canvasgen.app.view.canvasview.canvasObject.FreeDrawLine;
import canvasgen.app.view.canvasview.canvasObject.Line;
import canvasgen.app.view.canvasview.canvasObject.RectangleObj;
import canvasgen.app.model.WorkspaceModel;


public class CanvasPresenter extends Presenter implements InternalFrameListener, MouseListener, MouseMotionListener {
	
	//models views and the frame
	//private  CanvasView view;
	private  CanvasModel canvasModel;
	private WorkspaceModel workspaceModel;
	//private Component frame;
	
	//frame of the canvas
	private InternalFrame internalFrame;
	
	//Canvas
	private Canvas canvas;
	private ArrayList<Point> pointList;
	private int color;
	
	public CanvasPresenter(CanvasModel model, CanvasView view){
		super(model, view);
		canvasModel = model; 
		//view = (CanvasView)view;
		workspaceModel = (WorkspaceModel) mvpFactory.getModel("Workspace");	
		view.display(model.getModelObjects());
		
		internalFrame = view.getInternalFrame();
		internalFrame.addInternalFrameListener(this);
		
		pointList = new ArrayList<>();
		canvas = view.getCanvas();
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		
		workspaceModel.change("desktopPane", view.getCanvasDesktop());
	}

	public void actionPerformed(ActionEvent arg0) {}
	
	
	//BEGIN internal frame actions
	public void internalFrameActivated(InternalFrameEvent arg0) {}

	public void internalFrameClosed(InternalFrameEvent arg0) {
		internalFrame.openFrameCount--;
		if(internalFrame.openFrameCount == 0){
			workspaceModel.change("undoEnabled", false);
			workspaceModel.change("redoEnabled", false);
			workspaceModel.change("saveEnabled", false);
			workspaceModel.change("save as...Enabled", false);
			workspaceModel.change("export projectEnabled", false);
			mvpFactory.clearCanvasModels();
		}
	}

	public void internalFrameClosing(InternalFrameEvent arg0) {}
	public void internalFrameDeactivated(InternalFrameEvent arg0) {}
	public void internalFrameDeiconified(InternalFrameEvent arg0) {}
	public void internalFrameIconified(InternalFrameEvent arg0) {}
	public void internalFrameOpened(InternalFrameEvent arg0) {}
	//END internal frame actions
	
	
	//BEGIN canvas mouse listener
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	
	public void mousePressed(MouseEvent e) {
		pointList.add(e.getPoint());
		color = ((Color) workspaceModel.getModelObjects().get("selectedColor")).getRGB();
		String selectedTool = (String) workspaceModel.getModelObjects().get("selectedTool");
		switch(selectedTool){
			case "freeDraw":
				break;
			default:
				break;
		}
	}
	
	public void mouseReleased(MouseEvent arg0) {

		
		String selectedTool = (String) workspaceModel.getModelObjects().get("selectedTool");
		switch(selectedTool){
			case "freeDraw":
				if(pointList.size() > 2){
					int[] x = new int[pointList.size()];
					int[] y = new int[pointList.size()];
					
					for(int i = 0; i < pointList.size(); i++){
						x[i] = pointList.get(i).x;
						y[i] = pointList.get(i).y;
					}
					
					canvasModel.addCanvasObject(new FreeDrawLine(x,y,color));
				}
				break;
			case "circle":
				int xDistance = pointList.get(pointList.size()-1).x - pointList.get(0).x;
				int yDistance = pointList.get(pointList.size()-1).y - pointList.get(0).y;
				double radius = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
				double width = radius*2;
				double height = width;
				CanvasObject circle = new Circle(pointList.get(0).x,pointList.get(0).y, width, height, color);
				canvasModel.addCanvasObject(circle);
				canvas.objectQueue.add(circle);
				canvas.repaint();
				break;
			case "rectangle":
				System.out.println("drawing rectangle!");
				int xDistance1 = Math.abs(pointList.get(pointList.size()-1).x - pointList.get(0).x);
				int yDistance1 = Math.abs(pointList.get(pointList.size()-1).y - pointList.get(0).y);
				CanvasObject rectangle = new RectangleObj(pointList.get(0).x,pointList.get(0).y, xDistance1, yDistance1, color);
				canvasModel.addCanvasObject(rectangle);
				canvas.objectQueue.add(rectangle);
				canvas.repaint();
				break;
			case "line":
				System.out.println("drawing line!");
				if(pointList.size() >= 2){
					int x1 = pointList.get(0).x;
					int y1 = pointList.get(0).y;
					int x2 = pointList.get(pointList.size()-1).x;
					int y2 = pointList.get(pointList.size()-1).y;
					CanvasObject line = new Line(x1, y1, x2, y2, color);
					System.out.println(color);
					canvasModel.addCanvasObject(line);
					canvas.objectQueue.add(line);
					canvas.repaint();
				}		
				break;
			case "eraser":
				if(pointList.size() > 2){
					int[] x = new int[pointList.size()];
					int[] y = new int[pointList.size()];
					
					for(int i = 0; i < pointList.size(); i++){
						x[i] = pointList.get(i).x;
						y[i] = pointList.get(i).y;
					}
					Color white = Color.white;
					int white1 = white.getRGB();
					canvasModel.addCanvasObject(new EraseLine(x,y,white1,color));
				}
				break;
			default:
				break;
		}
		pointList.clear();
		color = ((Color) workspaceModel.getModelObjects().get("selectedColor")).getRGB();
	}
	//END canvas mouse listener
	

	//BEGIN canvas mouse motion listener
	public void mouseDragged(MouseEvent e) {
		mvpFactory.getModel("Workspace").change("xPoint", e.getPoint().x);
		mvpFactory.getModel("Workspace").change("yPoint", e.getPoint().y);
		pointList.add(e.getPoint());
		String selectedTool = (String) workspaceModel.getModelObjects().get("selectedTool");
		switch(selectedTool){
			case "freeDraw":
				int[] x = new int[pointList.size()];
				int[] y = new int[pointList.size()];
				for(int i = 0; i < pointList.size(); i++){
					x[i] = pointList.get(i).x;
					y[i] = pointList.get(i).y;
				}
				canvas.objectQueue.add(new FreeDrawLine(x, y,color));
				canvas.repaint();
				break;
			case "circle":
				int xDistance = e.getPoint().x - pointList.get(0).x;
				int yDistance = e.getPoint().y - pointList.get(0).y;
				double radius = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));
				double width = radius*2;
				double height = width;
				if(xDistance == 1){
					//canvas.objectQueue.add(new Circle(e.getPoint().x,e.getPoint().y, width, height));
				}else if(xDistance > 1){
					//canvas.objectQueue.set(canvas.objectQueue.size()-1, new Circle(e.getPoint().x,e.getPoint().y, width, height));
					//canvas.repaint();
				}
				
				break;
			case "eraser":
				int[] x1 = new int[pointList.size()];
				int[] y1 = new int[pointList.size()];
				for(int i = 0; i < pointList.size(); i++){
					x1[i] = pointList.get(i).x;
					y1[i] = pointList.get(i).y;
				}
				Color white = Color.white;
				int white1 = white.getRGB();
				canvas.objectQueue.add(new EraseLine(x1, y1,white1,color));
				canvas.repaint();
				break;
			default:
				break;
		}
	}
	public void mouseMoved(MouseEvent e) {
		//this.workspaceModel.getModelObjects().get("point");
		mvpFactory.getModel("Workspace").change("xPoint", e.getPoint().x);
		mvpFactory.getModel("Workspace").change("yPoint", e.getPoint().y);


	}
	//END canvas mouse motion listener
}
