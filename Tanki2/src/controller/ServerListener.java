package controller;
import java.net.*;
import java.io.*;

import common.Constants;
import controller.event.GenericEvent;
import controller.event.ModelTimerEvent;
import controller.GameController;

public class ServerListener extends Thread {

    private GameController controller;

    public ServerListener (GameController gc) {
        super("ServerListener");
        controller = gc;
    }

    public GenericEvent parse (String msg) {
    	return new ModelTimerEvent ();
    }
    
    public void run() {
    	try (
                Socket socket = new Socket(Constants.serverHostName, Constants.serverPortNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            ) {
                String message;
                while ((message = in.readLine()) != null) {
                	controller.AddEvent(parse(message));
                }
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host " + Constants.serverHostName);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                		Constants.serverHostName);
            }
    }
}