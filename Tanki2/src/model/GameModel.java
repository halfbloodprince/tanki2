package model;

import view.GameView;

public class GameModel {
	private GameView view;
	private GameTimer timer;

	public GameModel(GameView view) {
		this.view = view;
		timer = new GameTimer(10);
		timer.scheduleRenderTask(view.getCanvas().getRenderTask());
	}
}
