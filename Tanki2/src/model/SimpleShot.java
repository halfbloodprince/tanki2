package model;

import common.Environment;

/**
 * Shot with no special effects. Air has natural effects (wind, resistance), gravity applies normally.
 * @author Severus
 *
 */
public class SimpleShot extends Shot {
	double airResistance;

	/**
	 * Create this shot
	 * @param x
	 * @param y
	 * @param power
	 * @param angle
	 */
	public SimpleShot(int x, int y, double power, double angle) {
		super(x, y, 25*power, angle);
		
		this.radius = 100;
		this.airResistance = 0.2;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Shot#dealDmg(model.Tank)
	 */
	public int dealDmg(Tank tank) {
		double dist = Math.sqrt((tank.getX() - getX()) * (tank.getX() - getX()) +
				(tank.getY() - getY()) * (tank.getY() - getY()));
		
		if (dist >= this.radius)		
			return 0;
		
		int dmg = (int)(basedmg * (1 - dist/radius));
		tank.damage(dmg);
		
		return dmg;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Shot#move()
	 */
	public void move () {
		x += xVel;
		y += yVel;
		yVel += Environment.gravity;
		xVel -= (xVel + Environment.wind) * Environment.airFriction * airResistance;
		yVel -= yVel * Environment.airFriction * airResistance;
	}
}
