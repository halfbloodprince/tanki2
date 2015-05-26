package view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import view.GameWindow;
import common.Constants;
import view.GameCanvas;

public final class GameView {
	private GameWindow window;
	private GameCanvas canvas;

	public class BasicWindowMonitor extends WindowAdapter {
		  public void windowClosing(WindowEvent e) {
		    Window w = e.getWindow();
		    w.setVisible(false);
		    w.dispose();
		    System.exit(0);
		  }
	}
	
	public GameView() throws IOException {
		window = new GameWindow(Constants.DefaultWindowWidth, Constants.DefaultWindowHeight);
		window.setVisible(true);
		window.addWindowListener(new BasicWindowMonitor());
		
		canvas = new GameCanvas(window.width, window.height);
		window.add(canvas);
		
		canvas.addSprite(new Sprite("media/img/tank.png"));
	}
	
	public GameCanvas getCanvas() {
		return canvas;
	}
}
