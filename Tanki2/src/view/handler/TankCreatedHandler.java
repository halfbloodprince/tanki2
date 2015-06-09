package view.handler;

import java.io.IOException;

import view.GameView;
import view.TankSprite;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.TankCreatedEvent;

public class TankCreatedHandler implements GenericHandler {
	private GameView view;
	
	public TankCreatedHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		TankCreatedEvent event = (TankCreatedEvent)e;
		try {
			view.getCanvas().addSprite(new TankSprite(event.tank, view));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}