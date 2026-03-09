package view;

import controle.CategoriaControle;
import modelo.Categoria;
import modelo.dao.CategoriaDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Painel JPanel que exibe as categorias cadastradas e permite adicionar novas.
 * Pacote: view
 */
public class Painel extends JPanel {

    private JTextField        nomeCategoriaTxt = new JTextField(20);
    private JButton           adicionarCatBtn  = new JButton("Adicionar Categoria");
    private JButton           atualizarBtn     = new JButton("Atualizar Lista");
    private DefaultTableModel tableModel;
    private JTable            tabelaCategorias;
    private JLabel            statusLbl        = new JLabel(" ");

    public Painel() {
        initComponents();
        carregarCategorias();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        // Título
        JLabel titulo = new JLabel("Gerenciamento de Categorias", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        add(titulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"ID", "Nome da Categoria"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelaCategorias = new JTable(tableModel);
        tabelaCategorias.setFont(new Font("Arial", Font.PLAIN, 13));
        tabelaCategorias.setRowHeight(24);
        tabelaCategorias.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabelaCategorias.setSelectionBackground(new Color(173, 216, 230));
        tabelaCategorias.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaCategorias.getColumnModel().getColumn(1).setPreferredWidth(300);

        JScrollPane scrollPane = new JScrollPane(tabelaCategorias);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Categorias Cadastradas"));
        add(scrollPane, BorderLayout.CENTER);

        // Formulário nova categoria
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nova Categoria"));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;

        g.gridx = 0; g.gridy = 0;
        formPanel.add(new JLabel("Nome:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        nomeCategoriaTxt.setFont(new Font("Arial", Font.PLAIN, 13));
        formPanel.add(nomeCategoriaTxt, g);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        botoesPanel.setBackground(new Color(245, 245, 245));
        estilizarBotao(adicionarCatBtn, new Color(34, 139, 34), Color.WHITE);
        estilizarBotao(atualizarBtn,    new Color(70, 130, 180), Color.WHITE);
        botoesPanel.add(adicionarCatBtn);
        botoesPanel.add(atualizarBtn);

        g.gridx = 0; g.gridy = 1; g.gridwidth = 2; g.fill = GridBagConstraints.NONE; g.weightx = 0;
        formPanel.add(botoesPanel, g);

        statusLbl.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLbl.setForeground(new Color(100, 100, 100));
        g.gridx = 0; g.gridy = 2; g.gridwidth = 2;
        formPanel.add(statusLbl, g);

        add(formPanel, BorderLayout.SOUTH);

        adicionarCatBtn.addActionListener(e -> adicionarCategoria());
        atualizarBtn.addActionListener(e -> carregarCategorias());
    }

    private void estilizarBotao(JButton btn, Color fundo, Color texto) {
        btn.setBackground(fundo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
    }

    public void carregarCategorias() {
        tableModel.setRowCount(0);
        try {
            List<Categoria> categorias = new CategoriaDAO().listarTodas();
            for (Categoria c : categorias) {
                tableModel.addRow(new Object[]{c.getId(), c.getNome()});
            }
            setStatus("✔ " + categorias.size() + " categoria(s) carregada(s).", new Color(34, 139, 34));
        } catch (Exception ex) {
            setStatus("✘ Erro ao carregar: " + ex.getMessage(), Color.RED);
        }
    }

    private void adicionarCategoria() {
        String nome = nomeCategoriaTxt.getText().trim();
        if (nome.isEmpty()) {
            setStatus("✘ Informe o nome da categoria.", Color.RED);
            nomeCategoriaTxt.requestFocus();
            return;
        }
        try {
            CategoriaControle.adicionarCategoria(nome);
            nomeCategoriaTxt.setText("");
            carregarCategorias();
            setStatus("✔ Categoria '" + nome + "' adicionada!", new Color(34, 139, 34));
        } catch (Exception ex) {
            setStatus("✘ Erro: " + ex.getMessage(), Color.RED);
        }
    }

    private void setStatus(String msg, Color cor) {
        statusLbl.setText(msg);
        statusLbl.setForeground(cor);
    }
}
