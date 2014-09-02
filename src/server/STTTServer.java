package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class STTTServer {
	private void start(int port) {
		boolean listening = true;
		// Get a server Socket - 
		try {
			System.out.println("Conencting to port- "+ port);
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Server started on "+ ss.getInetAddress()+":"+port);
			while(listening){
				Socket STTTClient = ss.accept();
				(new STTTServerThread(STTTClient)).start();
			}
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
