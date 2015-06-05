package controller.event;

public class ExplosionEvent extends GenericEvent {
	public int x, y;
	public double power;
	
	public ExplosionEvent(int x, int y, double power) {
		this.x = x;
		this.y = y;
		this.power = power;
	}
}
