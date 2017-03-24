package sungwon.DB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

  public static Connection comCon() {
    Connection conn = null;
    try {
      String a = (String.valueOf(DB.class.getResource("comnawa.prop"))).replaceAll("file:", "");
      FileInputStream fis = new FileInputStream(String.valueOf(a));
      Properties prop = new Properties();
      prop.load(fis);
      String url = prop.getProperty("url");
      String id = prop.getProperty("id");
      String password = prop.getProperty("password");
      try {
        conn = DriverManager.getConnection(url, id, password);
      } catch (SQLException e) {
        try {
          conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", id, password);
        } catch (SQLException e1) {
          conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", id, password);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }
}