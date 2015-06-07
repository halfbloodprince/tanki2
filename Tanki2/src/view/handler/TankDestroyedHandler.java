package view.handler;

import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.TankDestroyedEvent;
import view.GameView;

public class TankDestroyedHandler  implements GenericHandler {
	private GameView view;
	
	public TankDestroyedHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		TankDestroyedEvent event = (TankDestroyedEvent)e;
		view.getCanvas().removeTank(event.tank);
	}
}