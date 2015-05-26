package model;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {
	private int fps;
	
	public GameTimer(int fps) {
		super();
		this.fps = fps;
	}
	
	public void scheduleRenderTask(TimerTask task) { 
		 this.scheduleAtFixedRate(task, 0, 1000/fps);
	}
}
