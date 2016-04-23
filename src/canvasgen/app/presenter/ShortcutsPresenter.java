package canvasgen.app.presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import canvasgen.app.factory.mvpFactory;
import canvasgen.app.model.EventModel;
import canvasgen.app.model.Model;
import canvasgen.app.model.ShortcutsModel;
import canvasgen.app.view.View;
import canvasgen.app.view.eventview.EventView;
import canvasgen.app.view.shortcutsview.ShortcutsView;

public class ShortcutsPresenter extends Presenter{
	
	private  ShortcutsView view;
	private  Model model;
	private Component frame;
	private String errorMessage;

	public ShortcutsPresenter(Model model, View view) {
		super(model, view);
		this.model = (ShortcutsModel)model;
		this.view = (ShortcutsView)view;
		this.view.display(this.model.getModelObjects());
		
		JButton okButton = this.view.getButton();
		this.view.attachButtonListener(this);
		this.view.getDialog().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<JTextField> textFieldList = this.view.getTextFields();
		
		if(validateEntries(textFieldList)){
			String[] textFieldArray = new String[textFieldList.size()];
			
			for(int i = 0; i < textFieldArray.length; i++)
				textFieldArray[i] = textFieldList.get(i).getText();
			
			this.model.change("shortcutFields", textFieldArray);
			errorMessage = "Your changes have been applied!";
			System.out.println(errorMessage);
		}	
		System.out.println("button was clicked!");
	}
	
	//make sure all shortcut entries are valid
	private boolean validateEntries(ArrayList<JTextField> textFieldList){
		for(int i = 0; i < textFieldList.size()-1; i++){
			for(int j = i+1; j < textFieldList.size(); j++){
				if( textFieldList.get(i).getText().equals( textFieldList.get(j).getText() ) ){
					//System.out.println("duplicate keyboard shortcuts!");
					errorMessage = "Error, duplicate keyboard shortcuts. Please fix and then press Ok";
					System.out.println(errorMessage);
					return false;
				}
			}
		}		
		return hasValidShortcuts(textFieldList); 
	}
	
	//checks if every entry is a valid combination of characters
	private boolean hasValidShortcuts(ArrayList<JTextField> textFieldList){
		for(int i = 0; i < textFieldList.size(); i++){
			String[] keys = textFieldList.get(i).getText().split("\\+");
			if(!hasValidKeys(keys)){
				return false;
			}
				
		}
		return true;
	}
	
	//checks the keys of a given entry. keys are split on the + sign
	private boolean hasValidKeys(String[] keys){
		if(keys.length > 3){
			errorMessage = "Error key combination too long";
			System.out.println(errorMessage);
			return false;
		}		
		
		for(int i = 0; i < keys.length; i++){
			String key = keys[i].trim().toLowerCase();
			
			if(key.length() > 1)
				if(!key.equals("ctrl") && !key.equals("shft") && !key.equals("alt") && !key.equals("f11")){
					errorMessage = key+"Error, invalid shortcut buttton";
					System.out.println(errorMessage);
					return false;
				}
			else
				if(!Character.isLetter(key.charAt(0)))
					if(!Character.isDigit(key.charAt(0))){
						errorMessage = key+"Error, invalid shortcut key";
						System.out.println(errorMessage);
						return false;
					}										
		}
		return true;
	}
	
}
