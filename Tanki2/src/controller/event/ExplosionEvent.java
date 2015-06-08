package controller.event;

import model.Shot;

/**
 * Event created when explosion has started
 * @author Severus
 *
 */
public class ExplosionEvent extends GenericEvent {
	public Shot shot;
	
	/**
	 * Create this event
	 * @param shot shot, which caused this explosion
	 */
	public ExplosionEvent(Shot shot) {
		this.shot = shot;
	}
}
