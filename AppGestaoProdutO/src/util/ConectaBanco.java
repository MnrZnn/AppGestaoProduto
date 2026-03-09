package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe utilitária responsável pela conexão com o banco de dados MySQL.
 * Pacote: util
 * Banco: dbloja | Porta: 3306 | Usuário: root | Senha: (vazia)
 */
public class ConectaBanco {

    private static final String URL    = "jdbc:mysql://localhost:3306/dbloja?useSSL=false&serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER   = "root";
    private static final String PASS   = "";

    public static Connection getConexao() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | java.sql.SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage());
        }
        return con;
    }
}
