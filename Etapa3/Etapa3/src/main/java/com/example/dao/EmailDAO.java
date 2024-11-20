package com.example.dao;

import com.example.dados.Email;
import com.example.dados.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO implements IEmailDAO {

    private Connection conexao;

    public EmailDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void adicionarEmailRecebido(Usuario destinatario, Email email) {
        try {
            String query = "INSERT INTO emails (id_remetente, id_destinatario, mensagem, dataEmail) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, email.getRemetente().getId());
            stmt.setInt(2, destinatario.getId());
            stmt.setString(3, email.getMensagem());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(email.getData()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarEmailEnviado(Usuario remetente, Email email) {
        try {
            String query = "INSERT INTO emails (id_remetente, id_destinatario, mensagem, dataEmail) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, remetente.getId());
            stmt.setInt(2, email.getDestinatario().getId());
            stmt.setString(3, email.getMensagem());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(email.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarUsuario(int idUsuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        return usuarioDAO.buscarUsuario(idUsuario);
    }

    @Override
    public List<Email> listarEmailsRecebidos(Usuario usuario) {
        List<Email> emails = new ArrayList<>();
        try {
            //String query = "SELECT * FROM usuarios WHERE id_destinatario = ?";
            String query = "SELECT * FROM emails e WHERE e.id_destinatario = ? AND NOT EXISTS " +
                           "(SELECT 1 FROM emails_excluidos ee WHERE ee.id_usuario = ? AND ee.id_email = e.id)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, usuario.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Email email = new Email();
                email.setId(rs.getInt("id"));
                email.setMensagem(rs.getString("mensagem"));
                email.setData(rs.getTimestamp("dataEmail").toLocalDateTime());
            
                int remetenteId = rs.getInt("id_remetente");
                Usuario remetente = buscarUsuario(remetenteId);
                email.setRemetente(remetente);
            
                int destinatarioId = rs.getInt("id_destinatario");
                Usuario destinatario = buscarUsuario(destinatarioId);
                email.setDestinatario(destinatario);

                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    @Override
    public List<Email> listarEmailsEnviados(Usuario usuario) {
        List<Email> emails = new ArrayList<>();
        try {
            //String query = "SELECT * FROM emails WHERE id_remetente = ?";
            String query = "SELECT * FROM emails e WHERE e.id_remetente = ? AND NOT EXISTS " +
                            "(SELECT 1 FROM emails_excluidos ee WHERE ee.id_usuario = ? AND ee.id_email = e.id)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, usuario.getId());
            stmt.setInt(2, usuario.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Email email = new Email();
                email.setId(rs.getInt("id"));
                email.setMensagem(rs.getString("mensagem"));
                email.setData(rs.getTimestamp("dataEmail").toLocalDateTime());

                int remetenteId = rs.getInt("id_remetente");
                Usuario remetente = buscarUsuario(remetenteId);
                email.setRemetente(remetente);

                int destinatarioId = rs.getInt("id_destinatario");
                Usuario destinatario = buscarUsuario(destinatarioId);
                email.setDestinatario(destinatario);

                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public void excluirEmail(Email email) {
        try {
            String query = "DELETE FROM emails WHERE id = ?";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, email.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEmailParaUsuario(int idUsuario, int idEmail) {
        try {
            String query = "INSERT INTO emails_excluidos (id_usuario, id_email) VALUES (?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
