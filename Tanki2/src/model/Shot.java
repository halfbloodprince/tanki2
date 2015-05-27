package model;

public abstract class Shot {
	protected double startX;
	protected double startY;
	protected double power;
	protected double angle;

	public Shot(int x, int y, double power, double angle) {
		this.startX = x;
		this.startY = y;
		this.power = power;
		this.angle = angle;
	}

	public abstract double getBulletX(int t);
	public abstract double getBulletY(int t);
}
