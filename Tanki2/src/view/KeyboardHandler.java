package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Tank;

public class KeyboardHandler implements KeyListener {
	private GameView gameView;

	public KeyboardHandler (GameView gv)
	{
		gameView = gv;
	}

	public void keyPressed (KeyEvent kbEvent)
	{
		Tank tank = gameView.getFocusedTank();
	
		/* TODO: handle keys when no tank is focused */
		if (tank == null)
			return;
		
		switch(kbEvent.getKeyCode())
		{
			case KeyEvent.VK_SPACE: gameView.Shoot(); break;
			case KeyEvent.VK_M: gameView.Shoot2(); break;
			case KeyEvent.VK_W: tank.addPower( 0.05); break;
			case KeyEvent.VK_S: tank.addPower(-0.05); break;
			case KeyEvent.VK_A: tank.addAngle(-0.1); break;
			case KeyEvent.VK_D: tank.addAngle( 0.1); break;
			case KeyEvent.VK_ALT: gameView.setSwitchWeapons(true); break;
			default: break;
		}
	}

	public void keyReleased(KeyEvent kbEvent)
	{
		Tank tank = gameView.getFocusedTank();
	
		/* TODO: handle keys when no tank is focused */
		if (tank == null)
			return;

		switch(kbEvent.getKeyCode())
		{
			case KeyEvent.VK_ALT: gameView.setSwitchWeapons(false); break;
			default: break;
		}
	}

	public void keyTyped(KeyEvent kbEvent)
	{
	}
}
