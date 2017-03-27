package kwanwoo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException{
		System.out.println("이름을 입력해 주세요.");
		Scanner scan = new Scanner(System.in);
		String sname = scan.nextLine();
		
		try {
			String ServerIP = "localhost";
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("서버와 연결이 되었습니다.");
		//사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 thread
			Thread sender = new Sender(socket,sname);
		//서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 스레드.//
			Thread receiver = new Receiver(socket);
			System.out.println("채팅방에 입장하셨습니다.");
			sender.start();
			receiver.start();
		} catch (Exception e) {
			System.out.println("예외[MultiClient class]:"+e);
		}
	}
}
