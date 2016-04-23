package canvasgen.app.view.shortcutsview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.EventModel;
import canvasgen.app.model.ShortcutsModel;
import canvasgen.app.model.WorkspaceModel;
import canvasgen.app.view.View;
import canvasgen.app.view.canvasview.InternalFrame;
import canvasgen.app.view.eventview.EventDialog;

public class ShortcutsView extends View{
	
	
	private HashMap<String, Object> shortcutModelObjects;
	protected HashMap<String, Object> workspaceModelObjects;
	
	private ShortcutsModel shortcutsModel;
	private WorkspaceModel workspaceModel;
	
	private String title;
	private ShortcutsDialog shortcutsDialog;
	private JFrame workspaceFrame;
	private JButton okButton;
	private ArrayList<JLabel> labelList;
	private ArrayList<JTextField> textFieldList;

	public ShortcutsView(ShortcutsModel shortcutsModel) {
		super(shortcutsModel);
		this.shortcutsModel = (ShortcutsModel) this.model;
		workspaceModel = (WorkspaceModel)mvpFactory.getModel("Workspace");
		workspaceModelObjects = workspaceModel.getModelObjects();
		workspaceFrame = (JFrame)workspaceModelObjects.get("workspaceFrame");
		title = (String)shortcutsModel.getModelObjects().get("title");
	}
	
	@Override
	public void display(HashMap<String, Object> modelObjects) {
		shortcutsDialog = new ShortcutsDialog(workspaceFrame, title, "message");
		shortcutsDialog.setMinimumSize((Dimension)modelObjects.get("minSize"));
		shortcutsDialog.getContentPane().setBackground((Color)modelObjects.get("backgroundColor"));
		labelList = shortcutsDialog.getLabels();
		textFieldList = shortcutsDialog.getTextFields();
		okButton = shortcutsDialog.getOkButton();	
		
		//shortcutsDialog.setVisible(true);
		//setVisible is done in the presenter so actions can be added first
	}

	@Override
	public void updateDisplay() {
	}
	
	public void attachButtonListener(ActionListener listener){
		okButton.addActionListener(listener);
	}
	
	public ShortcutsDialog getDialog(){
		return shortcutsDialog;
	}

	public JButton getButton(){
		return okButton;
	}
	
	public ArrayList<JLabel> getLabels(){
		return labelList;
	}
	  
	public ArrayList<JTextField> getTextFields(){
		return textFieldList;
	}
}
