package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class STTTClient {

	int PORT  = 8090;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	//The constructor connects to the server, lays out the board, and registers the GUI listeners.
	public STTTClient(String serverAddress) throws Exception{
		//Setting up networking - 
		socket = new Socket(serverAddress, PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in,stdIn;
		PrintWriter out;
		String fromServer, fromClient;
		
		if(args.length!=2){
			System.err.println("Usage not proper");
			System.exit(1);
		}
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		while(true){
			Socket ss = new Socket(host, port);
			System.out.println("Conencted to server.");
			
			//Get BufferedReader on input stream
			in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			
			//Get Printwriter on output stream-
			out = new PrintWriter(ss.getOutputStream(), true);
			
			//Get a BufferedReader to read user input-
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			while((fromServer = in.readLine())!=null){
				System.out.println("Server says: "+ fromServer);
				fromClient = stdIn.readLine();
				if(fromClient!=null){
					System.out.println("Client says:" +fromClient);
					out.print(fromClient);
				}
			}
		}
}
	
}
