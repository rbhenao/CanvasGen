package canvasgen.app.view.workspaceview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.WorkspaceModel;
import canvasgen.app.view.View;


public class WorkspaceView extends View {

	private WorkspaceModel workspaceModel;
	private WorkspaceFrame workspaceFrame;
	private WorkspaceMenuBar menuBar;
	private JDesktopPane canvasDesktop;
	private WorkspaceToolBar toolBar;
	private ArrayList<JMenuItem> menuItems;
	private ArrayList<JButton> tools;
	private JButton colorChooser;
	private JLabel mousePosition;

	private Color backgroundColor;
	
	public WorkspaceView(WorkspaceModel model){
		super(model);
		workspaceModel = (WorkspaceModel) this.model;
		mvpFactory.getModel("Shortcuts").registerObserver(this);
	}

	@Override
	public void display(HashMap<String, Object> modelObjects) {
		try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Canvas Generator");
        } catch (Exception e) {
            System.out.println("Display error");
        }
        workspaceFrame = new WorkspaceFrame();
        workspaceFrame.getContentPane().setBackground((Color)modelObjects.get("backgroundColor"));
        workspaceFrame.setMinimumSize((Dimension)modelObjects.get("minSize"));
        workspaceFrame.setLocationRelativeTo(null);
        menuBar = (WorkspaceMenuBar)workspaceFrame.getJMenuBar();
        toolBar = (WorkspaceToolBar) workspaceFrame.getToolBar();
        canvasDesktop = workspaceFrame.getCanvasDesktop();
        menuItems = menuBar.getMenuItems();
        tools = toolBar.getTools();
		mousePosition = workspaceFrame.getPointPosition();
        
        workspaceFrame.setVisible(true); 
	}

	@Override
	public void updateDisplay() {
		HashMap<String, Object> frameObjects = workspaceModel.getModelObjects();
		//display(modelObjects);	
		backgroundColor = (frameObjects.containsKey("backgroundColor")) ? (Color)frameObjects.get("backgroundColor"): Color.gray;
		workspaceFrame.getCanvasDesktop().setBackground(backgroundColor);
		
		if((Boolean)frameObjects.get("fullScreen"))
			workspaceFrame.setExtendedState((int)frameObjects.get("fullScreenDimension"));
		else
			workspaceFrame.setExtendedState(Frame.NORMAL);
		
		if(frameObjects.containsKey("canvasDesktop")){
			workspaceFrame.setCanvasDesktop((JDesktopPane)frameObjects.get("canvasDesktop"));
		}
		
		//change enable status of menu items as needed
		for(JMenuItem menuItem: menuItems){
			String key = menuItem.getText().toLowerCase()+"Enabled";
			if(frameObjects.containsKey(key)){
				menuItem.setEnabled((boolean) frameObjects.get(key));
			}
		}
		
		colorChooser = toolBar.getColorChooser();
		colorChooser.setBackground((Color) workspaceModel.getModelObjects().get("selectedColor"));
		//figure out how to create new menu bar if shortcuts have changed
		//this isn't working
		//menuBar = new WorkspaceMenuBar();
		//workspaceFrame.setJMenuBar(menuBar);

		if (frameObjects.containsKey("xPoint") && frameObjects.containsKey("yPoint")) {
			int xPoint = (int) frameObjects.get("xPoint");
			int yPoint = (int) frameObjects.get("yPoint");
			mousePosition.setText(" " + xPoint + "," + yPoint);
		}


	}
	
	public void addMenuItemListeners(ActionListener listener){
		for(JMenuItem menuItem: menuItems){
			menuItem.addActionListener(listener);
		}
	}
	
	public void addToolListeners(ActionListener listener){
		for(JButton tool: tools)
			tool.addActionListener(listener);
	}
	
	public WorkspaceFrame getWorkspaceFrame() {
		return workspaceFrame;
	}
	
	public JDesktopPane getCanvasDesktop(){
		return canvasDesktop;
	}
	
	public void setCanvasDesktop(JDesktopPane j){
		this.workspaceFrame.setCanvasDesktop(j);
	}
}
