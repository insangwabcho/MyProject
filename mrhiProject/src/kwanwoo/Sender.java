package kwanwoo;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	Socket socket;
	DataOutputStream out;
	String	name;
	
	public Sender(Socket socket, String name){
		this.socket = socket;
		try {
			out = new DataOutputStream(this.socket.getOutputStream());
			this.name = name;
		} catch (Exception e) {
		 System.out.println("���� : "+e);
		}
	}
	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		try {
			out.writeUTF(name);
		} catch (Exception e) {
			System.out.println("���� : "+e);
		}
		while(out != null){
			try {
				out.writeUTF(name+"=>"+s.nextLine());
			//Ű����κ��� �Է¹��� ���ڿ��� ������ ����	
			} catch (Exception e) {
				System.out.println("���� : "+e);
			}
		}
	}
}
