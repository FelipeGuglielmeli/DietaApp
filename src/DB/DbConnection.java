/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author felip
 */
public class DbConnection {
    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "2306");
            properties.setProperty("useSSL", "false");
            properties.setProperty("useTimezone", "true");
            properties.setProperty("serverTimezone", "UTC");
            properties.setProperty("allowPublicKeyRetrieval","true");
            
            String con = "jdbc:mysql://localhost/dietaapp";
            return DriverManager.getConnection(con, properties);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
