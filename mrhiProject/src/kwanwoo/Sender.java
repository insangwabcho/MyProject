
package kwanwoo;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JTextArea;

public class Sender extends Thread implements Serializable {
  Socket socket;
  DataOutputStream out;
  ObjectOutputStream objout;
  OutputStream os;
  String name;//
  private String chat = null;
  JTextArea tArea;

  public Sender(Socket socket, String name, String chat, JTextArea tArea) {
    this.chat = chat;
    this.socket = socket;
    this.tArea = tArea;
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
    //    try {
    //      out.writeUTF(name);
    //    } catch (Exception e) {
    //      System.out.println("예외 : " + e);
    //    }
    while (out != null) {
      try {
        if (chat != null) {
          out.writeUTF(name + " => " + chat);
          chat = null;
        }
        //키보드로부터 입력받은 문자열을 서버로 보냄	
      } catch (Exception e) {
        System.out.println("예외 : " + e);
      }
    }
    tArea.append(chat);
  }
}