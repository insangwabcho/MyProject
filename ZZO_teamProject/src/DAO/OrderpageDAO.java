package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import common.DB;

public class OrderpageDAO {

  public ArrayList prodList(String company) {
    ArrayList items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    switch (company) {
    case "메인보드":
      company = "main";
      break;
    case "그래픽카드":
      company = "vga";
      break;
    case "메모리카드":
      company = "ram";
      break;
    }

    try {
      String sql = "select distinct company from " + company;
      conn = DB.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {

        items.add(rs.getString("company").toUpperCase());
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

    return items;
  }

  public ArrayList optionList(String company) {
    ArrayList items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "select name, generation, ea, price, img from cpu where company=?";
      conn = DB.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, company.toLowerCase());
      rs = pstmt.executeQuery();

      while (rs.next()) {
        items.add(rs.getString("name"));
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

    return items;
  }

  public Vector cartAdd(ArrayList<String> list, String ea) {
    Vector items = new Vector<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "select name, price from ? where company=? and name=?";
      conn = DB.getConn();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, list.get(0));
      pstmt.setString(2, list.get(1).toLowerCase());
      pstmt.setString(3, list.get(2));
      rs = pstmt.executeQuery();

      while (rs.next()) {
        Vector row = new Vector<>();
        row.add(list.get(0));
        row.add(rs.getString("name"));
        row.add(ea);
        row.add(rs.getInt("price"));
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

    return items;
  }

}
