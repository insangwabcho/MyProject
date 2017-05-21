package config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DB {
	public static Connection dbConn() {
		DataSource ds = null; // javax.sql
		Connection conn = null;
		try {
			 
			Context ctx = new InitialContext();// javax.naming
			ds = (DataSource) ctx.lookup(
"java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;  
	}
}
