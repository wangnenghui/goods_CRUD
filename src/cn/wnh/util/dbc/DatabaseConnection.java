package cn.wnh.util.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() ;
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver" ;
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:wnh" ;
	private static final String DBUSER = "scott" ;
	private static final String PASSWORD = "tiger" ;
	public static void close() {
		Connection conn = threadLocal.get() ;
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			threadLocal.remove(); 
		}
	}
	/**
	 * 通过ThreadLocal取得一个数据库连接对象
	 * @return
	 */
	public static Connection get() {
		Connection conn = threadLocal.get() ;
		try {
			if (conn == null) {
				conn = rebuildConnection();
				threadLocal.set(conn);
			}
			return conn ;
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null ;
	}
	/**
	 * 进行数据库的连接操作使用
	 * @return
	 */
	private static Connection rebuildConnection() throws Exception {
		Class.forName(DBDRIVER) ;
		return DriverManager.getConnection(DBURL, DBUSER, PASSWORD) ;
	}
}
