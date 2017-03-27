package kwanwoo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{
	
	Socket socket;
	DataInputStream in;
	
	//Socket을 매개변수로 받는 생성자.
	public Receiver(Socket socket){
		this.socket = socket;
		
		try {
			in = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			System.out.println("예외 : "+e);
		}
	}//------생성자
	@Override
	public void run() {
	 while(in != null){
		 try {
			System.out.println(in.readUTF());
			//서버로부터 읽어온 데이터를 콘솔에 출력
		} catch (IOException e) {
			System.out.println("예외 : "+e);
		}
	 }		
  }
}
