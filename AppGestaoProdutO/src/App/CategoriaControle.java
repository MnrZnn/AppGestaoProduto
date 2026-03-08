package app;

import java.util.*;

public class CategoriaControle {
    public static List<String> listarCategorias() {
        List<String> lista = new ArrayList<>();
        for (Categoria c : new CategoriaDAO().listarTodas())
            lista.add(c.getNome());
        return lista;
    }
}
