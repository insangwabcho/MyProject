package sangjin.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import sungwon.DB.DB;

public class MemberDAO {
	public Vector listMember(){
		Vector list=new Vector();
		Connection con=null;
		PreparedStatement psm=null;
		ResultSet rs=null;
		try {
			con=DB.comCon();
			String sql="select id,name,birth,sex,email,tel,address,log,ip from member";
			psm=con.prepareStatement(sql);
			rs=psm.executeQuery();
			while(rs.next()){
				Vector row=new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getDate("birth"));
				row.add(rs.getString("sex"));
				row.add(rs.getString("email"));
				row.add(rs.getString("tel"));
				row.add(rs.getString("address"));
				row.add(rs.getString("log"));
				row.add(rs.getString("ip"));
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(psm!=null) psm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
