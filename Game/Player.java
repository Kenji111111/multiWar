package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Player {
	
	String serverAddr = "127.0.0.1";
	int port = 8888;
	
	int myID = -1;
	
	public static void main(String[] args){
		try {
			new Player("Kenj");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Player(String name) throws Exception {
		
		Socket socky = new Socket(serverAddr, port);

        BufferedReader input = new BufferedReader(new InputStreamReader(socky.getInputStream()));
		PrintWriter output = new PrintWriter(socky.getOutputStream(), true);
		
		String answer = input.readLine();
		myID = Integer.parseInt(answer);
		
		output.println(name + "," + myID);
		
        
	}
}
