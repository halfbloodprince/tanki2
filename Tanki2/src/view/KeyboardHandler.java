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
		switch(kbEvent.getKeyCode())
		{
			case KeyEvent.VK_SPACE: gameView.Shoot(); break;
			case KeyEvent.VK_M: gameView.Shoot2(); break;
			case KeyEvent.VK_W: gameView.AddPower( 0.1); break;
			case KeyEvent.VK_S: gameView.AddPower(-0.1); break;
			case KeyEvent.VK_A: gameView.AddAngle(-0.1); break;
			case KeyEvent.VK_D: gameView.AddAngle( 0.1); break;
			default: break;
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
