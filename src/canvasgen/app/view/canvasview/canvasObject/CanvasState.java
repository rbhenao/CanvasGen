package canvasgen.app.view.canvasview.canvasObject;

import java.awt.Graphics;

public class CanvasState implements CanvasObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private String canvasName;
	private int canvasWidth;
	private int canvasHeight;
	
	public CanvasState(String name, int width, int height){
		this.setCanvasName(name);
		this.setCanvasWidth(width);
		this.setCanvasHeight(height);
	}

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
		return 0;
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

	@Override
	public void draw(Graphics g) {
	}
	//represents the state of the canvas when it is serialized

	public String getCanvasName() {
		return canvasName;
	}

	public void setCanvasName(String name) {
		this.canvasName = name;
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}
}
