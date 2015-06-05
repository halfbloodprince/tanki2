package view.handler;

import view.GameView;
import view.ShotSprite;
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
			view.getCanvas().addSprite(new ShotSprite(event.shot));
		}
		catch (Exception ex)
		{
			System.out.println("Exception: " + ex);
		}
	}
}
