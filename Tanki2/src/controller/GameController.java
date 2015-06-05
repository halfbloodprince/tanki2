package controller;

import view.DirtMap;
import view.GameView;
import view.ShotAnimation;
import model.GameModel;
import model.Shot;
import model.Tank;
import controller.event.GenericEvent;

import java.util.concurrent.LinkedBlockingQueue;

public class GameController implements Runnable {
	GameModel model;
	GameView view;
	LinkedBlockingQueue <GenericEvent> queue;
	
	public GameController (GameModel gameModel, GameView gameView) {
		queue = new LinkedBlockingQueue <GenericEvent> ();
		model = gameModel;
		view = gameView;
		view.SetController (this);
	}
	
	public void AddEvent (GenericEvent e)
	{
		queue.add (e);
	}
	
	/**
	 * Start the game
	 */
	private void startGame() {
		view.enableCanvas();
		DirtMap map = new DirtMap(view.getCanvas().getWidth(), view.getCanvas().getHeight());
		model.setGrid(map);
		view.getCanvas().setMap(map);
	}
	
	Tank test_tank;
	public void run() {

		startGame();
		
		try {
			test_tank = model.spawnTank(600);
			view.getCanvas().addSprite(test_tank);
		}

		catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		while (true)
		{
			GenericEvent event;
			try 
			{
				event = queue.take();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}

			try {
				Shot shot = model.shoot(test_tank);
				ShotAnimation anim = new ShotAnimation(shot);
				anim.setDuration(model.calcShotDuration(shot));
				view.getCanvas().addAnimation(anim);
			}
			catch (Exception e) {
				System.out.println("Exception: " + e);
			}
			// execute event
		}
	}
}
