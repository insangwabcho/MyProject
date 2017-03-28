package kwanwoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextArea;

import sungwon.DB.DB;

public class PullMsg extends Thread {

  private String sql = "";
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  JTextArea tarea;
  String id;

  public PullMsg(JTextArea tarea, String id) {
    this.tarea = tarea;
    this.id = id;
    try {
      conn = DB.comCon();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void delMsg() {
    try {
      sql = "delete from chat where idfrom=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void pullMsg() {
    try {
      sql = "select * from chat where idfrom=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if (id.equals("root")) {
        while (rs.next()) {
          tarea.append("[ " + rs.getString("idto") + " ] :");
          tarea.append(rs.getString("chat") + "\n");
        }
      }
      else {
        while (rs.next()) {
          tarea.append("[ 관리자 ] : " + rs.getString("chat") + "\n");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        pullMsg();
        delMsg();
        Thread.sleep(1500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
