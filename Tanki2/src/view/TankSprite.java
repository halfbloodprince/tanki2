package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
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
	private Font font;
	private GameView view;

	public TankSprite (Tank t, GameView gv) throws IOException {
		super (Constants.TankImages[t.getTeamID()]);
		tank = t;
		view = gv;
		font = new Font("SansSerif", Font.PLAIN, 10) ;
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
			if (view.getSwitchWeapons()) {
				g.setFont(font);
				g.setColor(Color.WHITE);
				g.drawString("SMALL SHOT", getX()-4, getY()-10);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(getX() - 4, getY() - 11, 100/3+2, 7);
				g.setColor(Color.getHSBColor((float)tank.getHP()/300, 1.0f, 0.8f));
				g.fillRect(getX() - 3, getY() - 10, tank.getHP()/3, 5);
			}
		}
	}
	
	public Tank getTank() {
		return tank;
	}
}
