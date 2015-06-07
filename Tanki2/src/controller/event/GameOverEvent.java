package controller.event;

/**
 * Someone has won the game
 */
public class GameOverEvent extends GenericEvent {
	public int winner;

	public GameOverEvent(int winner) {
		this.winner = winner;
	}
}
