package controller;

import view.DirtMap;
import view.GameView;
import view.GameView.ViewMode;
import view.TankSprite;
import model.GameModel;
import model.Tank;
import controller.event.GenericEvent;
import controller.server.EventServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Controller part of MVC. Store event queue and send events to model and view.
 */
public class GameController implements Runnable {
	/**
	 * Task for sending event with delay
	 * @author Severus
	 *
	 */
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
	ServerListener server;

	Tank[] test_tank; // temporary for testing

	public GameController (GameModel gameModel, GameView gameView) {
		Thread serverThread = new Thread(new EventServer ());
		serverThread.start();
		queue = new LinkedBlockingQueue <GenericEvent> ();
		model = gameModel;
		view = gameView;
		model.SetController (this);
		view.SetController (this);
		delayingTimer = new Timer();
		server = new ServerListener (this);
		server.start();
		test_tank = new Tank[2];
	}

	/**
	 * Add new event to the queue
	 * @param e
	 */
	public void AddEvent (GenericEvent e)
	{
		if (e != null) queue.add (e);
	}
	
	public void send (GenericEvent msg)
	{
		server.send (msg);
	}

	/**
	 * Schedule an event to be created after specified period of time
	 * @param e Event to be created
	 * @param delay Delay in miliseconds
	 */
	public void AddDelayedEvent(GenericEvent e, long delay) {
		delayingTimer.schedule(new AddEventTask(this, e), delay);
	}

	/**
	 * Start the game
	 */
	public void startGame() {
		DirtMap map = new DirtMap(view.getCanvas().getWidth(), view.getCanvas().getHeight());
		model.setGrid(map);
		view.getCanvas().setMap(map);
		
		try {
			test_tank[0] = model.spawnTank(600);
			test_tank[0].setTeamId(0);
			view.addTank(test_tank[0]);
			test_tank[1] = model.spawnTank(300);
			test_tank[1].setTeamId(1);
			view.getCanvas().addSprite(new TankSprite(test_tank[1], view));
		}

		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	/**
	 * Run controller task
	 */
	public void run() {

		//startGame();
		view.setViewMode(ViewMode.IN_MENU);

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
