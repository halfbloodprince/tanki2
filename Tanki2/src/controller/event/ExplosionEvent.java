package controller.event;

import model.Shot;

public class ExplosionEvent extends GenericEvent {
	public Shot shot;
	
	public ExplosionEvent(Shot shot) {
		this.shot = shot;
	}
}
