package app;

import java.sql.*;

public class ConectaBanco {
    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbloja", "root", "");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
