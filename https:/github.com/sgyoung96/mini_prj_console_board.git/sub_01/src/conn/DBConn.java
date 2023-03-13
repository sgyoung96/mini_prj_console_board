package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static DBConn dbconn = new DBConn();
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	
	private DBConn() {}
	
	public static DBConn getInstance() {
		return dbconn;
	}
	
	public Connection conn() {
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			//세션수립
			return DriverManager.getConnection(url, "kosta_admin", "kosta_admin");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
