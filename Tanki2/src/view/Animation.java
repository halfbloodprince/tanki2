package view;

import common.Constants;

/**
 * Base class for all time-related drawables. It has control on its time and after duration will be removed by view.
 * @author Severus
 *
 */
public abstract class Animation implements Drawable{
	protected long duration;
	protected long begin;
	
	public void start() {
		begin = System.currentTimeMillis();
	}
	
	/**
	 * Informs if animation has ended
	 * @return true if animation is done
	 */
	public boolean done() {
		return System.currentTimeMillis() - begin >= duration;
	}
	
	/**
	 * Set the duration of this animation
	 * @param d
	 */
	public void setDuration(long d) {
		duration = d;
	}
}
