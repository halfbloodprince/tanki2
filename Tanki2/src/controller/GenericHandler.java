package controller;

import controller.event.GenericEvent;

public interface GenericHandler {
	public void handle (GenericEvent e);
}
