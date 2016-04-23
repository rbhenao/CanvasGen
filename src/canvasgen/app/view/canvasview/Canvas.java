package canvasgen.app.view.canvasview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import canvasgen.app.view.canvasview.canvasObject.CanvasObject;

import java.util.ArrayList;

public class Canvas extends JPanel {
	private int height;
	private int width;
	public ArrayList<CanvasObject> objectQueue;

    public Canvas(int height, int width) {
    	this.height = height;
    	this.width = width;
    	objectQueue = new ArrayList<>();
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.height,this.width);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        if(objectQueue.size() > 0){
        	for(CanvasObject c: objectQueue){
        		c.draw(g);
        	}
        }
    } 
}