package canvasgen.app.view.canvasview.canvasObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

public class RectangleObj implements CanvasObject{
	
	private static final long serialVersionUID = 4L;
	
	//fields every canvas object has
	private int[] coordinates = new int[2]; //2 slots for x and y. These should be center of circle
	private int zIndex; //Objects with higher z-indexes appear on top of objects with lower z-indexes
	private int backgroundColor; //note this should be a hexadecimal number
	private int outlineColor; //color of the outline of the shape should also be hexadecimal

	//fields unique to the rectangle
	private int width;
	private int height;
	private int x;
	private int y;
	
	
	public RectangleObj (int x, int y, int width, int height, int backgroundColor){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
	}
	
	//this method must be in every single canvas object!!!
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public int getKey() {
		return 0;
	}

	@Override
	public int[] getCoordinates() {
		return null;
	}

	@Override
	public int getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public int getOutlineColor() {
		return 0;
	}

	@Override
	public int getzIndex() {
		return 0;
	}

	@Override
	public void setCoordinates(int[] coordinates) {
		
	}

	@Override
	public void setBackgroundColor(int backgroundColor) {
		
	}

	@Override
	public void setOutlineColor(int outlineColor) {

	}

	@Override
	public void setzIndex(int zIndex) {
	}

	@Override
	public double getArea() {
		return 0;
	}
	
	public int getWidth(){
		return (int)this.width;
	}
	
	public int getHeight(){
		return (int)this.height;
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY(){
		return (int)this.y;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		Rectangle r = new Rectangle(x,y,width,height);
		Color c = new Color(backgroundColor, true);
		g.setColor(c);
		g2d.draw(r);
	}
	
}
