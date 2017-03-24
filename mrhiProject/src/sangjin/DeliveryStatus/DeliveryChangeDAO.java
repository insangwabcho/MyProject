package sangjin.DeliveryStatus;

import java.sql.Connection;//
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import sungwon.DB.DB;

public class DeliveryChangeDAO {
  //전체 구매리스트
  public Vector list(String status) {
    Vector items = new Vector();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = sungwon.DB.DB.comCon();
      String sql = "select c.order_no,c.id,c.buydate,d.status from cart c,delivery d"
      		+ " where c.order_no=d.order_no and status like ? order by order_no";
      pstmt = conn.prepareStatement(sql);
      if(status.equals("전체배송현황")){
    	  status="%";
      }
      pstmt.setString(1, status);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        Vector row = new Vector();
        row.add(rs.getInt("order_no"));
        row.add(rs.getString("id"));
        row.add(rs.getDate("buydate"));
        row.add(rs.getString("status"));
        items.add(row);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (rs != null)
          rs.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
      try {
        if (pstmt != null)
          pstmt.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
      try {
        if (conn != null)
          conn.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return items;
  }

  //구매 배송 상세내역
  public DeliveryChangeDTO detailCart(int order_no) {
    DeliveryChangeDTO dto = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = sungwon.DB.DB.comCon();
      String sql = "select ct.order_no 주문번호,member.name 이름,member.id 아이디,ct.newadress 주소, c.name CPU, v.name VGA,"
          + " r.name RAM,r2.name RAM2, h.name HDD, s.name SSD, m.name MAIN,ct.total 합계, d.status 배송상태" + " from member ,cart ct,cpu c, vga v, ram r, hdd h, ssd s, main m ,ram r2, delivery d"
          + " where d.order_no=ct.order_no and member.id=ct.id" + " and c.serial(+)=ct.cpu_serial and v.serial(+)=ct.vga_serial" + " and r.serial(+)=ct.ram_serial and h.serial(+)=ct.hdd_serial"
          + " and s.serial(+)=ct.ssd_serial and m.serial(+)=ct.main_serial" + " and r2.serial(+)=ct.ram2_serial and ct.order_no=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, order_no);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        dto = new DeliveryChangeDTO();
        dto.setId(rs.getString("아이디"));
        dto.setName(rs.getString("이름"));
        dto.setAddress(rs.getString("주소"));
        dto.setCpu(rs.getString("CPU"));
        ;
        dto.setVga(rs.getString("VGA"));
        dto.setRam(rs.getString("RAM"));
        dto.setRam2(rs.getString("RAM2"));
        dto.setHdd(rs.getString("HDD"));
        dto.setSsd(rs.getString("SSD"));
        dto.setMain(rs.getString("MAIN"));
        dto.setTotal(rs.getInt("합계"));
        dto.setDvs(rs.getString("배송상태"));
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
    return dto;
  }
  	//배송상태 수정
	public int updateMember(String status, int order_no){
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.comCon();
			String sql="update delivery set status=? where order_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, order_no);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null ) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if( conn != null ) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
