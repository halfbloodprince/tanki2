package controller.event;

import model.Tank;

/**
 * Event created when damage was dealt to tank
 * @author Severus
 */
public class DmgDealtEvent extends GenericEvent {
	public Tank tank;
	public int amount;
	
	/**
	 * Construct this event
	 * @param tank Tank which was damaged
	 * @param amount Amount of damage dealt
	 */
	public DmgDealtEvent(Tank tank, int amount) {
		this.tank = tank;
		this.amount = amount;
	}
}

