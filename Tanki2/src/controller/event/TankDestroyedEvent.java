package controller.event;

import model.Tank;

/**
 * Event created when a tank was destroyed
 * @author Severus
 *
 */
public class TankDestroyedEvent extends GenericEvent {
	public Tank tank;

	/**
	 * @param tank Tank which was destroyed
	 */
	public TankDestroyedEvent(Tank tank) {
		this.tank = tank;
	}
}