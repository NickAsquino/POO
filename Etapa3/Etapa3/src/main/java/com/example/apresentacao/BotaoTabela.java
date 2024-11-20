package com.example.apresentacao;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BotaoTabela extends JPanel implements TableCellRenderer {
    private JButton botaoResponder;
    private JButton botaoExcluir;

    public BotaoTabela() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        this.botaoResponder = new JButton("Responder");
        this.botaoExcluir = new JButton("Excluir");
        
        this.add(botaoResponder, gbc);
        gbc.gridx = 1;
        this.add(botaoExcluir, gbc);
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean hasFocus, int linha, int coluna) {
        return this;
    }
}