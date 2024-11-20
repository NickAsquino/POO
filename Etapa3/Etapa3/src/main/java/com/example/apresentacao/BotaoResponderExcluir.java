package com.example.apresentacao;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.example.dados.Email;
import com.example.dados.Usuario;
import com.example.negocio.CorreioEletronico;

import javax.swing.AbstractCellEditor;

public class BotaoResponderExcluir extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private JButton botaoResponder;
    private JButton botaoExcluir;
    private CorreioEletronico correioEletronico;
    private Usuario usuario;
    private JTable tabela;
    private int rowIndex;

    public BotaoResponderExcluir(CorreioEletronico correioEletronico, Usuario usuario) {
        this.correioEletronico = correioEletronico;
        this.usuario = usuario;
        
        this.panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        this.botaoResponder = new JButton("Responder");
        this.botaoExcluir = new JButton("Excluir");
        
        this.panel.add(botaoResponder);
        this.panel.add(botaoExcluir);

        this.botaoResponder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                responderEmail();
                fireEditingStopped();
            }
        });

        this.botaoExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirEmail();
                fireEditingStopped();
            }
        });
    }

    private void responderEmail() {
        Email email = null;
        if (tabela.getModel() instanceof TabelaEmailsRecebidos) {
            email = ((TabelaEmailsRecebidos) tabela.getModel()).getEmailAt(rowIndex);
        } else if (tabela.getModel() instanceof TabelaEmailsEnviados) {
            email = ((TabelaEmailsEnviados) tabela.getModel()).getEmailAt(rowIndex);
        }
        
        if (email != null) {
            new TelaEscreverEmail(correioEletronico, usuario, email).setVisible(true);
        }
        atualizarTabela();
    }

    private void excluirEmail() {
        Email email = getEmail(rowIndex);
        
        if (email != null) {
            correioEletronico.excluirEmailParaUsuario(usuario.getId(), email.getId());

            if (tabela.getModel() instanceof TabelaEmailsRecebidos) {
                ((TabelaEmailsRecebidos) tabela.getModel()).removeEmail(rowIndex);
            } else if (tabela.getModel() instanceof TabelaEmailsEnviados) {
                ((TabelaEmailsEnviados) tabela.getModel()).removeEmail(rowIndex);
            }
            atualizarTabela();
        }
    }

    private Email getEmail(int rowIndex) {
        if (tabela.getModel() instanceof TabelaEmailsRecebidos) {
            return ((TabelaEmailsRecebidos) tabela.getModel()).getEmailAt(rowIndex);
        } else if (tabela.getModel() instanceof TabelaEmailsEnviados) {
            return ((TabelaEmailsEnviados) tabela.getModel()).getEmailAt(rowIndex);
        }
        return null;
    }

    private void atualizarTabela() {
        tabela.revalidate();
        tabela.repaint();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.tabela = table;
        this.rowIndex = row;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
