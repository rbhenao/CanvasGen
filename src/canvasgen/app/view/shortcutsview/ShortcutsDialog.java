package canvasgen.app.view.shortcutsview;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import canvasgen.app.factory.mvpFactory;

public class ShortcutsDialog extends JDialog{
	
  private JButton okButton;
  private ArrayList<JLabel> labelList;
  private ArrayList<JTextField> textFieldList;
	
  public ShortcutsDialog(JFrame parent, String title, String message) {
    super(parent, title, true);
    if (parent != null) {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
      setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 10);
    }
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    labelList = new ArrayList<JLabel>();
    textFieldList = new ArrayList<JTextField>();
     
    JScrollPane  scrollPane = new JScrollPane();
	scrollPane.getViewport().add(createRows());
    add(scrollPane);
    pack();    
  }
  
  private JPanel createRows(){
	  JPanel pane = new JPanel();
	  
	  ArrayList<JComponent> componentList = getRows();
	 	    
      pane.setBorder(BorderFactory.createTitledBorder("Shortcuts"));
      pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
     
      for(JComponent component: componentList)
    	  pane.add(component);
      
      okButton = new JButton("Ok");
      	if(okButton == null){
      		System.out.println("its null!");
      	}
      pane.add(okButton);
      
      return pane;
  }
  
  private ArrayList<JComponent> getRows(){
	  ArrayList<JComponent> componentList = new ArrayList<JComponent>();
	  
	  HashMap<String, Object> shortcutObjects = mvpFactory.getModel("Shortcuts").getModelObjects();
	  String[] actionFields = (String[])shortcutObjects.get("actionFields");
	  String[] shortcutFields = (String[])shortcutObjects.get("shortcutFields");
	  
	  //for(int i = 0; i < actionFields.length; i++){
		  //System.out.println(actionFields[i] + ": " + shortcutFields[i]);
	  //}
	  	  
	  for(int i = 0; i < actionFields.length; i++){
			  JComponent component = new JPanel();
			  JLabel l = new JLabel(actionFields[i]);
			  JTextField t = new JTextField(shortcutFields[i], 10);
			  labelList.add(l);
			  textFieldList.add(t);	  
			  component.add(l);
			  component.add(t);
		      component.setAlignmentX(Component.LEFT_ALIGNMENT);
		      componentList.add(component);	  
	  }   
	  return componentList;
  }
  
  @Override
  public Dimension getPreferredSize() {
      return new Dimension(300, 300);
  }

  public JButton getOkButton() {
	  return this.okButton;
  }
  
  public ArrayList<JLabel> getLabels(){
	  return labelList;
  }
  
  public ArrayList<JTextField> getTextFields(){
	  return textFieldList;
  }
}

