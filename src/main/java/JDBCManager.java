import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCManager {
    public Connection recuperarConexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost/proyectointegrador";
        String usuario = "root";
        String pass = "!Gianfranco1";

        return DriverManager.getConnection(url, usuario, pass);
    }
}



