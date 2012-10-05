package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClass {

	public static Socket socket;
	public static ServerSocket serverSocket;
	public static ArrayList<ServerThread> list = new ArrayList<ServerThread>();
	
	ServerClass(){
		try{
			serverSocket = new ServerSocket(9090);
			while(true){
				System.out.println("연결중...");
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+"_연결되었습니다.");
				
				ServerThread st = new ServerThread();
				list.add(st);
				st.start();
			}
		}catch(Exception e){
			System.out.println("접속 에러");
		}
	}
	//접속된 모든 클라이언트에게 메세지 전송
	public static  void sendMessage(String message){
		for(ServerThread st : list){
			st.pw.println(message);
		}
	}
	public static void main (String[] args){
		new ServerClass();
	}
}
