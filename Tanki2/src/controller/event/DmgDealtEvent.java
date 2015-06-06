package controller.event;

import model.Tank;

public class DmgDealtEvent extends GenericEvent {
	public Tank tank;
	public int amount;
	
	public DmgDealtEvent(Tank tank, int amount) {
		this.tank = tank;
		this.amount = amount;
	}
}

