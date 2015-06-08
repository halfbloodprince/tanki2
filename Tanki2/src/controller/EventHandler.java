package controller;

import java.util.Map;
import java.util.HashMap;

import controller.event.GenericEvent;

/**
 * It put events to proper handlers
 */
public class EventHandler {
	/// maps event types to their respective handlers
	private Map <Class<? extends GenericEvent>, GenericHandler> handleMap;

	public EventHandler () {
		handleMap = new HashMap <Class<? extends GenericEvent>, GenericHandler> ();
	}
	
	/**
	 * Add event handler for given vlass
	 */
	public void put (Class<? extends GenericEvent> key, GenericHandler value) {
		handleMap.put(key, value);
	}
	
	/**
	 * Handle an event
	 * @param e Event to be handled
	 */
	public void handle (GenericEvent e) {
		if (handleMap.containsKey(e.getClass())) handleMap.get(e.getClass()).handle(e);
	}
}
