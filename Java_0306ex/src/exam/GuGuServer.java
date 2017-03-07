package exam;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GuGuServer implements Runnable {
  private ServerSocket serverSocket;
  private Socket socket;
  private DataInputStream dis;
  private DataOutputStream dos;

  public GuGuServer() {
    try {
      serverSocket = new ServerSocket(9999);
      System.out.println("구구단 서비스가 시작되었습니다.");
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      while (true) {
        // 클라이언트의 접속을 기다림 (대기상태)
        // 접속하면 소켓이 생성
        socket = serverSocket.accept();
        InetAddress ip = socket.getInetAddress();
        System.out.println("클라이언트의 ip 주소:" + ip);
        // 스트림 생성
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        // 백그라운드 스레드 생성
        Thread th = new Thread(this);
        th.start();

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    System.out.println("thread name : " + Thread.currentThread().getName());
    int dan = 0;
    try {
      dan = dis.readInt();
      System.out.println(dan);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    new GuGuServer();
  }
}

class ssd extends Thread {

}
