package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import controller.event.GameOverEvent;
import controller.event.GenericEvent;
import controller.event.NewGameEvent;
import controller.event.NextTurnEvent;
import controller.event.TankCreatedEvent;
import controller.event.TankDestroyedEvent;
import view.handler.DmgDealtHandler;
import view.handler.ExplosionDoneHandler;
import view.handler.ExplosionHandler;
import view.handler.GameOverHandler;
import view.handler.NewGameHandler;
import view.handler.NextTurnHandler;
import view.handler.ShootHandler;
import view.handler.TankCreatedHandler;
import view.handler.TankDestroyedHandler;
import controller.event.ShootEvent;
import view.handler.ProjectileCreatedHandler;
import controller.event.ProjectileCreatedEvent;

/**
 * View part of mvc. Can handle many events.
 * @author Severus
 *
 */
public final class GameView {
	public enum ViewMode {
		IN_MENU,
		IN_GAME,
	};
	
	private GameWindow window;
	private GameCanvas canvas;
	private GameTimer renderTimer;
	GameController controller;
	KeyboardHandler keyboard;
	EventHandler handler;
	private Tank focusedTank;
	private boolean switchWeapons;
	Map <Integer, ViewTank> tanks;
	private Menu menu;
	private ViewMode mode;
	FocusListener focusRetainer;
	
	public void SetController (GameController c) { 
		controller = c; 
		menu.setController(c);
	}

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

	/**
	 * Create new game view
	 * @throws IOException Can be thrown when some files cannot be opened
	 */
	public GameView() throws IOException {
		switchWeapons = false;
		tanks = new HashMap <Integer, ViewTank> ();
		keyboard = new KeyboardHandler (this);

		window = new GameWindow(Constants.DefaultWindowWidth, Constants.DefaultWindowHeight);
		// todo: consider moving the following to GameWindow constructor
		window.addWindowListener(new BasicWindowMonitor());
		window.addKeyListener (keyboard);
		window.requestFocusInWindow();
		
		focusRetainer = new FocusListener() {
            public void focusGained(FocusEvent e) { }
            public void focusLost(FocusEvent e) { e.getComponent().requestFocus(); }
        };
        window.addFocusListener(focusRetainer);
        
		canvas = new GameCanvas(window.width, window.height);
		window.add(canvas, BorderLayout.NORTH);
		
	    window.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu = new Menu(controller);
		window.add(menu);

		handler = new EventHandler ();
		handler.put (ShootEvent.class, new ShootHandler (this));
		handler.put (ProjectileCreatedEvent.class, new ProjectileCreatedHandler (this));
		handler.put (ExplosionEvent.class, new ExplosionHandler(this));
		handler.put (ExplosionDoneEvent.class, new ExplosionDoneHandler(this));
		handler.put (DmgDealtEvent.class, new DmgDealtHandler(this));
		handler.put (NextTurnEvent.class, new NextTurnHandler(this));
		handler.put (TankDestroyedEvent.class, new TankDestroyedHandler(this));
		handler.put (GameOverEvent.class, new GameOverHandler(this));
		handler.put (NewGameEvent.class, new NewGameHandler(this));
		handler.put (TankCreatedEvent.class, new TankCreatedHandler(this));
	}
	
	private void setFocusRetainer(boolean b) {
		if (b) {
			focusRetainer = new FocusListener() {
	            public void focusGained(FocusEvent e) { }
	            public void focusLost(FocusEvent e) { e.getComponent().requestFocus(); }
	        };
			window.addFocusListener(focusRetainer);
		} else {
			window.removeFocusListener(focusRetainer);
		}
	}
	
	public void setViewMode(ViewMode mode) {
		this.mode = mode;
		switch(mode) {
		case IN_MENU:
			canvas.setVisible(false);
			canvas.setEnabled(false);
			disableCanvas();
			menu.setVisible(true);
			menu.setEnabled(true);
			window.setSize(200,200);
			setFocusRetainer(false);
			break;
		case IN_GAME:
			enableCanvas();
			canvas.setEnabled(true);
			canvas.setVisible(true);
			menu.setVisible(false);
			menu.setEnabled(false);
			window.setSize(Constants.DefaultWindowWidth, Constants.DefaultWindowHeight);
			window.requestFocus();
			setFocusRetainer(true);
			break;
		}
	}

	/**
	 * @return Rendering canvas associated with this view
	 */
	public GameCanvas getCanvas() {
		return canvas;
	}

	public boolean getSwitchWeapons () { return switchWeapons; }
	public void setSwitchWeapons (boolean value) { switchWeapons = value; }

	public void Shoot () {
		controller.send(new ShootEvent (focusedTank.getID(), 0, focusedTank.getAngle(), focusedTank.getPower()));
	}
	public void Shoot2 () {
		controller.send(new ShootEvent (focusedTank.getID(), 1, focusedTank.getAngle(), focusedTank.getPower()));
	}

	/**
	 * Start rendering canvas
	 */
	public void enableCanvas() {
		renderTimer = new GameTimer(Constants.FPS);
		renderTimer.scheduleRenderTask(canvas.getRenderTask());
	}
	
	/**
	 * Stop rendering canvas
	 */
	public void disableCanvas() {
		if (renderTimer != null)
			renderTimer.cancel();
	}
	
	public void addTank (Tank tank) {
		tanks.put (tank.getID(), new ViewTank (tank, this));
	}

	public ViewTank getTank (int id) {
		return tanks.get(id);
	}
	
	/**
	 * Set tank to be marked as focused
	 * @param tank
	 */
	public void setFocusedTank(Tank tank) {
		focusedTank = tank;
		canvas.setFocusedTank(tank);
	}
	
	/**
	 * Get a tank which is currently marked as focused
	 * @return a tank
	 */
	public Tank getFocusedTank() {
		return focusedTank;
	}

	/**
	 * Show an explosion
	 * @param explosion
	 */
	public void scheduleExplosion(Explosion explosion) {
		getCanvas().addAnimation(explosion);
		controller.AddDelayedEvent(new ExplosionDoneEvent(), explosion.duration);
	}

	/**
	 * Show that game has ended
	 * @param winner
	 */
	public void gameOver(int winner) {
		DrawableString s = new DrawableString("Winner winner chicken dinner!");
		s.setPosition(canvas.getWidth()/2 - 100, canvas.getHeight()/2);
		canvas.addDrawable(s);
	}
}
