package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Utils {

    public static Connection getDatabaseConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/ebookstore";
        String username = "stefan";
        String password = "stefan";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static Connection getPoolConnection() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/EbookStoreConnection");
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "Eroare conexiune pool", ex);
        }
        return conn;
    }
}
