package canvasgen.app.view.canvasview.canvasObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class Line implements CanvasObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//fields every canvas object has
	private int[] coordinates = new int[2]; //2 slots for x and y. These should be center of circle
	private int zIndex; //Objects with higher z-indexes appear on top of objects with lower z-indexes
	private int backgroundColor; //note this should be a hexadecimal number
	private int outlineColor; //color of the outline of the shape should also be hexadecimal
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Line(int x1, int y1, int x2, int y2, int backgroundColor){
		this.setX1(x1);
		this.setY1(y1);
		this.setX2(x2);
		this.setY2(y2);
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

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Shape line = new Line2D.Double(x1,y1,x2,y2);
		Color c = new Color(backgroundColor, true);
		g.setColor(c);
		g2d.draw(line);
	}

}
