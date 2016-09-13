package br.com.filarmonica.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Conexao instance = null;
    public static Connection conn = null;
    
    private Conexao() {
        
    }
    
    public static Conexao getInstance() {
        if(instance == null) {
            instance = new Conexao();
        }
        return instance;
    }
    
    public static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/filarmonica";
        String user = "root";
        String password = "";
        try {
            if(conn == null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
        return conn;
    }
}
