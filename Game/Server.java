package Game;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.Date;;

public class Server {
	
	int port = 8888;
	int numPlayers = 2;
	int currentID = 0;
	
	JTextArea serverLog;
	
	public static void main(String[] args){
		try {
			new Server();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Server() throws IOException{
		
		ServerSocket sockyServer = new ServerSocket(port);
		
		Date today = new Date(System.currentTimeMillis());
		
		JFrame j = new JFrame("Server - Port " + sockyServer.getLocalPort());
		
		serverLog = new JTextArea(">Game Server - " + today.toString() + "\n>", 20, 60);
		serverLog.setCaretPosition(serverLog.getText().length());
		serverLog.setLineWrap(true);
		JScrollPane jspane = new JScrollPane(serverLog);
		jspane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		j.add(jspane);
		j.setPreferredSize(serverLog.getPreferredSize());
		j.pack();
		
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			while (true) {
				Socket socky = sockyServer.accept();
				log("Connection from: " + socky.getInetAddress().toString().substring(1) + "  " + socky.toString());
				
				try {
					PrintWriter out = new PrintWriter(socky.getOutputStream(), true); 
					out.println(currentID);
					currentID++;
					
			        BufferedReader input = new BufferedReader(new InputStreamReader(socky.getInputStream()));
			        String info = null;
			        while(info == null){
			        	info = input.readLine();
			        }
			        String playerName = info.substring(0, info.indexOf(','));
			        int id = Integer.parseInt(info.substring(info.indexOf(',') + 1));
			        log("Shook hands with " + playerName + ", ID " + id);
			        
			        
					
				} finally {
					socky.close();
				}

			}
		} finally {
			sockyServer.close();
		}
	}
	
	private void log(String text){
		serverLog.append(text + "\n>");
		serverLog.setCaretPosition(serverLog.getText().length());
	}
}
