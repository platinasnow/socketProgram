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
				//내용 가져오기
				br = new BufferedReader(new InputStreamReader(ServerClass.socket.getInputStream()));
				//데이터 전송
				pw = new PrintWriter(ServerClass.socket.getOutputStream(),true);
				nickName = br.readLine();
				ServerClass.sendMessage(nickName+"님 접속");
				String Message ="";
				while((Message = br.readLine())!=null ){
					ServerClass.sendMessage("["+nickName+"] :"+Message);
				}
				
			} catch (IOException e) {
				//스레드 해제
				ServerClass.list.remove(this);
				ServerClass.sendMessage("["+nickName+"] 퇴장");
				System.out.println("["+nickName+"] 퇴장");
			}
			
	}
}