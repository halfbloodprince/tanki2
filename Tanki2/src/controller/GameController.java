package controller;

import view.GameView;
import model.GameModel;

public class GameController implements Runnable {
	GameModel model;
	GameView view;
	
	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		
	}
	
	/**
	 * Start the game
	 */
	private void startGame() {
		view.enableCanvas();
	}
	
	public void run() {
		startGame();
	}
}
