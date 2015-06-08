package controller.event;

/**
 * Someone has won the game
 */
public class GameOverEvent extends GenericEvent {
	public int winner;

	/**
	 * Construct this event
	 * @param winner TeamID of team which has won
	 */
	public GameOverEvent(int winner) {
		this.winner = winner;
	}
}
