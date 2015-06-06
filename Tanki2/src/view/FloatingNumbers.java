package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import model.Shot;
import common.Constants;

public class FloatingNumbers extends Animation {
	private int x, y, value;
	
	public FloatingNumbers(int x, int y, int value) throws IOException {
		this.x = x;
		this.y = y;
		this.value = value;
		this.duration = 1500;
	}
	
	public void paint (Graphics g) {
		int t = (int)(System.currentTimeMillis() - begin);
		int dx = (int)(20 * Math.sin((double)t/100));
		int dy = t / 20;
		g.setColor(Color.RED);
		g.drawString(String.valueOf(value), x + dx , y - dy);
	}
}
