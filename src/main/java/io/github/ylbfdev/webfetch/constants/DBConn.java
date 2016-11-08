package io.github.ylbfdev.webfetch.constants;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * 
 * 数据库链接
 * 
 * @author Song Wenbin
 *
 */
public class DBConn {
	private static final Logger Log = Logger.getLogger(DBConn.class.getName());
	public static String CONN_URL;
	public static String USERNAME;
	public static String PASSWORD;

	private DBConn() {

	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			Log.error(e);
		}
		return conn;
	}
}
