package model.handler;

import model.GameModel;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.ShootEvent;

public class ShootHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public ShootHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		ShootEvent event = (ShootEvent) e;
		// System.out.format("shoot happens. (model)");
	}
}
