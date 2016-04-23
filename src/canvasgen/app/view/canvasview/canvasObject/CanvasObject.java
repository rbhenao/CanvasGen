package canvasgen.app.view.canvasview.canvasObject;

import java.awt.Graphics;
import java.io.Serializable;

/*
 * This interface defines how to interact with all canvas objects.
 * note that all canvas objects must implement the clone method
 * Also since this is a shallow clone only Strings and primitive types will 
 * be allowed inside the canvas objects.
 * 
*/
public interface CanvasObject extends Cloneable, Serializable{
	//values that are final and all canvas objects may need to use
	public final double PI = 3.14159;
	
	public int getKey();
	public int[] getCoordinates();
	public int getBackgroundColor();
	public int getOutlineColor();
	public int getzIndex();
	
	//key cannot be changed after construction
	public void setCoordinates(int[] coordinates);
	public void setBackgroundColor(int backgroundColor );
	public void setOutlineColor(int outlineColor );
	public void setzIndex(int zIndex);
	
	public double getArea();
	public Object clone() throws CloneNotSupportedException;
	
	//tells the object to draw itself
	public void draw(Graphics g);
	
	//if we ever do animation, add a bunch of animation properties here
}
