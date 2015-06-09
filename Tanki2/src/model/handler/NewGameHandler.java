package model.handler;
import view.DirtMap;
import view.TankSprite;
import model.GameModel;
import controller.GenericHandler;
import controller.event.GenericEvent;

public class NewGameHandler implements GenericHandler {
	private GameModel model;

	/// @param target model to inflict changes to
	public NewGameHandler (GameModel m) {
		model = m;
	}

	public void handle (GenericEvent e) {
		model.startGame();
	}
}

