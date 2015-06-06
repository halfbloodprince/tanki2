package view;

import java.awt.Color;
import java.awt.Graphics;

import common.Constants;

public class Explosion extends Animation {
	int x, y, r;

	public Explosion(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		
		setDuration(1000);
		start();
	}

	public void paint (Graphics g) {
		int t = (int)(System.currentTimeMillis() - begin);
		t *= Constants.TimeScale;
		int size = 2*(int)(r * t / duration);
		
		g.setColor(Color.white);
		g.drawOval(x-size, y-size, 2*size, 2*size);
	}
}
