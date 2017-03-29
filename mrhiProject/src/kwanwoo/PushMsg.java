package kwanwoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import insangjo.adminfame.rootFrame;
import sungwon.DB.DB;

public class PushMsg {

  private String idto;
  private String idfrom;
  private String msg;
  private String sql;
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;
  private boolean gongji;
  private rootFrame rf;

  public PushMsg() {
  }

  public PushMsg(String idto, String msg, String idfrom, rootFrame rf) {
    this(idto, msg, idfrom);
    this.rf = rf;
  }

  public PushMsg(String idto, String msg, String idfrom) {//
    this.idto = idto;
    this.msg = msg;
    this.idfrom = idfrom;
    try {
      conn = DB.comCon();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    checkGongji();
    if (idfrom.equals("all_members")) {
      if (gongji) {
        JOptionPane.showMessageDialog(rf, "이미 공지사항이 있습니다");
        return;
      }
      else {
        allmembersMsg();
      }
    }
    else {
      pushMsg();
    }
  }

  private void pushMsg() {
    try {
      sql = "insert into chat values('" + idto + "','" + msg + "','" + idfrom + "')";
      pstmt = conn.prepareStatement(sql);
      pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
  }

  private void allmembersMsg() {
    try {
      sql = "insert into chat values('" + idto + "','" + msg + "','" + "all_members" + "')";
      pstmt = conn.prepareStatement(sql);
      pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
  }

  public int delGongji() {
    int result = 0;
    try {
      sql = "delete from chat where idfrom='all_members'";
      conn = DB.comCon();
      pstmt = conn.prepareStatement(sql);
      result = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {//
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return result;
  }

  private void checkGongji() {
    try {
      sql = "select * from chat where idfrom='all_members'";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        gongji = true;
      }
      else
        gongji = false;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }
  }
}