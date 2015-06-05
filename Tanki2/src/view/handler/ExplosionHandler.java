package view.handler;

import view.GameView;
import view.ShotSprite;
import controller.GenericHandler;
import controller.event.ExplosionEvent;
import controller.event.GenericEvent;
import controller.event.ProjectileCreatedEvent;

public class ExplosionHandler implements GenericHandler{
	private GameView view;
	
	public ExplosionHandler (GameView v) {
		view = v;
	}

	public void handle (GenericEvent e) {
		ExplosionEvent event = (ExplosionEvent) e;
		try {
			view.getCanvas().removeBullet(event.shot);
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex);
		}
	}	
}
