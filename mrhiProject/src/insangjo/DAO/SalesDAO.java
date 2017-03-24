package insangjo.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class SalesDAO {
  private String price;

  public void maxPrice() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "";
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
