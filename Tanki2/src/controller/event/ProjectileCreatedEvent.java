package controller.event;

import model.Shot;

public class ProjectileCreatedEvent extends GenericEvent {
	public final Shot shot;
	
	public ProjectileCreatedEvent (Shot x) { shot = x; }
}
