package model;

public class SimpleShot extends Shot {

	public SimpleShot(int x, int y, double power, double angle) {
		super(x, y, 25*power, angle);
	}
}
