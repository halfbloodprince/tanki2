package view;

import common.Constants;

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
		return Constants.TimeScale * (System.currentTimeMillis() - begin) >= duration;
	}
	
	public void setDuration(long d) {
		duration = d;
	}
}
