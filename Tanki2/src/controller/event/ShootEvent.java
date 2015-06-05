package controller.event;

public class ShootEvent extends GenericEvent {
	/// the ID of the weapon to use (not yet available)
	public int weaponID;

	/// the requested angle and power of the shot
	public double angle, power;
	
	public ShootEvent (int wID, double ang, double pwr) {
		weaponID = wID;
		angle = ang;
		power = pwr;
	}
}
