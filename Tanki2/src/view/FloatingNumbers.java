package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import common.Constants;

/**
 * Animation of numbers floating nicely
 * @author Severus
 *
 */
public class FloatingNumbers extends Animation {
	private int x, y, value;
	
	/**
	 * Create this object
	 * @param x X starting position
	 * @param y Y starting position
	 * @param value Value of this numbers
	 * @throws IOException
	 */
	public FloatingNumbers(int x, int y, int value) throws IOException {
		this.x = x;
		this.y = y;
		this.value = value;
		this.duration = 1500;
	}
	
	/*
	 * (non-Javadoc)
	 * @see view.Drawable#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g) {
		int t = (int)(System.currentTimeMillis() - begin);
		int dx = (int)(20 * Math.sin((double)t/100));
		int dy = t / 20;
		g.setColor(Color.RED);
		g.drawString(String.valueOf(value), x + dx , y - dy);
	}
}
