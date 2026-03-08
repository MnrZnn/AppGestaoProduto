package app;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {

    private JTextField idprodTxt = new JTextField(15);
    private JTextField nomeprodTxt = new JTextField(15);
    private JComboBox<String> categoriaCmb = new JComboBox<>();

    public JanelaPrincipal() {
        setTitle("Gestão de Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;

        categoriaCmb.setEditable(true);
        for (String c : CategoriaControle.listarCategorias())
            categoriaCmb.addItem(c);

        add(new JLabel("Id do produto"),    grid(g, 0, 0));
        add(idprodTxt,                      grid(g, 1, 0));
        add(new JLabel("Nome do produto"),  grid(g, 0, 1));
        add(nomeprodTxt,                    grid(g, 1, 1));
        add(new JLabel("Categoria"),        grid(g, 0, 2));
        add(categoriaCmb,                   grid(g, 1, 2));

        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        add(new JButton("Adicionar"), g);

        pack();
        setLocationRelativeTo(null);
    }

    private GridBagConstraints grid(GridBagConstraints g, int x, int y) {
        g.gridx = x; g.gridy = y; g.gridwidth = 1;
        return g;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true));
    }
}
