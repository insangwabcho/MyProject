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
      conn = sungwon.DB.DB.comCon();
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
      conn = sungwon.DB.DB.comCon();
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
      serials.add(dto.getCpu() + ",cpu c ");
      serials.add(dto.getVga() + ",vga v ");
      serials.add(dto.getRam() + ",ram r ");
      serials.add(dto.getHdd() + ",hdd h ");
      serials.add(dto.getSsd() + ",ssd s ");
      serials.add(dto.getMain() + ",main m ");
      serials.add(dto.getRam2() + ",ram rtwo ");
      String[] t = new String[9];
      for (int i = 0; i < serials.size(); i++) {
        t[i] = serials.get(i).replaceAll("[^0-9]", "");
      }
      String[] item = new String[serials.size()];
      int[] itemSerial = new int[serials.size()];
      int aCount = 0;
      for (int i = 0; i < serials.size(); i++) {
        //if (serials.get(i).replaceAll("[^0-9]", "").equals("0")) {
        if (t[i].equals("0")) {
        }
        else {
          itemSerial[aCount] = Integer.parseInt(serials.get(i).replaceAll("[^0-9]", ""));
          String temp = serials.get(i).replaceAll("[0-9]", "");
          temp = temp.replaceAll(",", "");
          item[aCount] = temp;
          aCount++;
        }
      }

      StringBuilder sql2 = new StringBuilder("from cart,");
      for (int i = 0; i < aCount; i++) {
        if (item[i] != null) {
          sql2.append(item[i]);
        }
        if (i != aCount - 1) {
          sql2.append(",");
        }
      } // where절 끝

      StringBuilder sql1 = new StringBuilder("select ");
      StringBuilder sql3 = new StringBuilder("where ");
      ArrayList<String> kinds = new ArrayList<>();
      for (int i = 0; i < aCount; i++) {
        switch (item[i]) {
        case "cpu c ":
          sql1.append("c.name ,c.price ");
          sql3.append("c.serial= cart.cpu_serial ");
          kinds.add("CPU");
          break;
        case "vga v ":
          sql1.append("v.name ,v.price ");
          sql3.append("v.serial= cart.vga_serial ");
          kinds.add("그래픽카드");
          break;
        case "ram r ":
          sql1.append("r.name ,r.price ");
          sql3.append("r.serial= cart.ram_serial");
          kinds.add("메모리카드");
          break;
        case "hdd h ":
          sql1.append("h.name ,h.price ");
          sql3.append("h.serial= cart.hdd_serial");
          kinds.add("HDD");
          break;
        case "ssd s ":
          sql1.append("s.name ,s.price ");
          sql3.append("s.serial= cart.ssd_serial");
          kinds.add("SSD");
          break;
        case "main m ":
          sql1.append("m.name ,m.price ");
          sql3.append("m.serial= cart.main_serial");
          kinds.add("메인보드");
          break;
        case "ram rtow ":
          sql1.append("rtow.name ,rtow.price ");
          sql3.append("rtow.serial= cart.ram2_serial");
          kinds.add("추가 메모리카드");
          break;
        }
        if (i != aCount - 1)
          sql1.append(",");
      }
      String sql = sql1.toString() + sql2.toString() + sql3.toString();
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);

      rs = pstmt.executeQuery();

      aCount = 0;
      while (rs.next()) {
        Vector row = new Vector<>();
        row.add(kinds.get(aCount));
        row.add(rs.getString("name"));
        row.add(rs.getInt("price"));

        items.add(row);
        aCount++;
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
