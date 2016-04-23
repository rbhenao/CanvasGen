package canvasgen.app.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

public class EventModel extends Model{
	
	public String name = "EventModel";
	private Color backGroundColor = Color.white;
	private Dimension minimumSize = new Dimension(450, 350);
	private String title = "Event Window";
	
	public EventModel(){
		super();
		this.modelObjects.put("backgroundColor", backGroundColor);
		this.modelObjects.put("minSize", minimumSize);
		this.modelObjects.put("title", title);
	}
	
	@Override
	public void change(String key, Object value) {
	}

}
