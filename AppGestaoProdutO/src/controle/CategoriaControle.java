package controle;

import modelo.Categoria;
import modelo.dao.CategoriaDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controladora que intermedia a comunicação entre a View e o DAO.
 * Pacote: controle
 */
public class CategoriaControle {

    /**
     * Retorna uma lista de Strings com os nomes das categorias do banco.
     * @return List<String> com nomes das categorias
     */
    public static List<String> listarCategorias() {
        List<String> lista = new ArrayList<>();
        CategoriaDAO dao   = new CategoriaDAO();
        List<Categoria> itens = dao.listarTodas();
        for (Categoria c : itens) {
            lista.add(c.getNome());
        }
        return lista;
    }

    /**
     * Adiciona uma nova categoria ao banco de dados.
     * @param nome Nome da categoria a ser inserida
     */
    public static void adicionarCategoria(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }
        new CategoriaDAO().inserir(nome.trim());
    }
}
