package controller;

import view.GameView;
import model.GameModel;
import model.Tank;

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
		
		try {
			Tank tank = model.spawnTank(100, 100);
			view.getCanvas().addSprite(tank);
		}
	
		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
