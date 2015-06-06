package model.handler;

import model.GameModel;
import controller.GenericHandler;
import controller.event.GenericEvent;

public class ExplosionDoneHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public ExplosionDoneHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		if (!model.enableControl && model.turnFinished())
			model.nextTurn();
	}
}
