package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
	private GameView gameView;

	public KeyboardHandler (GameView gv)
	{
		gameView = gv;
	}

	@Override
	public void keyPressed (KeyEvent kbEvent)
	{
		// todo switch-case multiple keys
		if(kbEvent.getKeyCode() == KeyEvent.VK_SPACE)
		{
			gameView.Shoot();
		}
		if(kbEvent.getKeyCode() == KeyEvent.VK_M)
		{
			gameView.Shoot2();
		}
	}

	@Override
	public void keyReleased(KeyEvent keyboardEvent)
	{
	}

	@Override
	public void keyTyped(KeyEvent keyboardEvent)
	{
	}
}
