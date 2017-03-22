package sangjin.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import sangjin.DB.DB;

public class DeliveryChangeDAO {

	public Vector list(){
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.comCon();
			String sql="select c.order_no,c.id,c.buydate,d.status from cart c,delivery d where c.order_no=d.order_no";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Vector row=new Vector();
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
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	
}
