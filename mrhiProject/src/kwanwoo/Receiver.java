
package kwanwoo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;

public class Receiver extends Thread {

  Socket socket;
  DataInputStream in;
  JTextArea tArea;

  //Socket을 매개변수로 받는 생성자.
  public Receiver(Socket socket, JTextArea tArea) {
    this.socket = socket;
    this.tArea = tArea;
    try {
      in = new DataInputStream(this.socket.getInputStream());
    } catch (Exception e) {
      System.out.println("예외 : " + e);
    }
  }//------생성자

  @Override
  public void run() {
    while (in != null) {
      try {
        tArea.append(in.readUTF() + "\n");
        //System.out.println(in.readUTF());
        //서버로부터 읽어온 데이터를 콘솔에 출력
      } catch (IOException e) {
        System.out.println("예외 : " + e);
      }
    }
  }
}
