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
				System.out.println("������...");
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+"_����Ǿ����ϴ�.");
				
				ServerThread st = new ServerThread();
				list.add(st);
				st.start();
			}
		}catch(Exception e){
			System.out.println("���� ����");
		}
	}
	//���ӵ� ��� Ŭ���̾�Ʈ���� �޼��� ����
	public static  void sendMessage(String message){
		for(ServerThread st : list){
			st.pw.println(message);
		}
	}
	public static void main (String[] args){
		new ServerClass();
	}
}
