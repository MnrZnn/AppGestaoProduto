package view;

import controle.CategoriaControle;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Janela principal da aplicação de Gestão de Produtos.
 * Contém os campos Id, Nome, Categoria (combobox) e botão Adicionar,
 * conforme especificado na atividade prática (PDF).
 * Pacote: view
 */
public class JanelaPrincipal extends JFrame {

    // Componentes conforme PDF
    private JLabel     idprodLbl    = new JLabel("Id do produto");
    private JTextField idprodTxt    = new JTextField(15);

    private JLabel     nomeprodLbl  = new JLabel("Nome do produto");
    private JTextField nomeprodTxt  = new JTextField(15);

    private JLabel            categoriaLbl = new JLabel("Categoria");
    private JComboBox<String> categoriaCmb = new JComboBox<>();

    private JButton adicionarBtn = new JButton("Adicionar");

    // Painel JPanel de categorias (view.Painel)
    private Painel painel;

    public JanelaPrincipal() {
        setTitle("Gestão de Produtos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Formulário de produto no topo
        JPanel produtoPanel = criarPainelProduto();
        produtoPanel.setBorder(BorderFactory.createTitledBorder("Cadastro de Produto"));
        add(produtoPanel, BorderLayout.NORTH);

        // JPanel de categorias no centro
        painel = new Painel();
        add(painel, BorderLayout.CENTER);

        // Carrega categorias no combobox via CategoriaControle
        carregarCategoriasNoCombo();

        adicionarBtn.addActionListener(e -> adicionarProduto());

        pack();
        setMinimumSize(new Dimension(500, 580));
        setLocationRelativeTo(null);
    }

    private JPanel criarPainelProduto() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;

        // Linha 0: idprodLbl + idprodTxt
        g.gridx = 0; g.gridy = 0; g.fill = GridBagConstraints.NONE;
        panel.add(idprodLbl, g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        panel.add(idprodTxt, g);

        // Linha 1: nomeprodLbl + nomeprodTxt
        g.gridx = 0; g.gridy = 1; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        panel.add(nomeprodLbl, g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        panel.add(nomeprodTxt, g);

        // Linha 2: categoriaLbl + categoriaCmb
        categoriaCmb.setEditable(true); // editável conforme PDF
        g.gridx = 0; g.gridy = 2; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        panel.add(categoriaLbl, g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        panel.add(categoriaCmb, g);

        // Linha 3: adicionarBtn
        adicionarBtn.setBackground(new Color(70, 130, 180));
        adicionarBtn.setForeground(Color.WHITE);
        adicionarBtn.setFocusPainted(false);
        adicionarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        adicionarBtn.setOpaque(true);
        adicionarBtn.setBorderPainted(false);

        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        g.fill = GridBagConstraints.NONE; g.weightx = 0;
        g.anchor = GridBagConstraints.CENTER;
        panel.add(adicionarBtn, g);

        return panel;
    }

    /**
     * Carrega os nomes das categorias do banco via CategoriaControle no JComboBox.
     */
    public void carregarCategoriasNoCombo() {
        categoriaCmb.removeAllItems();
        try {
            List<String> categorias = CategoriaControle.listarCategorias();
            for (String c : categorias) {
                categoriaCmb.addItem(c);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar categorias: " + ex.getMessage(),
                "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ação do botão Adicionar.
     */
    private void adicionarProduto() {
        String id       = idprodTxt.getText().trim();
        String nome     = nomeprodTxt.getText().trim();
        String categoria = categoriaCmb.getEditor().getItem() != null
                           ? categoriaCmb.getEditor().getItem().toString().trim()
                           : "";

        if (id.isEmpty() || nome.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Preencha todos os campos antes de adicionar.",
                "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmação (futuramente conectar com ProdutoDAO)
        JOptionPane.showMessageDialog(this,
            "Produto adicionado com sucesso!\n\nID: " + id +
            "\nNome: " + nome + "\nCategoria: " + categoria,
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        idprodTxt.setText("");
        nomeprodTxt.setText("");
        carregarCategoriasNoCombo();
        painel.carregarCategorias();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new JanelaPrincipal().setVisible(true);
        });
    }
}
