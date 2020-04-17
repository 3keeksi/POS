/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class DBTester {
    private Connection connection;
    
    public DBTester() throws ClassNotFoundException {
        // load postgres database driver (optional)
        Class.forName("org.postgresql.Driver");
    }
    
    /**
     * connect to postgres database dbName
     * @param dbName 
     */
    public void connect(String dbName) throws SQLException {
        // Default port number is 5432
        String url = "jdbc:postgresql://localhost/" + dbName;
        String username = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public static void main(String[] args) {
        try {
            DBTester dbt = new DBTester();
            dbt.connect("postgres");
            dbt.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
