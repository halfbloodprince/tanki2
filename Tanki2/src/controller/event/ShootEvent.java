package controller.event;

/**
 * Event created when tank made a shot
 * @author Severus
 *
 */
public class ShootEvent extends GenericEvent {
	/// tank ID
	public int tankID;

	/// the ID of the weapon to use (not yet available)
	public int weaponID;

	/// the requested angle and power of the shot
	public double angle, power;
	
	public ShootEvent (int tID, int wID, double ang, double pwr) {
		tankID = tID;
		weaponID = wID;
		angle = ang;
		power = pwr;
	}
}
