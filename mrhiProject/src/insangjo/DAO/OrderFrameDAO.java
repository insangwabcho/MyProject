package insangjo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import sungwon.DB.DB;

public class OrderFrameDAO {

  public int addDelevery(int n) {
    int result = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;//

    try {
      String sql = "insert into delivery values (" + n + ",'배송대기')";
      conn = sungwon.DB.DB.comCon();
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
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);

      rs = pstmt.executeQuery();

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
    }
    Connection conn = null;
    PreparedStatement pstmt = null;
    int result = 0;

    int[] arr = new int[9];
    arr[0] = getOrderNum();
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

      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
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
      conn = sungwon.DB.DB.comCon();
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

  public void getEA(String order_no) {
    ArrayList<String> serials = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = DB.comCon();
      String sql = "select * from cart where order_no=" + order_no;
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        serials.add("cpu," + rs.getInt("cpu_serial"));
        serials.add("vga," + rs.getInt("vga_serial"));
        serials.add("ram," + rs.getInt("ram_serial"));
        serials.add("hdd," + rs.getInt("hdd_serial"));
        serials.add("ssd," + rs.getInt("ssd_serial"));
        serials.add("main," + rs.getInt("main_serial"));
        serials.add("ram2," + rs.getInt("ram2_serial"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException e2) {
          e2.printStackTrace();
        }
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
    }

    getsEA(serials);

  }

  public void getsEA(ArrayList<String> serials) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<String> serial = serials;
    ArrayList<String> ea = new ArrayList<>();

    try {
      conn = DB.comCon();
      for (int i = 0; i < serial.size(); i++) {
        String[] arr = serial.get(i).split(",");
        if (arr[1].equals("0"))
          continue;

        StringBuilder query = new StringBuilder("select ea from ");

        switch (arr[0]) {
        case "cpu":
          query.append("cpu where serial=" + arr[1]);
          break;
        case "vga":
          query.append("vga where serial=" + arr[1]);
          break;
        case "main":
          query.append("main where serial=" + arr[1]);
          break;
        case "ssd":
          query.append("ssd where serial=" + arr[1]);
          break;
        case "hdd":
          query.append("hdd where serial=" + arr[1]);
          break;
        case "ram":
          query.append("ram where serial=" + arr[1]);
          break;
        case "ram2":
          query.append("ram where serial=" + arr[1]);
          break;
        }

        String sql = query.toString();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();

        if (rs.next()) {
          ea.add(rs.getInt("ea") + "");
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (rs != null)
        try {
          rs.close();
        } catch (SQLException e2) {
          e2.printStackTrace();
        }
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
    }
    IncreaseEA(serial, ea);
  }

  public void IncreaseEA(ArrayList<String> serials, ArrayList<String> eas) {
    ArrayList<String> serial = serials;
    ArrayList<String> ea = eas;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int aCount = 0;
    try {
      conn = DB.comCon();

      for (int i = 0; i < serial.size(); i++) { //arr[0] 종류 arr[1] 시리얼 ea 수량
        String[] arr = serial.get(i).split(",");
        String sql = "";
        StringBuilder query = new StringBuilder("update ");
        if (arr[1].equals("0"))
          continue;

        if (arr[0].equals("ram2"))
          query.append("ram set ea=");
        else
          query.append(arr[0] + " set ea=");

        int a = Integer.parseInt(ea.get(aCount));
        a++;

        query.append(a);
        aCount++;

        query.append(" where serial=" + arr[1]);

        sql = query.toString();

        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //주문한 부품의 수량파악
  public void getEA(ArrayList<String> arr) {
    ArrayList<String> items = arr;
    ArrayList<String> insang = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    int s = arr.size() / 2;
    StringBuilder sb = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    String sangjin = "";
    for (int i = 0; i < s; i++) {
      sangjin += arr.get(i);
      if (i != s - 1)
        sangjin += ", ";//
    }
    for (int i = 0; i < s; i++) {
      switch (arr.get(i)) {
      case "CPU c":
        sb.append("c.ea cpu");
        sb2.append("c.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("cpu");
        break;
      case "MAIN m":
        sb.append("m.ea main");
        sb2.append("m.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("main");
        break;
      case "VGA v":
        sb.append("v.ea vga");
        sb2.append("v.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("vga");
        break;
      case "HDD h":
        sb.append("h.ea hdd");
        sb2.append("h.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("hdd");
        break;
      case "SSD s":
        sb.append("s.ea ssd");
        sb2.append("s.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("ssd");
        break;
      case "RAM r":
        sb.append("r.ea ram");
        sb2.append("r.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("ram");
        break;
      case "RAM r2":
        sb.append("r2.ea rtwo");
        sb2.append("r2.name='" + items.get(i + s) + "'");
        name.add(items.get(i + s));
        insang.add("rtwo");
        break;
      }
      if (i != s - 1) {
        sb.append(", ");
        sb2.append(" and ");
      }
    }
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<String> hash = new ArrayList<String>();
    try {
      conn = DB.comCon();
      String sql = "select " + sb.toString() + " from " + sangjin + " where " + sb2.toString();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        for (int i = 0; i < insang.size(); i++) {
          hash.add(rs.getInt(insang.get(i)) + "");
        }
      }

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
    DecreaseEA(hash, insang, name);
  }

  //주문했을때 수량을 감소시키는 메소드
  public void DecreaseEA(ArrayList<String> hash, ArrayList<String> insang, ArrayList<String> name) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ArrayList<String> hm = hash;
    ArrayList<String> names = name;
    ArrayList<String> item = insang;
    int rr = 0;
    try {
      conn = DB.comCon();
      String sql = "";
      for (int i = 0; i < hm.size(); i++) {
        int t = Integer.parseInt(hm.get(i));
        if (item.get(i).equals("rtwo"))
          sql = "update " + "ram" + " set ea=" + (t - 1) + " where name='" + names.get(i) + "'";
        else
          sql = "update " + item.get(i) + " set ea=" + (t - 1) + " where name='" + names.get(i) + "'";
        pstmt = conn.prepareStatement(sql);
        rr += pstmt.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
