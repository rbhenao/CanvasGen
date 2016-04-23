package canvasgen.app.view.canvasview;

import java.awt.Color;
import javax.swing.JInternalFrame;

@SuppressWarnings("serial")
public class InternalFrame extends JInternalFrame{
	public static final int xPosition = 30, yPosition = 30;
	public static int openFrameCount = 0;
	
	public InternalFrame(String canvasName, int height, int width) {	
		super(canvasName, true, true, true, true); // name, resizable, closable, maximizable, iconifiable	
		setSize(height, width);	
		
		setBackground(Color.gray);
		this.setMaximizable(false);
	    this.setResizable(false); 
		
	    openFrameCount++;
		setLocation(xPosition * openFrameCount, yPosition * openFrameCount);// Set the window's location.
	}
}
