package connections;
import java.sql.*;

public class Conexao {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/livraria";
    private static final String USER = "postgres";
    private static final String PASSWORD = "julho";

    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {

            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }

            return conn;
    }
}
