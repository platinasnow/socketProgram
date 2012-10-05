package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	PrintWriter pw;
	BufferedReader br;
	@Override
	public void run() {
		String nickName = null;
			try {
				//���� ��������
				br = new BufferedReader(new InputStreamReader(ServerClass.socket.getInputStream()));
				//������ ����
				pw = new PrintWriter(ServerClass.socket.getOutputStream(),true);
				nickName = br.readLine();
				ServerClass.sendMessage(nickName+"�� ����");
				String Message ="";
				while((Message = br.readLine())!=null ){
					ServerClass.sendMessage("["+nickName+"] :"+Message);
				}
				
			} catch (IOException e) {
				//������ ����
				ServerClass.list.remove(this);
				ServerClass.sendMessage("["+nickName+"] ����");
				System.out.println("["+nickName+"] ����");
			}
			
	}
}