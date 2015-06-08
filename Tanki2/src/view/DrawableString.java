package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A string which is drawable
 * @author Severus
 *
 */
public class DrawableString implements Drawable {
	public String string;
	private int x, y;
	private Font font;

	/**
	 * Construct this string
	 * @param string String value of this drawable string
	 */
	public DrawableString(String string) {
		this.string = string;
		font = new Font("Serif", Font.PLAIN, 30) ;
	}
	
	/**
	 * Set position of this string
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set font size
	 * @param size
	 */
	public void setSize(float size) {
		font.deriveFont(size);
	}
	
	/*
	 * (non-Javadoc)
	 * @see view.Drawable#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g) {
		Font tmp = g.getFont();

		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(string, x, y);
	
		g.setFont(tmp);
	}
}
