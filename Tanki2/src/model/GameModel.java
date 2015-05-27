package model;

import java.util.List;

public class GameModel {
	List<Tank> tanks;

	/**
	 * Create new game model
	 * @param view View on which this model should present itself
	 */
	public GameModel() {
	}
	
	/**
	 * Create a tank and put it on the battlefield
	 * @param x X position of the tank
	 * @param y Y position of the tank
	 * @return Created tank
	 * @throws Exception
	 */
	public Tank spawnTank(int x, int y) throws Exception {
		Tank tank = new Tank();
		tank.setPosition(x, y);
		return tank;
	}
	
	public Shot shoot(Tank tank) {
		Shot shot = new SimpleShot(tank.getX(), tank.getY(), tank.getPower(), tank.getAngle());
		return shot;
	}
}
