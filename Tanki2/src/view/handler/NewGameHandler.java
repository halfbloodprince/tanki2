package view.handler;

import view.GameView;
import view.GameView.ViewMode;
import controller.GenericHandler;
import controller.event.GenericEvent;

public class NewGameHandler implements GenericHandler{
	private GameView view;
	
	public NewGameHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		view.setViewMode(ViewMode.IN_GAME);
	}
}