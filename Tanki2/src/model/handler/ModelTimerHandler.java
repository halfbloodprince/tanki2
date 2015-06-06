package model.handler;

import model.GameModel;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.ModelTimerEvent;

public class ModelTimerHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public ModelTimerHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		model.update();
	}
}
