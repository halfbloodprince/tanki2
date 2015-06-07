package controller.event;

import model.Tank;

public class TankDestroyedEvent extends GenericEvent {
	public Tank tank;

	public TankDestroyedEvent(Tank tank) {
		this.tank = tank;
	}
}