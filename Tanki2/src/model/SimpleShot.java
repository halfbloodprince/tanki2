package model;

import common.Environment;

public class SimpleShot extends Shot {
	public SimpleShot(int x, int y, double power, double angle) {
		super(x, y, 25*power, angle);
		
		this.radius = 100;
	}
	
	public int dealDmg(Tank tank) {
		double dist = Math.sqrt((tank.getX() - getX()) * (tank.getX() - getX()) +
				(tank.getY() - getY()) * (tank.getY() - getY()));
		
		if (dist >= this.radius)		
			return 0;
		
		int dmg = (int)(basedmg * (1 - dist/radius));
		tank.damage(dmg);
		
		return dmg;
	}
}
