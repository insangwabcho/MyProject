package sungwon.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

  public static Connection comCon() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.16:1521:orcl", "com", "com1234");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }

}