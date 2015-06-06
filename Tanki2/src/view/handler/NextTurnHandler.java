package view.handler;

import view.GameView;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.NextTurnEvent;

public class NextTurnHandler implements GenericHandler{
	private GameView view;
	
	public NextTurnHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		NextTurnEvent event = (NextTurnEvent) e;
		view.setFocusedTank(event.tank);
	}
}
