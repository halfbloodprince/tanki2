package controller;

import view.DirtMap;
import view.GameView;
import view.ShotAnimation;
import model.GameModel;
import model.Shot;
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
		DirtMap map = new DirtMap(view.getCanvas().getWidth(), view.getCanvas().getHeight());
		model.setGrid(map);
		view.getCanvas().setMap(map);
	}
	
	public void run() {
		startGame();
		
		try {
			Tank tank = model.spawnTank(600);
			view.getCanvas().addSprite(tank);
			
			Shot shot = model.shoot(tank);
			ShotAnimation anim = new ShotAnimation(shot);
			anim.setDuration(model.calcShotDuration(shot));
			view.getCanvas().addAnimation(anim);
		}
	
		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
}
