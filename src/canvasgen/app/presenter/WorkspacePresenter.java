package canvasgen.app.presenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import canvasgen.app.compiler.CodeGenerator;
import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.CanvasModel;
import canvasgen.app.model.Model;
import canvasgen.app.model.WorkspaceModel;
import canvasgen.app.util.ReadFile;
import canvasgen.app.util.WriteFile;
import canvasgen.app.view.View;
import canvasgen.app.view.canvasview.canvasObject.CanvasObject;
import canvasgen.app.view.canvasview.canvasObject.CanvasState;
import canvasgen.app.view.workspaceview.WorkspaceView;

public class WorkspacePresenter extends Presenter {
	private  WorkspaceView view;
	private  Model model;
	private Component frame;
	private String canvasName;
	
	public WorkspacePresenter(Model model, View view){
		super(model, view);
		this.model = (WorkspaceModel)model;
		this.view = (WorkspaceView)view;
		
		this.view.display(this.model.getModelObjects());
		
		//all workspaceFrame components that the model needs can be placed here
		this.model.change("workspaceFrame", this.view.getWorkspaceFrame());
		this.model.change("canvasDesktop", this.view.getCanvasDesktop());
		
		this.view.addMenuItemListeners(this);
		this.view.addToolListeners(this);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		
		String command = actionEvent.getActionCommand();
		//System.out.println("you pressed: " + command);
		switch(command){
			case "freeDraw":
				this.model.change("selectedTool", "freeDraw");
				break;
			case "line":
				this.model.change("selectedTool", "line");
				break;
			case "circle":
				this.model.change("selectedTool", "circle");
				break;
			case "rectangle":
				//System.out.println("rectangle!");
				this.model.change("selectedTool", "rectangle");
				break;
			case "paint":
				this.model.change("selectedTool", "paint");
				break;
			case "eraser":
				this.model.change("selectedTool", "eraser");
				break;
			case "magnify":
				this.model.change("selectedTool", "magnify");
				break;
			case "paintDrop":
				this.model.change("selectedTool", "paintDrop");
				break;
			case "text":
				this.model.change("selectedTool", "text");
				break;
			case "constraint":
				this.model.change("selectedTool", "constraint");
				break;
			case "colorChooser":
				colorChooserAction();
				break;
			case "New...":
				newAction();
				break;
			case "Open...":
				openAction();
				break;
				//open recent doesn't need an action
			case "Save":
				saveAction();
				break;
			case "Save As...":
				saveAsAction();
				break;
			case "Export Project":
				exportAction();
				break;
			case "Exit":
				exitAction();
				break;
			case "Undo":
				undoAction();
				break;
			case "Redo":
				redoAction();
				break;
			case "Clear":
				clearAction();
				break;
			case "Import":
				importAction();
				break;
			case "Ruler":
				rulerAction();
				break;
			case "Grid Lines":
				gridAction();
				break;
			case "Coordinates":
				coordinateAction();
				break;
			case "Translate":
				translateAction();
				break;
			case "Scale":
				scaleAction();
				break;
			case "Rotate":
				rotateAction();
				break;
			case "Change Color":
				colorAction();
				break;
			case "Add Mouse Event":
				mouseAction();
				break;
			case "Add Keyboard Event":
				keyboardAction();
				break;
			case "Add Touch Event":
				touchAction();
				break;
			case "Add Link Button":
				linkAction();
				break;
			case "Keyboard Shortcuts":
				shortcutAction();
				break;
			case "Full Screen":
				fullScreenAction();
				break;
			case "previous project":
				String file = "/files/recent.properties";
				HashMap<String, String> h = ReadFile.getHash(file);
				
				String fileName = h.get("file");
				String path = fileName;
				HashMap<String, CanvasObject> canvasObjects = ReadFile.getObjects(path);
					
					fileName = fileName.split("\\.")[0];
					
					CanvasState c = ((CanvasState) canvasObjects.get("-1"));
					String cName = c.getCanvasName();
					int cWidth = c.getCanvasWidth();
					int cHeight = c.getCanvasHeight();
					mvpFactory.createMVP("Canvas."+fileName+"."+cWidth+"."+cHeight);
					this.canvasName = fileName;
					canvasObjects.remove("-1");
					((CanvasModel) mvpFactory.getModel("Canvas."+fileName)).setStateList(canvasObjects);
					model.change("undoEnabled", true);
					model.change("redoEnabled", true);
					model.change("saveEnabled", true);
					model.change("save as...Enabled", true);
					model.change("export projectEnabled", true);
					System.out.println(path);
				
				break;
			default:
				break;
		}
	}

