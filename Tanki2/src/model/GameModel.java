package model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import controller.EventHandler;
import controller.GameController;
import controller.event.GenericEvent;
import controller.event.ShootEvent;
import controller.event.ProjectileCreatedEvent;
import model.handler.ShootHandler;

public class GameModel {
	Map <Integer, Tank> tanks;
	ArrayList <Shot> projectiles;
	Grid grid;
	EventHandler handler;
	GameController controller;

	public void SetController (GameController c) { controller = c; }

	/**
	 * Create new game model
	 * @param view View on which this model should present itself
	 */
	public GameModel()
	{
		projectiles = new ArrayList <Shot> ();
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

	public void update ()
	{
		ArrayList <Integer> removables = new ArrayList <Integer> ();
		for (int i = 0; i < projectiles.size(); ++i) {
			Shot shot = projectiles.get(i);
			shot.move();
			if (grid.occupied(shot.getX(), shot.getY())) removables.add(i);
			// todo: explosion + projectileRemoved event
		}
		for (int i = 0; i < removables.size(); ++i) {
			projectiles.remove (removables.get(i) - i);
		}
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
	
	public void addProjectile (Shot x) {
		projectiles.add (x);
		controller.AddEvent(new ProjectileCreatedEvent (x));
	}
}
