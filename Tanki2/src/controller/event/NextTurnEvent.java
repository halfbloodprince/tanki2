package controller.event;

import model.Tank;

public class NextTurnEvent extends GenericEvent {
	public Tank tank;

	public NextTurnEvent(Tank tank) {
		this.tank = tank;
	}
}
