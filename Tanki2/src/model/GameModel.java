package model;

import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.EventHandler;
import controller.GameController;
import controller.event.DmgDealtEvent;
import controller.event.ExplosionEvent;
import controller.event.GameOverEvent;
import controller.event.GenericEvent;
import controller.event.NextTurnEvent;
import controller.event.ShootEvent;
import controller.event.ModelTimerEvent;
import controller.event.ProjectileCreatedEvent;
import controller.event.TankDestroyedEvent;
import model.handler.ModelTimerHandler;
import model.handler.ExplosionHandler;
import model.handler.ShootHandler;

/**
 * Main class for model part of MVC. Can perform multiple operations, including physics calculations and generating events.
 */
public class GameModel {
	Map <Integer, Tank> tanks;
	ArrayList <Shot> projectiles;
	public Grid grid;
	EventHandler handler;
	GameController controller;
	public boolean enableControl;
	Integer currentTankId;
	List<Integer> order;
	private boolean somethingExploded;

	/**
	 * Set new controller object to this model
	 * @param c Controller to be used
	 */
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
		handler.put (ExplosionEvent.class, new ExplosionHandler (this));
		handler.put (ModelTimerEvent.class, new ModelTimerHandler (this));		
		order = new ArrayList<Integer>();		
		enableControl = true;
		somethingExploded = false;
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
		order.add(tank.getID());
		return tank;
	}

	public void update ()
	{
		ArrayList <Integer> removables = new ArrayList <Integer> ();
		for (int i = 0; i < projectiles.size(); ++i) {
			Shot shot = projectiles.get(i);
			shot.move();
			if (grid.occupied(shot.getX(), shot.getY())) {
				removables.add(i);
				controller.AddEvent(new ExplosionEvent(shot));
			}
		}
		for (int i = 0; i < removables.size(); ++i) {
			projectiles.remove (removables.get(i) - i);
		}
		
		Iterator<Map.Entry<Integer, Tank>> iter = tanks.entrySet().iterator(); 
		while(iter.hasNext()) {
			Tank tank = iter.next().getValue();
			if (!grid.occupied(tank.getX(), tank.getY() + 1)) {
				tank.setPosition(tank.getX(), tank.getY() + 4);
			}
			
			if (tank.getHP() <= 0) {
				controller.AddEvent(new TankDestroyedEvent(tank));
				order.remove(new Integer(tank.getID()));
				iter.remove();
			}
		}
		
		if (!enableControl && somethingExploded && projectiles.isEmpty()) {
			nextTurn();
		}
	}
	
	public int projectilesCount() {
		return projectiles.size();
	}

	/*
	 * return true if current turn is totally finished
	 */
	public boolean turnFinished() {
		if (!projectiles.isEmpty())
			return false;

		for (Tank tank : tanks.values()){ 
			if (!grid.occupied(tank.getX(), tank.getY() + 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get winner if game is ended
	 * @return Winning team id or -1 if game is not finished
	 */
	public int getWinner() {
		int winner = -1;

		for (Tank tank : tanks.values()) {
			if (winner == -1)
				winner = tank.getTeamID();
			
			if (winner != tank.getTeamID())
				return -1;
		}

		return winner;
	}
	
	/**
	 * Finish this turn and start next one
	 */
	public void nextTurn() {
		if (!turnFinished()) {
			return;
		}
		
		int winner = getWinner();
		if (winner != -1) {
			controller.AddEvent(new GameOverEvent(winner));
			enableControl = false;
			somethingExploded = false;
			return;
		}
		
		somethingExploded = false;
		enableControl = true;
		int index = order.indexOf(currentTankId);
		currentTankId = order.get((index + 1) % order.size());
		controller.AddEvent(new NextTurnEvent(getTank(currentTankId)));
	}
	
	/**
	 * Get tank of given id
	 * @param id Id of tank
	 * @return Tank which id equals given
	 */
	public Tank getTank (int id) {
		return tanks.get(id);
	}

	/**
	 * Spawn new tank in specified location. Use information about ground height to determine y location.
	 * @param x X position
	 * @return New tank
	 * @throws Exception
	 */
	public Tank spawnTank(int x) throws Exception {
		return spawnTank(x, grid.getHeight() - grid.getSurfaceHeight(x) - 1);
	}
	
	/**
	 * Generate new random map
	 * @param grid Grid on which map should be generated
	 */
	public void generateMap(Grid grid) {
		/* TODO deterministic way to do this */
		Random gen = new Random();
		gen.setSeed(1337);
		double div = 0;
		double h = 1.0;
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
	
	/**
	 * Use new grid object
	 * @param grid Grid to be used
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
		generateMap(grid);
	}
	
	/**
	 * Add projectile from given shot
	 * @param x Shot which was made
	 */
	public void addProjectile (Shot x) {
		projectiles.add (x);
		controller.AddEvent(new ProjectileCreatedEvent (x));
	}

	/**
	 * Deal damage from given shot to all tanks
	 * @param shot
	 */
	public void dealDmg(Shot shot) {
		somethingExploded = true;
		for (Tank tank : tanks.values()) {
			int res = shot.dealDmg(tank);
			if (res != 0)
				controller.AddEvent(new DmgDealtEvent (tank, res));
		}
	}

	/**
	 * Initiate all game parameters
	 */
	public void startGame() {
		/* TODO What if there are no tanks in game? */
		/* TODO Set order properly */
		order.clear();
		for (Integer id : tanks.keySet()) {
			order.add(id);
		}

		currentTankId = order.get(order.size() - 1);
		nextTurn();
	}
}
