package canvasgen.app.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

public class WorkspaceModel extends Model{
	
	public String name = "WorkSpaceModel";
	private Color backGroundColor = Color.darkGray;
	private Dimension minimumSize = new Dimension(900, 600);
	private Boolean fullScreen = false;
	private int fullScreenDim = Frame.MAXIMIZED_BOTH;
	private Color selectedColor = Color.black;
	private String selectedTool = "freeDraw";
	
	private boolean undoEnabled = false;
	private boolean redoEnabled = false;
	private boolean saveEnabled = false;
	private boolean saveAsEnabled = false;
	private boolean exportEnabled = false;

	public WorkspaceModel(){
		super();
		this.modelObjects.put("backgroundColor", backGroundColor);
		this.modelObjects.put("minSize", minimumSize);
		this.modelObjects.put("fullScreen", fullScreen);
		this.modelObjects.put("fullScreenDimension", fullScreenDim);
		this.modelObjects.put("selectedColor", selectedColor);
		this.modelObjects.put("selectedTool", selectedTool);
		this.modelObjects.put("undoEnabled", undoEnabled);
		this.modelObjects.put("redoEnabled", redoEnabled);
		this.modelObjects.put("saveEnabled", saveEnabled);
		this.modelObjects.put("save as...Enabled", saveAsEnabled);
		this.modelObjects.put("export projectEnabled", exportEnabled);
	}
	
	public void change(String key, Object value){
		this.modelObjects.put(key, value);
		this.notifyObservers();
	}
}