	private void colorChooserAction() {
		try {
			JFrame guiFrame = new JFrame();
			Color selectedColor = JColorChooser.showDialog(guiFrame, "Pick a Color" , Color.black);
			mvpFactory.getModel("Workspace").change("selectedColor", selectedColor);
		} catch (Exception ex) {
		}

	}

	private void newAction() {
		String fileName;
		int x = 300, y = 300;


		JPanel superPanel = new JPanel(new BorderLayout());

		JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
		JPanel fileInfo = new JPanel(new GridLayout(0, 1, 2, 2));
		JPanel infoInput = new JPanel(new GridLayout(0, 1, 2, 2));
		TitledBorder infoTitle = new TitledBorder("File Information");
		fileInfo.add(new JLabel("Name:", SwingConstants.RIGHT));
		JTextField name = new JTextField();
		infoInput.add(name);

		JPanel dimensionPanel = new JPanel(new BorderLayout(5, 5));
		JPanel dimensionInfo = new JPanel(new GridLayout(0, 1, 2, 2));
		JPanel dimensionInput = new JPanel(new GridLayout(0, 1, 2, 2));
		TitledBorder dimensionTitle = new TitledBorder("Dimensions");
		dimensionInfo.add(new JLabel("Width:", SwingConstants.RIGHT));
		JTextField width = new JTextField();
		dimensionInput.add(width);
		dimensionInfo.add(new JLabel("Height:", SwingConstants.RIGHT));
		JTextField height = new JTextField();
		dimensionInput.add(height);

		infoPanel.add(fileInfo, BorderLayout.WEST);
		infoPanel.add(infoInput, BorderLayout.CENTER);
		infoPanel.setBorder(infoTitle);

		dimensionPanel.add(dimensionInfo, BorderLayout.WEST);
		dimensionPanel.add(dimensionInput, BorderLayout.CENTER);
		dimensionPanel.setBorder(dimensionTitle);

		superPanel.add(infoPanel, BorderLayout.NORTH);
		superPanel.add(dimensionPanel, BorderLayout.CENTER);
		
		JFrame errorFrame = new JFrame();
		errorFrame.setPreferredSize(new Dimension(100, 100));

		int result = JOptionPane.showConfirmDialog(null, superPanel, "New", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if (result == JOptionPane.OK_OPTION) {
			fileName = name.getText();
			if (Objects.equals(fileName, "")){
				fileName = "Canvas";
			}
			try{
				x = Integer.parseInt(width.getText());
				y = Integer.parseInt(height.getText());
				
				if(x < 100 || y < 100 || x > 1000 || y > 1000)
					throw new Exception();
					
			}catch(Exception e){
				x = 300;
				y = 300;
				JOptionPane.showMessageDialog(dimensionInput, "Invalid Dimensions. Creating Default Canvas", "Error Message", JOptionPane.ERROR_MESSAGE);
				//System.out.println("invalid dimensions. using default");
			}			
			mvpFactory.createMVP("Canvas."+fileName+"."+y+"."+x);
			this.canvasName = fileName;
			model.change("undoEnabled", true);
			model.change("redoEnabled", true);
			model.change("saveEnabled", true);
			model.change("save as...Enabled", true);
			model.change("export projectEnabled", true);
		} else {
			System.out.println("Cancelled");
		}
	}
	
	private void openAction(){
		JFileChooser fileChooser = new JFileChooser();
		// status = fileChooser.showOpenDialog(null);
		FileFilter filter = new FileNameExtensionFilter("Cgen file extensions", "cgen", "Cgen", "CGen", "CGEN");
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {		
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			HashMap<String, CanvasObject> canvasObjects = ReadFile.getObjects(path);
			String fileName = fileChooser.getSelectedFile().getName();
			fileName = fileName.split("\\.")[0];
			
			CanvasState c = ((CanvasState) canvasObjects.get("-1"));
			String cName = c.getCanvasName();
			int cWidth = c.getCanvasWidth();
			int cHeight = c.getCanvasHeight();
			mvpFactory.createMVP("Canvas."+fileName+"."+cWidth+"."+cHeight);
			this.canvasName = fileName;
			canvasObjects.remove("-1");
			((CanvasModel) mvpFactory.getModel("Canvas."+fileName)).setStateList(canvasObjects);
			model.change("undoEnabled", true);
			model.change("redoEnabled", true);
			model.change("saveEnabled", true);
			model.change("save as...Enabled", true);
			model.change("export projectEnabled", true);
			System.out.println(path);
		}
		
	}
	
	private void saveAction(){
		String fileName = canvasName+".cgen";
		String DefaultPath=new JFileChooser().getFileSystemView().getDefaultDirectory().toString()+File.separatorChar+fileName;
		WriteFile.writeObjectsToFile(DefaultPath, ((CanvasModel) mvpFactory.getModel("Canvas."+canvasName)).getCanvasState(true));
		System.out.println("saving to " + DefaultPath);
		HashMap<String, String> h = new HashMap<String, String>();
		h.put("file", DefaultPath);
		WriteFile.writeHashToFile("/files/recent.properties", h);
	}
	
	private void saveAsAction() {
		JFileChooser fileChooser = new JFileChooser();
		
		String fileName = canvasName+".cgen";
		fileChooser.setSelectedFile(new File(fileName));
		int returnVal = fileChooser.showSaveDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			WriteFile.writeObjectsToFile(filePath, ((CanvasModel) mvpFactory.getModel("Canvas."+canvasName)).getCanvasState(true));
			System.out.println("saving!");
			HashMap<String, String> h = new HashMap<String, String>();
			h.put("file", filePath);
			WriteFile.writeHashToFile("/files/recent.properties", h);
		}
		if(returnVal == fileChooser.CANCEL_OPTION){
			System.out.println("cancel!");
		}
		
		
		//saveAction();
	}
	
