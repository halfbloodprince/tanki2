package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import common.Constants;
import model.Tank;

public class TankSprite extends Sprite {
	/// the relevant tank
	private Tank tank;
	// private int visibleHP;

	public TankSprite (Tank t) throws IOException {
		super (Constants.DefaultTankImage);
		tank = t;
		// visibleHP = t.getHP();
	}
	
	public void updateHP() {
		// visibleHP = tank.getHP();
	}

	public int getX() {
		return tank.getX() - img.getWidth()/2;
	}
	
	public int getY() {
		return tank.getY() - img.getHeight();
	}
	
	public void paint(Graphics g) {
		if (enabled) {
			super.paint(g);
			g.setColor(Color.getHSBColor((float)tank.getHP()/300, 1.0f, 0.8f));
			g.fillRect(getX() - 3, getY() - 10, tank.getHP()/3, 5);
		}
	}
	
	public Tank getTank() {
		return tank;
	}
}
