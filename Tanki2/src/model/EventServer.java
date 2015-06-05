package model;

import java.util.TimerTask;
import java.util.Timer;

// fixme: on timer tick add update to event queue instead
public class EventServer extends Timer {
	public class GameFrameTask extends TimerTask {

		GameModel model;

		GameFrameTask (GameModel m) {
			model = m;
		}
		
		public void run () {
			model.update();
		}
	}
	
	public EventServer (GameModel m) {
		scheduleAtFixedRate(new GameFrameTask (m), 0, 50); // 25 sim frames / s 
	}
}
