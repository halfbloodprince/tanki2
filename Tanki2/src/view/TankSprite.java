package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.ICC_ProfileRGB;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.IOException;

import common.Constants;
import model.Tank;

public class TankSprite extends Sprite {
	/// the relevant tank
	private Tank tank;
	// private int visibleHP;

	public TankSprite (Tank t) throws IOException {
		super (Constants.TankImages[t.getTeamID()]);
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
			g.drawImage(img, getX(), getY(), null);
			g.setColor(Color.getHSBColor((float)tank.getHP()/300, 1.0f, 0.8f));
			g.fillRect(getX() - 3, getY() - 10, tank.getHP()/3, 5);
		}
	}
	
	public Tank getTank() {
		return tank;
	}
}
