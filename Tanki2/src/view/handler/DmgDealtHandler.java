package view.handler;

import view.FloatingNumbers;
import view.GameView;
import controller.GenericHandler;
import controller.event.DmgDealtEvent;
import controller.event.GenericEvent;

public class DmgDealtHandler implements GenericHandler{
	private GameView view;
	
	public DmgDealtHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		DmgDealtEvent event = (DmgDealtEvent)e;
		try {
			view.getCanvas().addAnimation(new FloatingNumbers(event.tank.getX(), event.tank.getY(), event.amount));
		}

		catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
}