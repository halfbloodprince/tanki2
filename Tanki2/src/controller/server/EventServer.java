package controller.server;

import java.net.*;
import java.util.ArrayList;
import java.io.*;
import common.Constants;
 
public class EventServer implements Runnable {

	private ArrayList <EventServerConnection> connections;
	
	public EventServer () {
		connections = new ArrayList <EventServerConnection> ();
		new ServerTimer (this);
	}

	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(Constants.serverPortNumber)) { 
            while (true) {
                System.out.println("Server: adding connection!");
            	EventServerConnection con = new EventServerConnection (this, serverSocket.accept());
                connections.add(con);
                con.start();
                System.out.println("Server: added connection!");
            }
        } catch (IOException e) {
            System.out.println("Server: could not listen on port " + Constants.serverPortNumber);
        }
	}

	public void send (String msg) {
		for (EventServerConnection esc : connections) {
			esc.send (msg);
		}
	}
}