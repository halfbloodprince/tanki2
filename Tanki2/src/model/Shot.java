package model;

import common.Environment;

/**
 * Base class for all shots
 * @author Severus
 *
 */
public abstract class Shot {
	/// X position of the projectile
	protected double x;
	/// Y position of the projectile
	protected double y;
	/// Power of this shot
	protected double power;
	/// Angle of shot
	protected double angle;
	/// Current x velocity
	protected double xVel;
	//// Current y velocity
	protected double yVel;
	/// Blast radius
	protected double radius;
	/// Base damage
	protected double basedmg;

	/**
	 * Create a shot
	 * @param x Starting x position
	 * @param y Starting y position
	 * @param power Power of the shot
	 * @param angle Angle of the shot
	 */
	public Shot (int x, int y, double power, double angle) {
		this.x = x;
		this.y = y;

		this.power = power;
		this.angle = angle;

		basedmg = 50;
		
		xVel = Math.cos(angle) * power;
		yVel = Math.sin(angle) * power;
	}

	/**
	 * @return Current X position of the projectile
	 */
	public int getX() { return (int) x; }
	
	/**
	 * @return Current Y position of the projectile
	 */
	public int getY() { return (int) y; }

	/**
	 * Calculate next step of projectile trajectory
	 */
	public abstract void move();
	
	/**
	 * Get blast radius
	 * @return radius
	 */
	public int getRadius() {
		return (int) radius;
	}
	
	/**
	 * Deal damage to given tank
	 * @param tank Tank to which we want to deal damage
	 * @return amount of damage dealt
	 */
	public abstract int dealDmg(Tank tank);
}
