package view;

import java.awt.Graphics;

/**
 * Interface for everything specific which can be showed on the screen
 * @author Severus
 *
 */
public interface Drawable {
	/**
	 * Draw this
	 * @param g Graphics to paint onto
	 */
	public abstract void paint (Graphics g);
}
