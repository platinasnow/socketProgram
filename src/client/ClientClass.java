package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientClass extends JFrame implements ActionListener{

	Socket socket;
	PrintWriter pw;
	BufferedReader br;
	Container pane;
	
	JFrame frame = new JFrame();
	JTextArea textArea = new JTextArea();
	JTextField textField = new JTextField();
	JScrollPane scrollPane = new JScrollPane(textArea);
	
	
	public ClientClass() {
		
		textArea.setEnabled(false);
		this.add(scrollPane,"Center");
		this.add(textField,"South");
		textArea.requestFocus();
		
		setting();
		textField.addActionListener(this);
		
		textArea.setBackground(Color.white);
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		String id = JOptionPane.showInputDialog(pane,"ID를 입력 :");
		pw.println(id);
		
		new MessageReceive().start();
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String message = textField.getText();
		pw.println(message);
		textField.setText("");
	}

	public static void main(String[] args) {
		new ClientClass();
	}
	
	class MessageReceive extends Thread{

		@Override
		public void run() {
			String clientMessage = "";
			
			try {
				while((clientMessage = br.readLine())!=null ){
					textArea.append(clientMessage+"\n");
					textArea.setCaretPosition(textArea.getText().length());
				}
				socket.close();
			} catch (IOException e) {
				System.out.println("받기에 실패했습니다.");
			}
		}
		
		
	}

}
