package sungwon.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class goodsDAO {
	//삭제후 시리얼번호 정렬 메소드
	public int updateserial(String table,int Aserial, int Bserial){
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = sungwon.DB.DB.comCon();
			String sql = "update "+table+" set serial=? where serial=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Aserial);
			pstmt.setInt(2, Bserial);
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
	
	// 삭제할 물품 출력메소드
	public ArrayList<String> selectDelete(String table, int num) {
		ArrayList<String> items = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = sungwon.DB.DB.comCon();
			String sql = "select *from " + table + " where serial=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				items.add(String.valueOf(rs.getInt("serial")));
				items.add(rs.getString("company"));
				items.add(rs.getString("name"));
				if(table.equals("CPU")){
					items.add(rs.getString("generation"));
				}else if(table.equals("MAIN")){
					items.add(rs.getString("socket"));
				}else if(table.equals("RAM")||table.equals("SSD")||table.equals("HDD")){
					items.add(rs.getString("volume"));
				}else if(table.equals("VGA")){
					items.add(rs.getString("os"));
					items.add(rs.getString("memory"));
				}
				items.add(String.valueOf(rs.getInt("ea")));
				items.add(String.valueOf(rs.getInt("cost")));
				items.add(String.valueOf(rs.getInt("price")));
				items.add(rs.getString("img"));
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

	// 물품 삭제
	public int deleteGoods(String name, int serial) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = sungwon.DB.DB.comCon();
			String sql = "delete from " + name + " where serial=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, serial);
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

	// 물품 추가
	public int insertGoods(String name, int serial, String name2, String company, String spec1, String spec2, int cost,
			int price, int ea, String img) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = sungwon.DB.DB.comCon();
			String sql = "insert into " + name + " values(?,?,?,?,?,?,?,?)";
			if (name.equals("VGA")) {
				sql = "insert into " + name + " values(?,?,?,?,?,?,?,?,?)";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, serial);
			pstmt.setString(2, company);
			pstmt.setString(3, name2);
			pstmt.setString(4, spec1);
			pstmt.setInt(5, ea);
			pstmt.setInt(6, price);
			pstmt.setString(7, img);
			pstmt.setInt(8, cost);
			if (name.equals("CPU")) {
				pstmt.setString(2, company);
				pstmt.setString(3, name2);
				pstmt.setString(4, spec1);
				pstmt.setInt(5, ea);
				pstmt.setInt(6, price);
				pstmt.setString(7, img);
				pstmt.setInt(8, cost);
			} else if (name.equals("VGA")) {
				pstmt.setString(2, spec1);
				pstmt.setString(3, company);
				pstmt.setString(4, name2);
				pstmt.setString(5, spec2);
				pstmt.setInt(6, ea);
				pstmt.setInt(7, price);
				pstmt.setString(8, img);
				pstmt.setInt(9, cost);
			} else if (name.equals("MAIN")) {
				pstmt.setString(2, company);
				pstmt.setString(3, spec1);
				pstmt.setString(4, name2);
				pstmt.setInt(5, ea);
				pstmt.setInt(6, price);
				pstmt.setString(7, img);
				pstmt.setInt(8, cost);
			} else if (name.equals("RAM") || (name.equals("SSD")) || (name.equals("HDD"))) {
				pstmt.setString(2, company);
				pstmt.setString(3, name2);
				pstmt.setString(4, spec1);
				pstmt.setInt(5, ea);
				pstmt.setInt(6, price);
				pstmt.setString(7, img);
				pstmt.setInt(8, cost);
			}
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

	// table2 table3 출력메소드
	public Vector jtable2List(String name) {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = sungwon.DB.DB.comCon();
			String sql = "select serial,company,name,generation,cost from cpu order by serial";
			if (name.equals("VGA")) {
				sql = "select serial,os,company,name,memory,cost from vga order by serial";
			} else if (name.equals("RAM")) {
				sql = "select serial,company,name,volume,cost from ram order by serial";
			} else if (name.equals("SSD")) {
				sql = "select serial,company,name,volume,cost from ssd order by serial";
			} else if (name.equals("HDD")) {
				sql = "select serial,company,name,volume,cost from hdd order by serial";
			} else if (name.equals("MAIN")) {
				sql = "select serial,socket,company,name,cost from main order by serial";
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
				} else if (name.equals("VGA")) {
					row.add(rs.getString("os"));
					row.add(rs.getString("company"));
					row.add(rs.getString("name"));
					row.add(rs.getString("memory"));
				} else if ((name.equals("SSD")) || (name.equals("HDD")) || (name.equals("RAM"))) {
					row.add(rs.getString("company"));
					row.add(rs.getString("name"));
					row.add(rs.getString("volume"));
				} else if (name.equals("MAIN")) {
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
	public int updateEa(String name, int textno, int tmpea, int serial) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = sungwon.DB.DB.comCon();
			// tmpea(현재 재고)+textno(추가할재고)
			int ea = tmpea + textno;
			String sql = "update cpu set ea=? where serial=?";
			if (name.equals("VGA")) {
				sql = "update vga set ea=? where name=?";
			} else if (name.equals("RAM")) {
				sql = "update ram set ea=? where name=?";
			} else if (name.equals("SSD")) {
				sql = "update ssd set ea=? where name=?";
			} else if (name.equals("HDD")) {
				sql = "update hdd set ea=? where name=?";
			} else if (name.equals("MAIN")) {
				sql = "update main set ea=? where name=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ea);
			pstmt.setInt(2, serial);
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
			conn = sungwon.DB.DB.comCon();
			String sql = "select serial,company,name,ea,cost from cpu order by serial";
			if (name.equals("VGA")) {
				sql = "select serial,company,name,ea,cost from vga order by serial";
			} else if (name.equals("RAM")) {
				sql = "select serial,company,name,ea,cost from ram order by serial";
			} else if (name.equals("SSD")) {
				sql = "select serial,company,name,ea,cost from ssd order by serial";
			} else if (name.equals("HDD")) {
				sql = "select serial,company,name,ea,cost from hdd order by serial";
			} else if (name.equals("MAIN")) {
				sql = "select serial,company,name,ea,cost from main order by serial";
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
			conn = sungwon.DB.DB.comCon();
			String sql = "select *from tab where tname=? or tname=? or tname=? or tname=? "
					+ " or tname=? or tname=? order by tname";
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
