package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class STTTServerThread extends Thread{
	Socket sttt;
	BufferedReader in;
	PrintWriter out;
	
	public STTTServerThread(Socket sttt){
		this.sttt = sttt;
	}
	

	public void run() {
		try{
			System.out.println("Getting a Buffered Reader on input stream");
			in = new BufferedReader(new InputStreamReader(this.sttt.getInputStream()));
			System.out.println("Getting a Printwriter on output stream");
			out = new PrintWriter(this.sttt.getOutputStream(),true);
			
			String clientInput;
			out.println("Connected");
			System.out.println("Waiting for client input");
			clientInput = in.readLine();
			System.out.println("Received client input");
			while(clientInput!=null){
				System.out.println("Client says:" +clientInput);
				out.println(clientInput);
				clientInput = in.readLine();
			}
			
			
		}
		catch (IOException ioe){
			System.out.println("Caught IOException: "+ioe);
		}
	}
}
