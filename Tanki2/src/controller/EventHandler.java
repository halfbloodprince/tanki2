package controller;

import java.util.Map;
import java.util.HashMap;

import controller.event.GenericEvent;

public class EventHandler {
	/// maps event types to their respective handlers
	private Map <Class<? extends GenericEvent>, GenericHandler> handleMap;

	public EventHandler () {
		handleMap = new HashMap <Class<? extends GenericEvent>, GenericHandler> ();
	}
	
	public void put (Class<? extends GenericEvent> key, GenericHandler value) {
		handleMap.put(key, value);
	}
	
	public void handle (GenericEvent e) {
		if (handleMap.containsKey(e.getClass())) handleMap.get(e.getClass()).handle(e);
	}
}
