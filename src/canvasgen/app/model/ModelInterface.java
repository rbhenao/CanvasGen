package canvasgen.app.model;

import canvasgen.app.view.Observer;

public interface ModelInterface {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
