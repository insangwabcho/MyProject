package insangjo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class OrderFrameDAO {

  public int addDelevery(int n) {
    int result = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      String sql = "insert into delivery values (" + n + ",'배송대기')";
      conn = sangjin.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);

      result = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (pstmt != null)
        try {
          pstmt.close();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      if (conn != null)
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    } // try , catch , finally

    return result;
  }

  public int addOrder(String query1, String query2, int ea, ArrayList<String> list, String userid, int totalPrice, String address) {
    ArrayList<Integer> items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      String sql = query1 + " from cpu c,hdd h,ram r,ssd s,main m,vga v,ram rtwo where" + query2;
      conn = sangjin.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);

      System.out.println(sql);
      rs = pstmt.executeQuery();

      System.out.println("listsize" + list.size());
      if (rs.next()) {
        for (int i = 0; i < list.size(); i++)
          items.add(rs.getInt(list.get(i)));
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
    int result = updateQuery(items, list, userid, totalPrice, address);
    return result;
  }

  public int updateQuery(ArrayList<Integer> itemss, ArrayList<String> list, String userid, int totalPrice, String address) {
    ArrayList<Integer> items = itemss;
    for (int i = 0; i < items.size(); i++) {
      System.out.println("ddd" + items.get(i));
    }
    Connection conn = null;
    PreparedStatement pstmt = null;
    int result = 0;

    System.out.println(address);
    int[] arr = new int[9];
    arr[0] = getOrderNum();
    arr[7] = totalPrice;
    System.out.println("------");
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
    System.out.println("------");
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
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
      case "추가RAM":
        arr[8] = items.get(i);
      }
    }

    Calendar cal = Calendar.getInstance();
    int year = cal.get(cal.YEAR);
    int month = cal.get(cal.MONTH) + 1;
    int date = cal.get(cal.DATE);

    Date arr8 = Date.valueOf(year + "-" + month + "-" + date);
    String arr10 = address;

    try {
      //String sql = "insert into cart values(?,?,?,?,?,?,?,?,?)";
      String sql = "insert into cart values (" + arr[0] + ",'" + userid + "'," + arr[1] + "," + arr[2] + "," + arr[3] + "," + arr[4] + "," + arr[5] + "," + arr[6] + "," + arr[7] + ",'" + arr8 + "','"
          + arr10 + "'," + arr[8] + ")";
      System.out.println(sql);
      conn = sangjin.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);

      //      pstmt.setInt(1, arr[0]);
      //      pstmt.setString(2, userid);
      //      int aCount = 1;
      //      for (int i = 3; i < 9; i++) {
      //        pstmt.setInt(i, arr[aCount]);
      //        aCount++;
      //      }
      //      pstmt.setInt(9, totalPrice);
      result = pstmt.executeUpdate();
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
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<Integer> items = new ArrayList<>();
    try {
      String sql = "select order_no from cart";
      conn = sangjin.DB.DB.comCon();
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
