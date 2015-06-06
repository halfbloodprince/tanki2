package model;

import common.Environment;

public abstract class Shot {
	protected double x;
	protected double y;
	protected double power;
	protected double angle;
	protected double xVel;
	protected double yVel;
	protected double radius;
	protected double basedmg;

	public Shot (int x, int y, double power, double angle) {
		this.x = x;
		this.y = y;

		this.power = power;
		this.angle = angle;

		basedmg = 50;
		
		xVel = Math.cos(angle) * power;
		yVel = Math.sin(angle) * power;
	}

	public int getX() { return (int) x; }
	public int getY() { return (int) y; }

	public abstract void move();
	
	public int getRadius() {
		return (int) radius;
	}
	
	public abstract int dealDmg(Tank tank);
}
