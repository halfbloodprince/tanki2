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
		setPosition (t.getX(), t.getY());
	}
}
