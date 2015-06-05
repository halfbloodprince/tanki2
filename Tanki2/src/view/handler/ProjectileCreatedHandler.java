package view.handler;

import view.GameView;
import view.ShotAnimation;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.ProjectileCreatedEvent;

public class ProjectileCreatedHandler implements GenericHandler {
	private GameView view;
	
	/// @param target model to inflict changes to
	public ProjectileCreatedHandler (GameView v) {
		view = v;
	}
	public void handle (GenericEvent e) {
		ProjectileCreatedEvent event = (ProjectileCreatedEvent) e;
		try {
			ShotAnimation anim = new ShotAnimation(event.shot);
			anim.setDuration(275); // find a better way to animate shots
			view.getCanvas().addAnimation(anim);
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex);
		}
	}
}
