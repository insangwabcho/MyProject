package insangjo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    }

    return result;
  }

}
