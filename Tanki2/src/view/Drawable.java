package view;

import java.awt.Graphics;

public interface Drawable {
	/**
	 * Draw this
	 * @param g Graphics to paint onto
	 */
	public abstract void paint (Graphics g);
}
