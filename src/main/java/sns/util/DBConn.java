package sns.util;

import java.sql.*;

public class DBConn {
    private static final String HOST = getEnvOrDefault("MYSQLHOST", "localhost");
    private static final String PORT = getEnvOrDefault("MYSQLPORT", "3306");
    private static final String DB   = getEnvOrDefault("MYSQLDATABASE", "sns");
    private static final String USER = getEnvOrDefault("MYSQLUSER", "root");
    private static final String PASSWORD = getEnvOrDefault("MYSQLPASSWORD", "ezen");

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB
                                        + "?useSSL=false&serverTimezone=UTC";

    public static Connection conn() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(ResultSet rs, PreparedStatement psmt, Connection conn) throws Exception {
        if(rs != null) rs.close();
        if(psmt != null) psmt.close();
        if(conn != null) conn.close();
    }

    public static void close(PreparedStatement psmt, Connection conn) throws Exception {
        if(psmt != null) psmt.close();
        if(conn != null) conn.close();
    }

    // 환경변수가 없으면 기본값을 반환
    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }
}



/*
package sns.util;

import java.sql.*;

public class DBConn {
	public static final String URL = "jdbc:mysql://localhost:3306/sns";
	public static final String USER = "root";
	public static final String PASSWORD = "ezen";
	
	public static Connection conn() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);	
	}
	
	public static void close(ResultSet rs,PreparedStatement psmt,Connection conn) throws Exception {
		if(rs != null) rs.close();
		if(psmt != null) psmt.close();
		if(conn != null) conn.close();
	}
	
	public static void close(PreparedStatement psmt,Connection conn) throws Exception {
		if(psmt != null) psmt.close();
		if(conn != null) conn.close();
	}
}
*/