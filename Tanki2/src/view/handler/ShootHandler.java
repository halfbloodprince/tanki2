package view.handler;

import view.GameView;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.ShootEvent;

public class ShootHandler implements GenericHandler {
	private GameView view;
	
	/// @param target model to inflict changes to
	public ShootHandler (GameView v) {
		view = v;
	}
	public void handle (GenericEvent e) {
		System.out.format("Shoot happens. (view)");
	}
}
