package canvasgen.app.factory;

import java.util.ArrayList;

import canvasgen.app.model.AnimationModel;
import canvasgen.app.model.CanvasModel;
import canvasgen.app.model.EventModel;
import canvasgen.app.model.Model;
import canvasgen.app.model.ShortcutsModel;
import canvasgen.app.model.WorkspaceModel;
import canvasgen.app.presenter.CanvasPresenter;
import canvasgen.app.presenter.EventPresenter;
import canvasgen.app.presenter.ShortcutsPresenter;
import canvasgen.app.presenter.WorkspacePresenter;
import canvasgen.app.view.canvasview.CanvasView;
import canvasgen.app.view.eventview.EventView;
import canvasgen.app.view.eventview.KeyboardView;
import canvasgen.app.view.eventview.LinkView;
import canvasgen.app.view.eventview.MouseView;
import canvasgen.app.view.eventview.TouchView;
import canvasgen.app.view.shortcutsview.ShortcutsView;
import canvasgen.app.view.workspaceview.WorkspaceView;


public class mvpFactory {
	
	private static EventModel eventModel;
	private static WorkspaceModel workspaceModel;
	private static AnimationModel animationModel;
	private static ShortcutsModel shortcutsModel;
	
	private static ArrayList<CanvasModel> canvasModelList = new ArrayList<CanvasModel>();
	
	public static void createMVP(String presenter){
		String[] args = presenter.split("\\.");
		presenter = args[0];
		switch(presenter){
			case "Workspace":
				mvpFactory.workspaceModel = (WorkspaceModel)getModel("Workspace");
				WorkspaceView workspaceView = new WorkspaceView(mvpFactory.workspaceModel);
				WorkspacePresenter workspacePresenter = new WorkspacePresenter(mvpFactory.workspaceModel, workspaceView);
			break;
			case "Canvas":
				String name = args[1];
				int width = Integer.parseInt(args[2]);
				int height = Integer.parseInt(args[3]);
				System.out.println("width" + width);
				System.out.println("height" + height);

				CanvasModel canvasModel = new CanvasModel(name, width, height);
				canvasModelList.add(canvasModel);
				CanvasView canvasView = new CanvasView(canvasModel);
				CanvasPresenter canvasPresenter = new CanvasPresenter(canvasModel, canvasView);
				break;
			case "Mouse":				
				mvpFactory.eventModel = (EventModel)getModel("Event");
				MouseView mouseView = new MouseView(eventModel);
				EventPresenter eventPresenter = new EventPresenter(eventModel, mouseView);
				break;
			case "Keyboard":				
				mvpFactory.eventModel = (EventModel)getModel("Event");
				KeyboardView keyboardView = new KeyboardView(eventModel);
				EventPresenter eventPresenter1 = new EventPresenter(eventModel, keyboardView);
				break;
			case "Touch":				
				mvpFactory.eventModel = (EventModel)getModel("Event");
				TouchView touchView = new TouchView(eventModel);
				EventPresenter eventPresenter2 = new EventPresenter(eventModel, touchView);
				break;
			case "Link":				
				mvpFactory.eventModel = (EventModel)getModel("Event");
				LinkView linkView = new LinkView(eventModel);
				EventPresenter eventPresenter3 = new EventPresenter(eventModel, linkView);
				break;
			case "Shortcuts":
				mvpFactory.shortcutsModel = (ShortcutsModel)getModel("Shortcuts");
				ShortcutsView shortcutsView = new ShortcutsView(shortcutsModel);
				ShortcutsPresenter shortCutsPresenter = new ShortcutsPresenter(shortcutsModel, shortcutsView);
				break;
			case "Animation":
				//mvp here.. etc
				break;
			default:
				break;
		}
	}
	
	public static Model getModel(String modelName){
		String[] args = modelName.split("\\.");
		modelName = args[0];
		switch(modelName){
			case "Workspace":
				if(mvpFactory.workspaceModel == null){
					mvpFactory.workspaceModel = new WorkspaceModel();
					return mvpFactory.workspaceModel;
				}else{
					return mvpFactory.workspaceModel;
				}			
			case "Canvas":
				for(CanvasModel c: canvasModelList)
					if(c.getModelObjects().get("canvasName").equals(args[1]))
						return c;
				return null;
			case "Event":
				if(mvpFactory.eventModel == null){
					mvpFactory.eventModel = new EventModel();
					return mvpFactory.eventModel;
				}else{
					return mvpFactory.eventModel;
				}				
			case "Animation":
				if(mvpFactory.animationModel == null){
					mvpFactory.animationModel = new AnimationModel();
					return mvpFactory.animationModel;
				}else{
					return mvpFactory.animationModel;
				}
			case "Shortcuts":
				if(mvpFactory.shortcutsModel == null){
					mvpFactory.shortcutsModel = new ShortcutsModel();
					return mvpFactory.shortcutsModel;
				}else{
					return mvpFactory.shortcutsModel;
				}
			default:
				return null;
		}
	}

	public static void clearCanvasModels() {
		canvasModelList.clear();
	}
}
