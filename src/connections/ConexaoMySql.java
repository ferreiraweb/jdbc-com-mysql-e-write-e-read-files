package connections;
import java.sql.*;

public class ConexaoMySql {
    
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "julho";

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {

            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }

            return conn;
    }
}
