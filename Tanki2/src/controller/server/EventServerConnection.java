package controller.server;
import java.net.*;
import java.io.*;

public class EventServerConnection extends Thread {

    private Socket socket;
    private EventServer server;
    private PrintWriter out;
 
    public EventServerConnection (EventServer es, Socket sock) {
        super("EventServerConnection");
        socket = sock;
        server = es;
        try { out = new PrintWriter(socket.getOutputStream(), true); }
        catch (IOException e) { }
    }

    public void send (String msg) {
    	out.println(msg);
	}

    public void run() {
    	try (
    		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    	){
    		String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	        	// System.out.println("Network read: " + inputLine);
	        	server.send(inputLine);
	        }
    	}
    	catch (IOException e)
    	{
        	System.out.println("lipa");
    	}
    }
}