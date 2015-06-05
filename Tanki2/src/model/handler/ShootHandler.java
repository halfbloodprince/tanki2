package model.handler;

import model.GameModel;
import model.Tank;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.ShootEvent;

public class ShootHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public ShootHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		ShootEvent event = (ShootEvent) e;
		Tank tank = model.getTank(event.tankID);
		if (tank == null) return;

		// todo: use an array of bullets (for multishot)
		model.addProjectile(tank.shoot(event.weaponID, event.power, event.angle));
	}
}
