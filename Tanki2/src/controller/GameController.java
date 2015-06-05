package controller;

import view.DirtMap;
import view.GameView;
import view.TankSprite;
import model.GameModel;
import model.Tank;
import model.EventServer;
import controller.event.GenericEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController implements Runnable {
	public class AddEventTask extends TimerTask {
		private GameController controller;
		private GenericEvent event;
		
		AddEventTask(GameController controller, GenericEvent event) {
			this.controller = controller;
			this.event = event;
		}
		
		public void run() {
			controller.AddEvent(event);
		}
	}

	GameModel model;
	GameView view;
	LinkedBlockingQueue <GenericEvent> queue;
	Timer delayingTimer;

	Tank test_tank; // temporary for testing

	public GameController (GameModel gameModel, GameView gameView) {
		queue = new LinkedBlockingQueue <GenericEvent> ();
		model = gameModel;
		view = gameView;
		model.SetController (this);
		view.SetController (this);
		delayingTimer = new Timer();
		EventServer es = new EventServer (model); //fixme replace with real server
	}

	public void AddEvent (GenericEvent e)
	{
		queue.add (e);
	}
	
	public void AddDelayedEvent(GenericEvent e, long delay) {
		delayingTimer.schedule(new AddEventTask(this, e), delay);
	}

	/**
	 * Start the game
	 */
	private void startGame() {
		view.enableCanvas();
		DirtMap map = new DirtMap(view.getCanvas().getWidth(), view.getCanvas().getHeight());
		model.setGrid(map);
		view.getCanvas().setMap(map);
		
		try {
			test_tank = model.spawnTank(600);
			view.getCanvas().addSprite(new TankSprite(test_tank));

			/* TODO focusing proper tank */
			view.setFocusedTank(test_tank);
		}

		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public void run() {

		startGame();

		while (true)
		{
			GenericEvent event = null;
			try 
			{
				event = queue.take();
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}

			if (event != null) {
				model.handle (event);
				view.handle (event);
			}
		}
	}
}
