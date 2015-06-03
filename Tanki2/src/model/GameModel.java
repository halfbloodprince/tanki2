package model;

import java.util.List;

public class GameModel {
	List<Tank> tanks;
	Grid grid;

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
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/**
	 * Calculate duration of shot (i.e. bullet collision point)
	 * @param shot Shot to be considered
	 * @return Moment of collision with something
	 */
	public int calcShotDuration(Shot shot) {
		int t = 0;
		while (true) {
			if (grid.occupied((int)shot.getBulletX(t), (int)shot.getBulletY(t))) {
				System.out.println("hit point: " + shot.getBulletX(t) + ", " + shot.getBulletY(t));
				return t;
			}
			t++;
		}
	}
	
	public Shot shoot(Tank tank) {
		Shot shot = new SimpleShot(tank.getX(), tank.getY(), tank.getPower(), tank.getAngle());
		return shot;
	}
}
