package view.handler;

import model.Shot;
import view.Explosion;
import view.GameView;
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
			Shot shot = event.shot;
			Explosion explosion = new Explosion(shot.getX(), shot.getY(), shot.getRadius());
			view.getCanvas().removeBullet(shot);
			view.scheduleExplosion(explosion);
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex);
		}
	}	
}
