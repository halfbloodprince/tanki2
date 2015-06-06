package model.handler;

import java.util.ArrayList;

import model.GameModel;
import controller.GenericHandler;
import controller.event.ExplosionEvent;
import controller.event.GenericEvent;

public class ExplosionHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public ExplosionHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		ExplosionEvent event = (ExplosionEvent) e;
		model.dealDmg(event.shot);
	}
}
