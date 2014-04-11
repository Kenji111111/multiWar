package Game;

import java.net.Socket;

public class NetPlayer {

	Socket mySock;
	String myName;
	int myID;
	
	NetPlayer(Socket socket, String name, int id){
		mySock = socket;
		myName = name;
		myID = id;
	}
}
