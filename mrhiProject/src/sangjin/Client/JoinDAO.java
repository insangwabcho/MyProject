package sangjin.Client;

import java.sql.Connection;import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import sungwon.DB.DB;

public class JoinDAO {
	
	//회원가입정보 데이터베이스에 저장
	public int insertMember(JoinDTO dto){
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn =	sungwon.DB.DB.comCon();
			String sql="insert into member values (?,?,?,?,?,?,?,?)"; //아이디,비번,이메일,이름,생년월일,성별,폰번호,주소
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassward());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getUname());
			pstmt.setDate(5, dto.getBirth());
			pstmt.setString(6, dto.getSex());
			pstmt.setString(7, dto.getTel());
			pstmt.setString(8, dto.getAddress());
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
	
	//회원정보 수정 데이터 베이스에 저장
	public int updateMember(JoinDTO dto){
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn =	sungwon.DB.DB.comCon();
			String sql="update member set password=?, email=?, tel=?, address=?"
					+ " where id=?"; //비밀번호, 이메일, 폰번호, 주소 변경
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPassward());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getId());
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
	
	
	//회원 탈퇴 데이터베이스에서 정보삭제
	public int deleteMember(String Lid){
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn =	sungwon.DB.DB.comCon();
			String sql="delete from member where id=?"; //해당 아이디면 삭제
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Lid);
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
	
	//회원 이름 리턴 메소드
	public String returnName(String Lid){
		Vector items = new Vector();
		String a=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = sungwon.DB.DB.comCon();
			String sql="select name from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Lid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				items.add(rs.getString("name"));
				a=items.get(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null ) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
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
		return a; //이름 리턴
	}
	
	//회원 아이디 리턴 메소드
		public String returnID(String Lid){
			Vector items = new Vector();
			String a = null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn = sungwon.DB.DB.comCon();
				String sql="select id from member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Lid);
				rs = pstmt.executeQuery();
				if(rs.next()){
					items.add(rs.getString("id"));
					a=items.get(0).toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if( rs != null ) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
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
			return a; //아이디 리턴
		}
		
		//주소리턴 메소드
		public String returnAddress(String Lid){
			Vector items = new Vector();
			String a=null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn = sungwon.DB.DB.comCon();
				String sql="select address from member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Lid);
				rs = pstmt.executeQuery();
				if(rs.next()){
					items.add(rs.getString("address"));
					a=items.get(0).toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if( rs != null ) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
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
			return a; //주소 리턴
		}
		
		
		//회원정보수정
		public Vector<String> List(String Lid){
			Vector<String> items = new Vector<String>();
			String a=null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				conn = sungwon.DB.DB.comCon();
				String sql="select id,name,email,birth,tel,address from member where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Lid);
				rs = pstmt.executeQuery();
				if(rs.next()){
					items.add(rs.getString("id"));
					items.add(rs.getString("email"));
					items.add(rs.getString("name"));
					items.add(String.valueOf(rs.getDate("birth")));
					items.add(rs.getString("tel"));
					items.add(rs.getString("address"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if( rs != null ) rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
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
			return items; //주소 리턴
		}
}
