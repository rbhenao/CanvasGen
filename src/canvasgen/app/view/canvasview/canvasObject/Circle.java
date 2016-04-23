package canvasgen.app.view.canvasview.canvasObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Circle implements CanvasObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//fields every canvas object has
	private int[] coordinates = new int[2]; //2 slots for x and y. These should be center of circle
	private int zIndex; //Objects with higher z-indexes appear on top of objects with lower z-indexes
	private int backgroundColor; //note this should be a hexadecimal number
	private int outlineColor; //color of the outline of the shape should also be hexadecimal
	
	//fields unique to the circle
	private double width;
	private double height;
	private double x;
	private double y;
	
	public Circle(double x, double y, double w, double h, int backgroundColor){
		System.out.println("circle!");
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.backgroundColor = backgroundColor;
		this.coordinates[0] = (int) (x + (width/2));
		this.coordinates[1] = (int) (y + (height/2));
	}
	
//	public Circle(double width, double height, int[] coordinates, int zIndex){
//		this(width, height, coordinates);
//		this.zIndex = zIndex;
//	}
//	
//	public Circle(double width, double height, int[] coordinates, int zIndex, int backgroundColor){
//		this(width, height, coordinates, zIndex);
//		this.backgroundColor = backgroundColor;
//	}
//	
//	public Circle(int width, int height, int[] coordinates, int zIndex, int backgroundColor, int outlineColor){
//		this(width, height, coordinates, zIndex, backgroundColor);
//		this.outlineColor = outlineColor;
//	}
	
	//this method must be in every single canvas object!!!
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public double getHeight(){
		return this.height;
	}

	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(int outlineColor) {
		this.outlineColor = outlineColor;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public double getArea() {
		//int r = getRadius();
		//return PI*(r*r);
		return 0;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		Shape circle = new Ellipse2D.Double(x, y, width, height);
		Color c = new Color(backgroundColor, true);
		g.setColor(c);
		g2d.draw(circle);
	}

	@Override
	public int getKey() {
		return 0;
	}
}
