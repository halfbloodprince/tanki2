package view;

import java.awt.Graphics;
import java.io.IOException;

import common.Constants;
import model.Shot;

public class ShotAnimation extends Animation {
	private Shot shot;
	private Sprite bullet;
	
	public ShotAnimation(Shot shot) throws IOException {
		this.shot = shot;
		this.bullet = new Sprite(Constants.BulletImage);
	}
	
	public void paint (Graphics g) {
		g.drawImage(bullet.getImg(), (int)shot.getX(), (int)shot.getY(), null);
	}
}