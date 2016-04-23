package canvasgen.app.view.workspaceview;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import canvasgen.app.factory.mvpFactory;

public class WorkspaceToolBar extends JToolBar{
	
	protected Insets insets = new Insets(0, 0, 0, 0);

	protected Color color = new Color(90,90,90);
	
	protected ImageIcon freeDraw = new ImageIcon(
			ImageIcon.class.getResource("/images/freeDraw.png"));
	
	protected ImageIcon line = new ImageIcon(
			ImageIcon.class.getResource("/images/line.png"));
	
	protected ImageIcon circle = new ImageIcon(
			ImageIcon.class.getResource("/images/circle.png"));
	
	protected ImageIcon rectangle = new ImageIcon(
			ImageIcon.class.getResource("/images/rectangle.png"));
	
	protected ImageIcon eraser = new ImageIcon(
			ImageIcon.class.getResource("/images/eraser.png"));
	
	private ArrayList<JButton> tools = new ArrayList<>();
	
	private final JButton colorChooser;
	
	public WorkspaceToolBar(int orientation){
		super(orientation); //JToolBar.VERTICAL
		setMargin(insets);
		setBackground(color);		
		
		add(createIcon(freeDraw, "freeDraw"));
		add(createIcon(line, "line"));
		add(createIcon(circle, "circle"));
		add(createIcon(rectangle, "rectangle"));
		add(createIcon(eraser, "eraser"));
		addSeparator();
		//addSeparator();
		
		colorChooser = new JButton();
		colorChooser.setBorder(new EmptyBorder(17, 17, 17, 17));
		colorChooser.setBackground((Color) mvpFactory.getModel("Workspace").getModelObjects().get("selectedColor"));
		colorChooser.setOpaque(true);
		colorChooser.setBorderPainted(false);
		colorChooser.setActionCommand("colorChooser");
		tools.add(colorChooser);
		add(colorChooser);
	}
	
	private JButton createIcon(ImageIcon i,String command){
		JButton button = new JButton(i);
		button.setBorder(new EmptyBorder(5, 5, 5, 5));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setActionCommand(command);
		tools.add(button);
		return button;
	}
	
	public ArrayList<JButton> getTools() {
		return tools;
	}
	
	public JButton getColorChooser(){
		return this.colorChooser;
	}
}
