package model;

import common.Environment;

public class Shot {
	protected double x;
	protected double y;
	protected double power;
	protected double angle;
	protected double xVel;
	protected double yVel;
	protected double radius;

	public Shot (int x, int y, double power, double angle) {
		this.x = x;
		this.y = y;

		this.power = power;
		this.angle = angle;

		xVel = Math.cos(angle) * power;
		yVel = Math.sin(angle) * power;
	}

	public int getX() { return (int) x; }
	public int getY() { return (int) y; }

	public void move () {
		x += xVel;
		y += yVel;
		yVel += Environment.gravity;
	}
	
	public int getRadius() {
		return (int) radius;
	}
}
