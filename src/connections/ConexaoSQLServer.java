
package connections;
import java.sql.*;

public class ConexaoSQLServer {
    
    private static final String URL = "jdbc:sqlserver://10.39.64.50;encrypt=false";

    private static final String USER = "sisproj";
    private static final String PASSWORD = "s15pr0jc3tr10";

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {

            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }

            return conn;
    }
}