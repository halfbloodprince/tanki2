package view;

import java.io.IOException;

import common.Constants;
import model.Tank;

public class TankSprite extends Sprite {
	/// the relevant tank
	private Tank tank;

	public TankSprite (Tank t) throws IOException {
		super (Constants.DefaultTankImage);
		tank = t;
	}

	public int getX() {
		return tank.getX() - img.getWidth()/2;
	}
	
	public int getY() {
		return tank.getY() - img.getHeight()/2;
	}
}
