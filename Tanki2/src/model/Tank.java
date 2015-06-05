package model;

public class Tank {

	/// used to make sure assigned tank IDs are unique
	static private int currentTankID = 0;

	/// used to tell tanks apart from one another
	private int tankID;
	
	/// position co-ordinates
	private int x, y;

	public Tank() {
		tankID = currentTankID++;
		System.out.format("created tank with id %d", tankID);
	}

	/**
	 * @return the unique ID of the tank
	 */
	public int getID () { return tankID; }

	/**
	 * @return X co-ordinate
	 */
	public int getX () { return x; }
	/**
	 * @return Y co-ordinate
	 */
	public int getY () { return y; }

	/** sets XY position
	 * @param x x
	 * @param y y
	 */
	public void setPosition (int xx, int yy) { x = xx; y = yy; }

	/**
	 * Make a single shot with current weapon
	 */
	public void shoot () {
		// todo: spawn bullet
	}
}
