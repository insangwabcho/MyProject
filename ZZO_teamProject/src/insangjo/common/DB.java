package insangjo.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {

  public static Connection getConn() {
    Connection conn = null;

    try {
      FileInputStream fis = new FileInputStream("oracle.prop");
      Properties prop = new Properties();
      prop.load(fis);
      String url = prop.getProperty("url");
      String id = prop.getProperty("id");
      String password = prop.getProperty("password");

      Class.forName("oracle.jdbc.OracleDriver");

      conn = DriverManager.getConnection(url, id, password);
    } catch (Exception e) {
      e.printStackTrace();
    } // try,catch

    return conn;
  } // dbConn();

}
