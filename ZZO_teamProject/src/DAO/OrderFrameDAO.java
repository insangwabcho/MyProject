package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DB;

public class OrderFrameDAO {

  public int addOrder(String query1, String query2, int ea, ArrayList<String> list, String userid, int totalPrice) {
    System.out.println("addOrder");
    ArrayList<Integer> items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      String sql = query1 + " from cpu c,hdd h,ram r,ssd s,main m,vga v where" + query2;
      conn = DB.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        for (int i = 0; i < ea; i++) {
          items.add(rs.getInt("serial"));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
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
    int result = updateQuery(items, list, userid, totalPrice);
    return result;
  }

  public int updateQuery(ArrayList<Integer> items, ArrayList<String> list, String userid, int totalPrice) {
    System.out.println("updateQuery");
    Connection conn = null;
    PreparedStatement pstmt = null;
    int result = 0;

    int[] arr = new int[8];
    arr[0] = getOrderNum();
    System.out.println("ordernum지나자마자");
    arr[7] = totalPrice;
    for (int i = 0; i < list.size(); i++) {
      switch (list.get(i)) {
      case "CPU":
        arr[1] = items.get(i);
        break;
      case "메인보드":
        arr[6] = items.get(i);
        break;
      case "그래픽카드":
        arr[2] = items.get(i);
        break;
      case "메모리카드":
        arr[3] = items.get(i);
        break;
      case "HDD":
        arr[4] = items.get(i);
        break;
      case "SSD":
        arr[5] = items.get(i);
        break;
      }
    }

    try {
      //String sql = "insert into cart values(?,?,?,?,?,?,?,?,?)";
      String sql = "insert into cart values (" + arr[0] + ",'" + userid + "'," + arr[1] + "," + arr[2] + "," + arr[3] + "," + arr[4] + "," + arr[5] + "," + arr[6] + "," + totalPrice + ")";
      System.out.println(sql);
      conn = DB.getConn();
      pstmt = conn.prepareStatement(sql);
      //      pstmt.setInt(1, arr[0]);
      //      pstmt.setString(2, userid);
      //      int aCount = 1;
      //      for (int i = 3; i < 9; i++) {
      //        pstmt.setInt(i, arr[aCount]);
      //        aCount++;
      //      }
      //      pstmt.setInt(9, totalPrice);
      System.out.println("마지막쿼리 전");
      result = pstmt.executeUpdate();
      System.out.println("마지막 쿼리 후");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
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

    return result;
  }

  public int getOrderNum() {
    System.out.println("getOrderNum");
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<Integer> items = new ArrayList<>();
    try {
      String sql = "select order_no from cart";
      conn = DB.getConn();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        items.add(rs.getInt("order_no"));
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
    return items.size() + 1;
  }
}
