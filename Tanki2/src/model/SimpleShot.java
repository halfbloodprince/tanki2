package model;

import common.Environment;

public class SimpleShot extends Shot{
	protected double xVel;
	protected double yVel;

	public SimpleShot(int x, int y, double power, double angle) {
		super(x, y, power, angle);
		
		xVel = Math.cos(angle) * power;
		yVel = Math.sin(angle) * power;
	}
	
	public double getBulletX(int t) {
		return xVel * t;
	}

	public double getBulletY(int t) {
		return  yVel * t - Environment.gravity * t * t;
	}
}
