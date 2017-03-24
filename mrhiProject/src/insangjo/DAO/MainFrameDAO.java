package insangjo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class MainFrameDAO {

  public String getImgPath(String kind, int serial) {
    String result = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      String sql = "select img from " + kind + " where serial=" + serial;
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        result = rs.getString("img");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public String getPwd(String id) {
    String result = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {//
      String sql = "select password from member where id='" + id + "'";
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        result = rs.getString("password");
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
    return result;
  }

  public int deleteMember(String id) {
    int result = 0;
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      String sql = "delete from member where id='" + id + "'";
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      result = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
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
    return result;
  }

  public ArrayList prodList(String company) {
    ArrayList items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String kortoeng;
    switch (company) {
    case "메인보드":
      kortoeng = "main";
      break;
    case "그래픽카드":
      kortoeng = "vga";
      break;
    case "메모리카드":
      kortoeng = "ram";
      break;
    case "추가 RAM":
      kortoeng = "ram";
    default:
      if (company.equals("추가 RAM")) {
        kortoeng = "ram";
      }
      else
        kortoeng = company;

    }
    kortoeng = kortoeng.toUpperCase();
    try {
      String sql = "select distinct company from " + kortoeng;
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String comp = rs.getString("company");
        items.add(comp);
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

  public ArrayList optionList(String company, String tablename) {
    ArrayList items = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String kortoeng;

    switch (company) {
    case "메인보드":
      kortoeng = "main";
      break;
    case "그래픽카드":
      kortoeng = "vga";
      break;
    case "메모리카드":
      kortoeng = "ram";
      break;
    default:
      if (company.equals("추가 RAM"))
        kortoeng = "ram";
      else
        kortoeng = company;
    }

    try {
      String sql = "select '제품번호 : '||serial||', 모델명 : '||name||' , 가격 : '||price||'원 , 재고수량 : '||ea||'ea' detail from " + kortoeng + " where company=?";
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, tablename.toUpperCase());
      rs = pstmt.executeQuery();

      while (rs.next()) {
        items.add(rs.getString("detail"));
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

  public Vector cartAdd(ArrayList<String> list, String ea) {
    Vector items = new Vector<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String one = "";
    switch (list.get(0)) {
    case "메인보드":
      one = "main";
      break;
    case "그래픽카드":
      one = "vga";
      break;
    case "메모리카드":
      one = "ram";
      break;
    default:
      one = list.get(0);
      break;
    }

    if (one.equals("추가 RAM"))
      one = "ram";

    try {

      String company = list.get(1).toUpperCase();

      StringBuilder sqll = new StringBuilder();
      String sql1 = "select name, price from ";
      String sql2 = one + " where company='" + company + "' and serial=" + list.get(2);
      sqll.append(sql1);
      sqll.append(sql2);

      String sql = sqll.toString();
      conn = sungwon.DB.DB.comCon();
      pstmt = conn.prepareStatement(sql);
      // 제조사명을 가져오는데 대문자로 가져오기때문에 소문자로 변경
      //pstmt.setString(1, company);
      //pstmt.setString(2, list.get(2));

      rs = pstmt.executeQuery();

      if (rs.next()) {
        Vector row = new Vector<>();
        row.add(list.get(0));
        row.add(rs.getString("name"));
        row.add(ea);
        row.add(rs.getInt("price") * Integer.parseInt(ea));

        items.add(row);
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
