package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DrawableString implements Drawable {
	public String string;
	private int x, y;
	private Font font;

	public DrawableString(String string) {
		this.string = string;
		font = new Font("Serif", Font.PLAIN, 30) ;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(float size) {
		font.deriveFont(size);
	}
	
	public void paint (Graphics g) {
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(string, x, y);
	}
}
