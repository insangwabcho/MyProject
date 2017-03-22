package insangjo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import insangjo.DTO.CartDTO;

public class SearchFrameDAO {

  public int checkOrder(String userid) {
    int result = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "select order_no from cart where id='" + userid + "'";
      conn = sangjin.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        result++;
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

    return result;
  }

  public ArrayList<CartDTO> dateOrder(String userid) {
    ArrayList<CartDTO> items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "select * from cart where id='" + userid + "'";
      conn = sangjin.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        CartDTO dto2 = new CartDTO();
        dto2.setOrder_no(rs.getInt("order_no"));
        dto2.setId(rs.getString("id"));
        dto2.setCpu(rs.getInt("cpu_serial"));
        dto2.setVga(rs.getInt("vga_serial"));
        dto2.setRam(rs.getInt("ram_serial"));
        dto2.setHdd(rs.getInt("hdd_serial"));
        dto2.setSsd(rs.getInt("ssd_serial"));
        dto2.setMain(rs.getInt("main_serial"));
        dto2.setTotal(rs.getInt("total"));
        dto2.setBuydate(rs.getDate("buydate"));
        dto2.setAddress(rs.getString("newadress"));
        dto2.setRam2(rs.getInt("ram2_serial"));
        items.add(dto2);
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

    return items;
  }

  public Vector detailOrder(CartDTO dto) {
    Vector items = new Vector<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      ArrayList<String> serials = new ArrayList<>();
      serials.add(dto.getCpu() + ",cpu");
      serials.add(dto.getVga() + ",vga");
      serials.add(dto.getRam() + ",ram");
      serials.add(dto.getHdd() + ",hdd");
      serials.add(dto.getSsd() + ",ssd");
      serials.add(dto.getMain() + ",main");
      serials.add(dto.getRam2() + ",ramtwo");

      String[] item = new String[serials.size()];
      int[] itemSerial = new int[serials.size()];
      int aCount = 0;
      for (int i = 0; i < serials.size(); i++) {
        if (serials.get(i).replaceAll("[^0-9]", "").equals("0")) {
          serials.remove(i);
        }
        else {
          item[aCount] = serials.get(i).replaceAll("[^0-9]", "");
          String t = serials.get(i).replaceAll("[^a-z]", "");
          t = t.replaceAll(",", "");
          itemSerial[i] = Integer.parseInt(t);
        }
        aCount++;
      }
      StringBuilder sql = new StringBuilder("select ");
      for (int i = 0; i < item.length; i++) {
        sql.append(item[i]);
        if (i != item.length - 1) {
          sql.append(",");
        }
      }
      System.out.println(sql);

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
