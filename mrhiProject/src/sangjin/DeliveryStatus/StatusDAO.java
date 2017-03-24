package sangjin.DeliveryStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import sungwon.DB.DB;


public class StatusDAO {
	public ArrayList<StatusDTO> list(){
		ArrayList<StatusDTO> items=new ArrayList<StatusDTO>();
		Connection con=null;
		PreparedStatement psm=null;
		ResultSet rs=null;
		try {
			con=DB.comCon();
			String sql="select distinct status from delivery";
			psm=con.prepareStatement(sql);
			rs=psm.executeQuery();
			while(rs.next()){
				String status=rs.getString("status");
				StatusDTO dto=new StatusDTO(status);
				items.add(dto);
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
				if(psm!=null) psm.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(con!=null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
}
