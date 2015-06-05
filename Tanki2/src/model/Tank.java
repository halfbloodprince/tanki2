package model;

import java.util.ArrayList;

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
	 * Make a shot with current weapon
	 */
	public ArrayList <Shot> shoot (int weaponID, double power, double angle) {
		ArrayList <Shot> ret = new ArrayList <Shot> ();
		if (weaponID == 0) { // fixme make into real customised weapons
			ret.add (new SimpleShot (x, y, power, angle));
		} else {
			ret.add (new SimpleShot (x, y, power, angle - 0.2));
			ret.add (new SimpleShot (x, y, power * 1.1, angle));
			ret.add (new SimpleShot (x, y, power, angle + 0.2));
		}
		return ret;	
	}
}
