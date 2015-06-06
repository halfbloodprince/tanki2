package model.handler;

import model.GameModel;
import model.Tank;
import model.Shot;
import controller.GenericHandler;
import controller.event.GenericEvent;
import controller.event.ShootEvent;
import java.util.ArrayList;

public class ShootHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public ShootHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		if (model.enableControl) {
			ShootEvent event = (ShootEvent) e;
			Tank tank = model.getTank(event.tankID);
			if (tank == null) return;
	
			ArrayList <Shot> shots = tank.shoot(event.weaponID, event.power, event.angle);
			for (Shot shot : shots) model.addProjectile(shot);
			model.enableControl = false;
		}
	}
}
