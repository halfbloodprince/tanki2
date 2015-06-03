package view;

import java.awt.Graphics;

import common.Constants;

public abstract class Animation {
	protected long duration;
	protected long begin;
	
	public void start() {
		begin = System.currentTimeMillis();
	}
	
	/**
	 * Draw this animation at given point
	 * @param g Graphics to paint onto
	 * @param t Time from the start of animation
	 */
	public abstract void paint (Graphics g);
	
	/**
	 * Informs if animation has ended
	 * @return true if animation is done
	 */
	public boolean done() {
		return Constants.TimeScale * (System.currentTimeMillis() - begin) >= duration;
	}
	
	public void setDuration(long d) {
		duration = d;
	}
}
