package kwanwoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sungwon.DB.DB;

public class PushMsg {

  private String idto;
  private String idfrom;
  private String msg;
  private String sql;
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public PushMsg(String idto, String msg, String idfrom) {
    this.idto = idto;
    this.msg = msg;
    this.idfrom = idfrom;
    try {
      conn = DB.comCon();
    } catch (Exception e) {
      e.printStackTrace();
    }
    pushMsg();
  }

  private void pushMsg() {
    try {
      sql = "insert into chat values('" + idto + "','" + msg + "','" + idfrom + "')";
      System.out.println(sql);
      pstmt = conn.prepareStatement(sql);
      pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
