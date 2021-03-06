import controller.GameController;
import model.GameModel;
import view.GameView;


public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting the game...");

		try {
			final GameView view = new GameView();
			final GameModel model = new GameModel();
			final GameController controller = new GameController(model, view);
			final Thread controllerThread = new Thread(controller);
			
			controllerThread.start();
		}
		
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

}