package controller;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
	private int fps;
	
	/**
	 * Create new game timer
	 * @param fps Repainting frequency in frames per second
	 */
	public GameTimer(int fps) {
		super();
		this.fps = fps;
	}
	
	/**
	 * Add a render task to be performed on each rendering cycle
	 * @param task Task to be added
	 */
	public void scheduleRenderTask(TimerTask task) { 
		 this.scheduleAtFixedRate(task, 0, 1000/fps);
	}
}
