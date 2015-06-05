package view.handler;

import view.GameView;
import controller.GenericHandler;
import controller.event.GenericEvent;

public class ExplosionDoneHandler implements GenericHandler{
	private GameView view;
	
	public ExplosionDoneHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		view.getCanvas().finishShot();
	}
}