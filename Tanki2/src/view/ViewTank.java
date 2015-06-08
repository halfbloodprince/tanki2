package view;

import java.util.Map;
import java.io.IOException;

import model.Tank;

public class ViewTank {
	private TankSprite sprite;
	public double power, angle;
	public int weaponID;
	
	public ViewTank (Tank t, GameView v) {
		try { sprite = new TankSprite (t, v); } catch (IOException e) {}
		v.getCanvas().addSprite(sprite);
		power = 0.5;
		angle = -0.9;
		weaponID = 0;
	}
}
