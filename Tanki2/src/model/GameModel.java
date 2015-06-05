package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import controller.EventHandler;
import controller.event.GenericEvent;
import controller.event.ShootEvent;
import model.handler.ShootHandler;

public class GameModel {
	Map <Integer, Tank> tanks;
	Grid grid;
	EventHandler handler;

	/**
	 * Create new game model
	 * @param view View on which this model should present itself
	 */
	public GameModel()
	{
		tanks = new HashMap <Integer, Tank> ();
		handler = new EventHandler ();
		handler.put (ShootEvent.class, new ShootHandler (this));
	}

	public void handle (GenericEvent e) { handler.handle(e); }
	
	/**
	 * Create a tank and put it on the battlefield
	 * @param x X position of the tank
	 * @param y Y position of the tank
	 * @return Created tank
	 * @throws Exception
	 */
	public Tank spawnTank(int x, int y) throws Exception {
		Tank tank = new Tank ();
		tank.setPosition(x, y);
		tanks.put (tank.getID(), tank);
		return tank;
	}

	public Tank getTank (int id) {
		return tanks.get(id);
	}

	public Tank spawnTank(int x) throws Exception {
		return spawnTank(x, grid.getHeight() - grid.getSurfaceHeight(x) - 1);
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
	
	public Shot shoot(Tank tank, double power, double angle, int weaponID) {
		Shot shot = new SimpleShot(tank.getX(), tank.getY(), power, angle);
		return shot;
	}
}
