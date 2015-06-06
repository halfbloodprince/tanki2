package view;

import java.io.*;
import java.net.*;

import common.Constants;

import controller.GameController;
import controller.event.GenericEvent;
import controller.event.ModelTimerEvent;

public class ServerSender extends Thread {

	private Socket socket;
	private PrintWriter out;

	public void send (String msg)
	{
		out.println(msg);
	}
	
    public ServerSender () {
        super("ServerSender");
        try {
        	socket = new Socket(Constants.serverHostName, Constants.serverPortNumber);
        	out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (UnknownHostException e) {
            System.out.println("view sender: unknown host" + Constants.serverHostName);
        }
        catch (IOException e) {
            System.out.println("cant get IO connection to " + Constants.serverHostName);
        }
    }
}