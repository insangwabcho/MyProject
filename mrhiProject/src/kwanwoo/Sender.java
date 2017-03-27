
package kwanwoo;

import java.io.DataOutputStream;
import java.net.Socket;

public class Sender extends Thread {
  Socket socket;
  DataOutputStream out;
  String name;//
  private String chat = null;

  public Sender(Socket socket, String name) {
    this.socket = socket;
    try {
      out = new DataOutputStream(this.socket.getOutputStream());
      this.name = name;
    } catch (Exception e) {
      System.out.println("예외 : " + e);
    }
  }

  @Override
  public void run() {
    //	  Scanner s = new Scanner(System.in);
    try {
      out.writeUTF(name);
    } catch (Exception e) {
      System.out.println("예외 : " + e);
    }
    while (out != null) {
      try {
        if (chat != null) {
          out.writeUTF(name + "=>" + chat);
          chat = null;
        }
        //키보드로부터 입력받은 문자열을 서버로 보냄	
      } catch (Exception e) {
        System.out.println("예외 : " + e);
      }
    }
  }

  public void sendChat(String chat) {
    this.chat = chat;
  }
}