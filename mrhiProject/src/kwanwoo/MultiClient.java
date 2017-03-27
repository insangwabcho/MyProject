package kwanwoo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class MultiClient {

  public void chatClient(String userid, String chat, JTextArea tArea, String recive) throws UnknownHostException, IOException {

    String ServerIP = getIp(recive);
    String getchat = "";

    try {
      Socket socket = new Socket("192.168.0.90", 9999);
      if (tArea.getText().equals(""))
        tArea.append("채팅서버에 연결되었습니다!\n");
      //System.out.println("서버와 연결이 되었습니다.");
      //사용자로부터 얻은 문자열을 서버로 전송해주는 역할을 하는 thread
      Thread sender = new Sender(socket, userid, chat);
      //서버에서 보내는 메시지를 사용자의 콘솔에 출력하는 스레드.//
      Thread receiver = new Receiver(socket, tArea);
      //System.out.println("채팅방에 입장하셨습니다.");

      sender.start();
      receiver.start();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getIp(String recive) {
    String Ip = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      String sql = "select ip from member where id='" + recive + "'";
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        Ip = rs.getString("ip");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    return Ip;
  }
}
