package sungwon.goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class goodsDAO {

  public Vector jtable2List(String name) {
    Vector items = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = DB.DB.comCon();
      String sql = "select serial,company,name,generation,cost from cpu";
      if (name.equals("VGA")) {
        sql = "select serial,os,company,name,memory,cost from vga";
      }
      else if (name.equals("RAM")) {
        sql = "select serial,company,name,volume,cost from ram";
      }
      else if (name.equals("SSD")) {
        sql = "select serial,company,name,volume,cost from ssd";
      }
      else if (name.equals("HDD")) {
        sql = "select serial,company,name,volume,cost from hdd";
      }
      else if (name.equals("MAIN")) {
        sql = "select serial,socket,company,name,cost from main";
      }
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        Vector row = new Vector();
        row.add(rs.getInt("serial"));
        if (name.equals("CPU")) {
          row.add(rs.getString("company"));
          row.add(rs.getString("name"));
          row.add(rs.getString("generation"));
        }
        else if (name.equals("VGA")) {
          row.add(rs.getString("os"));
          row.add(rs.getString("company"));
          row.add(rs.getString("name"));
          row.add(rs.getString("memory"));
        }
        else if ((name.equals("SSD")) || (name.equals("HDD")) || (name.equals("RAM"))) {
          row.add(rs.getString("company"));
          row.add(rs.getString("name"));
          row.add(rs.getString("volume"));
        }
        else if (name.equals("MAIN")) {
          row.add(rs.getString("socket"));
          row.add(rs.getString("company"));
          row.add(rs.getString("name"));
        }
        row.add(rs.getInt("cost"));
        items.add(row);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null)
          rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return items;
  }

  //// table 1출력
  // 입력한재고 업데이트
  public int updateEa(String name, int textno, int tmpea, String item) {
    int result = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
      conn = DB.DB.comCon();
      // tmpea(현재 재고)+textno(추가할재고)
      int ea = tmpea + textno;
      String sql = "update cpu set ea=? where name=?";
      if (name.equals("VGA")) {
        sql = "update vga set ea=? where name=?";
      }
      else if (name.equals("RAM")) {
        sql = "update ram set ea=? where name=?";
      }
      else if (name.equals("SSD")) {
        sql = "update ssd set ea=? where name=?";
      }
      else if (name.equals("HDD")) {
        sql = "update hdd set ea=? where name=?";
      }
      else if (name.equals("MAIN")) {
        sql = "update main set ea=? where name=?";
      }
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, ea);
      pstmt.setString(2, item);
      result = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  // JTABLE에 선택한table 재고출력
  public Vector jtableList(String name) {
    Vector items = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = DB.DB.comCon();
      String sql = "select serial,company,name,ea,cost from cpu";
      if (name.equals("VGA")) {
        sql = "select serial,company,name,ea,cost from vga";
      }
      else if (name.equals("RAM")) {
        sql = "select serial,company,name,ea,cost from ram";
      }
      else if (name.equals("SSD")) {
        sql = "select serial,company,name,ea,cost from ssd";
      }
      else if (name.equals("HDD")) {
        sql = "select serial,company,name,ea,cost from hdd";
      }
      else if (name.equals("MAIN")) {
        sql = "select serial,company,name,ea,cost from main";
      }
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        Vector row = new Vector();
        row.add(rs.getInt("serial"));
        row.add(rs.getString("company"));
        row.add(rs.getString("name"));
        row.add(rs.getInt("ea"));
        row.add(rs.getInt("cost"));
        items.add(row);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null)
          rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return items;
  }

  // 리스트에 TABLE 출력 메소드
  public ArrayList<String> jlistList() {
    ArrayList<String> items = new ArrayList<String>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = DB.DB.comCon();
      String sql = "select *from tab where tname=? or tname=? or tname=? or tname=? " + " or tname=? or tname=? order by tname";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, "CPU");
      pstmt.setString(2, "VGA");
      pstmt.setString(3, "RAM");
      pstmt.setString(4, "HDD");
      pstmt.setString(5, "SSD");
      pstmt.setString(6, "MAIN");
      rs = pstmt.executeQuery();
      while (rs.next()) {
        items.add(rs.getString("tname"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null)
          rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null)
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return items;
  }

}
