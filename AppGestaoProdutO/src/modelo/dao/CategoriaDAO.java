package modelo.dao;

import modelo.Categoria;
import util.ConectaBanco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO responsável por acessar a tabela categorias no banco de dados.
 * Pacote: modelo.dao
 */
public class CategoriaDAO {

    private static final String LISTAR = "SELECT * FROM categorias ORDER BY nome";

    /**
     * Lista todas as categorias cadastradas no banco de dados.
     * @return Lista de objetos Categoria
     */
    public List<Categoria> listarTodas() {
        List<Categoria> categorias = new ArrayList<>();
        ResultSet   rs   = null;
        Statement   stmt = null;
        Connection  con  = null;

        try {
            con  = ConectaBanco.getConexao();
            stmt = con.createStatement();
            rs   = stmt.executeQuery(LISTAR);

            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException err1) {
            throw new RuntimeException("Erro ao listar categorias: " + err1.getMessage());
        } finally {
            try {
                if (rs   != null) rs.close();
                if (stmt != null) stmt.close();
                if (con  != null) con.close();
            } catch (SQLException err2) {
                throw new RuntimeException("Erro ao fechar conexão: " + err2.getMessage());
            }
        }
        return categorias;
    }

    /**
     * Insere uma nova categoria no banco de dados.
     * @param nome Nome da categoria
     */
    public void inserir(String nome) {
        String sql = "INSERT INTO categorias (nome) VALUES ('" + nome.replace("'", "''") + "')";
        Connection con  = null;
        Statement  stmt = null;
        try {
            con  = ConectaBanco.getConexao();
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir categoria: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con  != null) con.close();
            } catch (SQLException e2) {
                throw new RuntimeException(e2.getMessage());
            }
        }
    }
}
