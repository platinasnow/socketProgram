package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CopyOfClientClass {

	Socket socket;
	PrintWriter pw;
	BufferedReader br;
	
	public CopyOfClientClass() {
		setting();
		System.out.println("ID를 입력 :");
		Scanner scan = new Scanner(System.in);
		String id = scan.next();
		pw.println(id);
		
		while(true){
			String message = scan.next();
			pw.println(message);
		}
	}
	
	public void setting(){
		try{
			socket = new Socket("127.0.0.1",9090);
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			br = new BufferedReader(inputStreamReader);
			pw = new PrintWriter(socket.getOutputStream(),true);
		}catch (Exception e) {
			System.out.println("소켓 에러");
		}
	}
	public static void main(String[] args) {
		new CopyOfClientClass();
	}
	
	class MessageReceive extends Thread{

		@Override
		public void run() {
			String clientMessage = "";
			try {
				while((clientMessage = br.readLine())!=null ){
					System.out.println(clientMessage+"\n");
				}
				socket.close();
			} catch (IOException e) {
				System.out.println("받기에 실패했습니다.");
			}
		}
	}
}
