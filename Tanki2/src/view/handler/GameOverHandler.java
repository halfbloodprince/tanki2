package view.handler;

import view.GameView;
import controller.GenericHandler;
import controller.event.GameOverEvent;
import controller.event.GenericEvent;

public class GameOverHandler implements GenericHandler{
	private GameView view;
	
	public GameOverHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		GameOverEvent event = (GameOverEvent)e;
		view.gameOver(event.winner);
	}
}