	private void exportAction() {
		JFileChooser fileChooser = new JFileChooser();
		
		String fileName = canvasName+".html";
		fileChooser.setSelectedFile(new File(fileName));
		int returnVal = fileChooser.showSaveDialog(null);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			CodeGenerator.generateOutputFile(filePath, ((CanvasModel) mvpFactory.getModel("Canvas."+canvasName)).getCanvasState(true));
			System.out.println("saving!");
		}
		if(returnVal == fileChooser.CANCEL_OPTION){
			System.out.println("cancel!");
		}
	}
	
	private void exitAction(){
		Object[] options = { "Save", "Don't Save", "Cancel" };

		int n = JOptionPane.showOptionDialog(frame,
				"Would you like to save before exiting?", "Exit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (n == JOptionPane.YES_OPTION) {
			saveAction();
		} else if (n == JOptionPane.NO_OPTION) {
			System.exit(0);
		} else if (n == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel");
		}
	}
	
	private void undoAction() {
		//for now go with first model
		//needs to look up model by which one is selected
		CanvasModel canvasModel = (CanvasModel) mvpFactory.getModel("Canvas."+canvasName);
		canvasModel.undo();
	}
	
	private void redoAction() {
		//for now go with first model
		//needs to look up model by which one is selected
		CanvasModel canvasModel = (CanvasModel) mvpFactory.getModel("Canvas."+canvasName);
		canvasModel.redo();
	}
	
	private void clearAction() {

	}
	
	private void importAction() {

	}
	
	private void rulerAction() {

	}
	
	private void gridAction() {

	}
	
	private void coordinateAction() {

	}
	
	private void translateAction() {

	}
	
	private void scaleAction() {
	}
	
	private void rotateAction() {
	}
	
	private void colorAction() {
	}
	
	private void mouseAction() {
		mvpFactory.createMVP("Mouse");
	}
	
	private void keyboardAction() {
		mvpFactory.createMVP("Keyboard");
	}
	
	private void touchAction() {
		mvpFactory.createMVP("Touch");
	}
	
	private void linkAction() {
		mvpFactory.createMVP("Link");
	}
	
	private void shortcutAction() {
		mvpFactory.createMVP("Shortcuts");
	}

	private void fullScreenAction(){
		Boolean fullScreen = !(Boolean)model.getModelObjects().get("fullScreen");
		model.change("fullScreen", fullScreen);
		
	}
}
