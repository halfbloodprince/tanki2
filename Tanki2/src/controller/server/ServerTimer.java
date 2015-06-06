package controller.server;

import java.awt.Canvas;
import java.util.Timer;
import java.util.TimerTask;

import common.Constants;

public class ServerTimer extends Timer {
	public class ServerTimerTask extends TimerTask {
		private EventServer server;
	
		ServerTimerTask (EventServer es) {
			server = es;
		}
		
		public void run() {
			server.send("SYNC_FRAME");
		}
	}
	public ServerTimer (EventServer es) {
		scheduleAtFixedRate(new ServerTimerTask (es), 0, 1000/Constants.SyncedFPS);
	}
}
