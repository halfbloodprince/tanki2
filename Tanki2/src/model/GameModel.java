package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {
	List<Tank> tanks;
	Grid grid;

	/**
	 * Create new game model
	 * @param view View on which this model should present itself
	 */
	public GameModel() {
		tanks = new ArrayList <Tank> ();
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
		tanks.add (tank);
		return tank;
	}
	
	public Tank spawnTank(int x) throws Exception {
		return spawnTank(x, grid.getHeight() - grid.getSurfaceHeight(x));
	}
	
	public void generateMap(Grid grid) {
		/* TODO deterministic way to do this */
		Random gen = new Random();
		double div = 0;
		double h = 0.2;
		double up = 0.7 * grid.getHeight();
		double down = 0.1 * grid.getHeight();
		double s = down + gen.nextDouble() * (up - down) / 2;

		for (int i = 0; i < grid.getWidth(); ++i) {
			div += h/2 - h * gen.nextDouble();
			if (s <= down)
				div = h;
			if (s >= up)
				div = -h;

			s += div;

			for (int j = 1; j < s; ++j) {
				grid.setTile(i, grid.getHeight() - j);
			}
		}
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
		generateMap(grid);
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
