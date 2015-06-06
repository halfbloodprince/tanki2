package view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import model.Tank;
import view.GameWindow;
import view.GameCanvas;
import common.Constants;
import controller.EventHandler;
import controller.GameController;
import controller.event.DmgDealtEvent;
import controller.event.ExplosionDoneEvent;
import controller.event.ExplosionEvent;
import controller.event.GenericEvent;
import controller.event.NextTurnEvent;
import view.handler.DmgDealtHandler;
import view.handler.ExplosionDoneHandler;
import view.handler.ExplosionHandler;
import view.handler.NextTurnHandler;
import view.handler.ShootHandler;
import controller.event.ShootEvent;
import view.handler.ProjectileCreatedHandler;
import controller.event.ProjectileCreatedEvent;

public final class GameView {
	private GameWindow window;
	private GameCanvas canvas;
	private GameTimer renderTimer;
	GameController controller;
	KeyboardHandler keyboard;
	EventHandler handler;
	private Tank focusedTank;
	
	public void SetController (GameController c) { controller = c; }

	public void handle (GenericEvent e) { handler.handle(e); }

	public class BasicWindowMonitor extends WindowAdapter {
		/**
		 * Closing callback of the main window
		 */
		  public void windowClosing(WindowEvent e) {
		    Window w = e.getWindow();
		    w.setVisible(false);
		    w.dispose();
		    System.exit(0);
		  }
	}
/*
	public void AddPower (double amount) {
		focusedTank.power += amount;
		System.out.format("Power is now %f\n", power);
	}

	public void AddAngle (double amount) {
		focusedTank.angle += amount;
		System.out.format("Angle is now %f\n", angle);
	}
	*/
	/**
	 * Create new game view
	 * @throws IOException Can be thrown when some files cannot be opened
	 */
	public GameView() throws IOException {
		keyboard = new KeyboardHandler (this);

		window = new GameWindow(Constants.DefaultWindowWidth, Constants.DefaultWindowHeight);
		// todo: consider moving the following to GameWindow constructor
		window.setVisible(true);
		window.addWindowListener(new BasicWindowMonitor());
		window.addKeyListener (keyboard);
		window.requestFocusInWindow();

		// ugly hack to make sure focus is retained
		window.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { }
            public void focusLost(FocusEvent e) { e.getComponent().requestFocus(); }
        });
		
		canvas = new GameCanvas(window.width, window.height);
		window.add(canvas);

		renderTimer = new GameTimer(Constants.FPS);

		handler = new EventHandler ();
		handler.put (ShootEvent.class, new ShootHandler (this));
		handler.put (ProjectileCreatedEvent.class, new ProjectileCreatedHandler (this));
		handler.put (ExplosionEvent.class, new ExplosionHandler(this));
		handler.put (ExplosionDoneEvent.class, new ExplosionDoneHandler(this));
		handler.put (DmgDealtEvent.class, new DmgDealtHandler(this));
<<<<<<< HEAD
=======
		handler.put (NextTurnEvent.class, new NextTurnHandler(this));

		
		sender = new ServerSender ();
>>>>>>> remotes/origin/master
	}

	/**
	 * @return Rendering canvas associated with this view
	 */
	public GameCanvas getCanvas() {
		return canvas;
	}

	public void Shoot () {
		controller.AddEvent(new ShootEvent (focusedTank.getID(), 0, focusedTank.getAngle(), focusedTank.getPower()));
		//String msg = new String ("SHOT 0 " + focusedTank.getAngle() + " " + focusedTank.getPower());
		// controller.send(msg);
	}
	public void Shoot2 () {
		controller.AddEvent(new ShootEvent (focusedTank.getID(), 1, focusedTank.getAngle(), focusedTank.getPower()));
		//String msg = new String ("SHOT 1 " + focusedTank.getAngle() + " " + focusedTank.getPower());
		//controller.send(msg);
	}

	/**
	 * Start rendering canvas
	 */
	public void enableCanvas() {
		renderTimer.scheduleRenderTask(canvas.getRenderTask());
	}
	
	/**
	 * Set tank to be marked as focused
	 * @param tank
	 */
	public void setFocusedTank(Tank tank) {
		focusedTank = tank;
		canvas.setFocusedTank(tank);
	}
	
	public Tank getFocusedTank() {
		return focusedTank;
	}

	public void scheduleExplosion(Explosion explosion) {
		getCanvas().addAnimation(explosion);
		controller.AddDelayedEvent(new ExplosionDoneEvent(), explosion.duration);
	}
}
