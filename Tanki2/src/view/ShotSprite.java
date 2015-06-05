package view;

import java.io.IOException;

import common.Constants;
import model.Shot;

public class ShotSprite extends Sprite {
	/// the relevant shot
	private Shot shot;

	public ShotSprite (Shot s) throws IOException {
		super (Constants.BulletImage);
		shot = s;
	}
	
	public int getX() {
		return shot.getX() - img.getWidth()/2;
	}
	
	public int getY() {
		return shot.getY() - img.getHeight()/2;
	}
}
