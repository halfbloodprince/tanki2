package controller.event;

import model.Tank;

/**
 * Event created when next turn has started
 * @author Severus
 *
 */
public class NextTurnEvent extends GenericEvent {
	public Tank tank;

	/**
	 * @param tank Tank, which is now playing
	 */
	public NextTurnEvent(Tank tank) {
		this.tank = tank;
	}
}
