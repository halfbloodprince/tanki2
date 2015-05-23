package view;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.GameWindow;
import common.Constants;

public final class GameView {
	private GameWindow window;

	public class BasicWindowMonitor extends WindowAdapter {
		  public void windowClosing(WindowEvent e) {
		    Window w = e.getWindow();
		    w.setVisible(false);
		    w.dispose();
		    System.exit(0);
		  }
	}
	
	public GameView() {
		window = new GameWindow(Constants.DefaultWindowWidth, Constants.DefaultWindowHeight);
		window.setVisible(true);
		window.addWindowListener(new BasicWindowMonitor());
	}
}
