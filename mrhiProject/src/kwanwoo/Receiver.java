package kwanwoo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread{
	
	Socket socket;
	DataInputStream in;
	
	//Socket�� �Ű������� �޴� ������.
	public Receiver(Socket socket){
		this.socket = socket;
		
		try {
			in = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			System.out.println("���� : "+e);
		}
	}//------������
	@Override
	public void run() {
	 while(in != null){
		 try {
			System.out.println(in.readUTF());
			//�����κ��� �о�� �����͸� �ֿܼ� ���
		} catch (IOException e) {
			System.out.println("���� : "+e);
		}
	 }		
  }
}
