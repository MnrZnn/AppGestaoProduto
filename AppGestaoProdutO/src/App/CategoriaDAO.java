package app;

import java.sql.*;
import java.util.*;

public class CategoriaDAO {
    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        try {
            Connection con = ConectaBanco.getConexao();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
            while (rs.next())
                lista.add(new Categoria(rs.getInt("id"), rs.getString("nome")));
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return lista;
    }
}
