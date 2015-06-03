package model;

import java.util.List;
import java.util.Random;

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
	
	public Tank spawnTank(int x) throws Exception {
		return spawnTank(x, grid.getHeight() - grid.getSurfaceHeight(x));
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
		Random gen = new Random();
		int div = 0;
		int h = 2;
		int s = gen.nextInt(grid.getHeight());
		for (int i = 0; i < grid.getWidth(); ++i) {
			div = h - gen.nextInt(2*h-1) - 1;
			s += div;
			System.out.println("div: " + div);
			s = Math.min(s, grid.getHeight() - 2);
			//System.out.println("s: 
			for (int j = 1; j < s; ++j) {
				grid.setTile(i, grid.getHeight() - j);
			}
		}
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
