package view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import view.GameWindow;
import common.Constants;
import controller.GameTimer;
import view.GameCanvas;

public final class GameView {
	private GameWindow window;
	private GameCanvas canvas;
	private GameTimer render_timer;

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
		window = new GameWindow(Constants.DefaultWindowWidth, Constants.DefaultWindowHeight);
		window.setVisible(true);
		window.addWindowListener(new BasicWindowMonitor());
		
		canvas = new GameCanvas(window.width, window.height);
		window.add(canvas);
		
		canvas.addSprite(new Sprite("media/img/tank.png"));
		
		render_timer = new GameTimer(Constants.FPS);
	}
	
	/**
	 * @return Rendering canvas associated with this view
	 */
	public GameCanvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Start rendering canvas
	 */
	public void enableCanvas() {
		render_timer.scheduleRenderTask(canvas.getRenderTask());
	}
}
