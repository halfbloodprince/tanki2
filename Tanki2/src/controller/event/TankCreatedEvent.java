package controller.event;

import model.Tank;

public class TankCreatedEvent extends GenericEvent {
	public Tank tank;
	
	public TankCreatedEvent(Tank tank) {
		this.tank = tank;
	}
}