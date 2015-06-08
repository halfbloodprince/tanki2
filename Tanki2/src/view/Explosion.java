package view;

import java.awt.Color;
import java.awt.Graphics;

import common.Constants;

/**
 * Animation of explosion
 * @author Severus
 *
 */
public class Explosion extends Animation {
	int x, y, r;

	/**
	 * Create this explosion
	 * @param x x position
	 * @param y y position
	 * @param r Radius
	 */
	public Explosion(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		
		setDuration(1000);
		start();
	}

	/*
	 * (non-Javadoc)
	 * @see view.Drawable#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g) {
		int t = (int)(System.currentTimeMillis() - begin);
		int size = (int)(r * t / duration);
		
		g.setColor(Color.white);
		g.drawOval(x-size, y-size, 2*size, 2*size);
	}
}
