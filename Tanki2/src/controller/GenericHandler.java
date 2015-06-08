package controller;

import controller.event.GenericEvent;

/**
 * Base interface for all event handlers
 */
public interface GenericHandler {
	public void handle (GenericEvent e);
}
