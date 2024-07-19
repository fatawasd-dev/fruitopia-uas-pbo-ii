/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
/**
 *
 * @author Asani
 */
public class DatabaseConfig {
    private static Connection connection;
    
    public static Connection getConnection() {
        if (connection == null) {
            try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                if (input == null) {
                    System.out.println("Sorry, unable to find config.properties");
                    return null;
                }
                
                prop.load(input);
                
                String url = prop.getProperty("db.url");
                String username = prop.getProperty("db.username");
                String password = prop.getProperty("db.password");
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return connection;
    }
}
