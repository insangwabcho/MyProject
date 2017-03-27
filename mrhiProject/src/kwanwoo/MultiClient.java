package kwanwoo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException{
		System.out.println("�̸��� �Է��� �ּ���.");
		Scanner scan = new Scanner(System.in);
		String sname = scan.nextLine();
		
		try {
			String ServerIP = "localhost";
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("������ ������ �Ǿ����ϴ�.");
		//����ڷκ��� ���� ���ڿ��� ������ �������ִ� ������ �ϴ� thread
			Thread sender = new Sender(socket,sname);
		//�������� ������ �޽����� ������� �ֿܼ� ����ϴ� ������.
			Thread receiver = new Receiver(socket);
			System.out.println("ä�ù濡 �����ϼ̽��ϴ�.");
			sender.start();
			receiver.start();
		} catch (Exception e) {
			System.out.println("����[MultiClient class]:"+e);
		}
	}
}
