package controller;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import common.Constants;
import controller.event.*;
import controller.GameController;

public class ServerListener extends Thread {

	private Socket socket;
    private GameController controller;
    private PrintWriter out;
    private BufferedReader in;

    public ServerListener (GameController gc) {
        super("ServerListener");
        controller = gc;
        try {
        	socket = new Socket(Constants.serverHostName, Constants.serverPortNumber);
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + Constants.serverHostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
            		Constants.serverHostName);
        }
        try {
	        out = new PrintWriter(socket.getOutputStream(), true);
	        in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e) {
            System.out.println("IN/OUT failed");
        }
    }

    public void send (String msg)
    {
    	out.println(msg);
    }
    
    public GenericEvent parse (String msg) {
    	Scanner scanner = new Scanner (msg);
    	scanner.useLocale(Locale.ENGLISH);
    	switch(scanner.next())
    	{
    		case "SYNC_FRAME": return new ModelTimerEvent ();
    		case "SHOT": {
    			int a = scanner.nextInt();
    			double b = scanner.nextDouble();
    			double c = scanner.nextDouble();
    			return new ShootEvent(0, a, b, c);
    		}
    		default: return null;
    	}
    }

    public void run() {
    	String message;
        try {
        	while ((message = in.readLine()) != null) {
	        	controller.AddEvent(parse(message));
	        }
        }
        catch (IOException e) {}
    }
}