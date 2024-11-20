package com.example.apresentacao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.dados.Email;
import com.example.dados.Usuario;
import com.example.negocio.CorreioEletronico;

public class TabelaEmailsRecebidos extends AbstractTableModel {

    private final String[] columnNames = {"Remetente", "Destinatario", "Data", "responder/Excluir"};
    private List<Email> emails;

    public TabelaEmailsRecebidos(CorreioEletronico correioEletronico, Usuario usuario) {
        this.emails = correioEletronico.listarEmailsRecebidos(usuario);
    }

    @Override
    public int getRowCount() {
        return emails.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Email email = emails.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return email.getRemetente().getEmail();
            case 1: 
                return email.getDestinatario().getEmail();
            case 2:
                return email.getData();
            case 3:
                return "Ações";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    public Email getEmailAt(int rowIndex) {
        return emails.get(rowIndex);
    }

    public void removeEmail(int rowIndex) {
        emails.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}

