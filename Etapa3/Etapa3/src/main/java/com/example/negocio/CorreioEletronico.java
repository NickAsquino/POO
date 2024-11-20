package com.example.negocio;

//import com.example.dao.IEmailDAO;
import com.example.dao.EmailDAO;
import com.example.dao.IEmailDAO;
import com.example.dao.IUsuarioDAO;
import com.example.dao.UsuarioDAO;
//import com.example.dao.IUsuarioDAO;
import com.example.dados.Email;
import com.example.dados.Usuario;

import java.sql.Connection;
import java.util.List;

public class CorreioEletronico {

    private IUsuarioDAO usuarioDAO;
    private IEmailDAO emailDAO;

    public CorreioEletronico(Connection conexao) {
        this.usuarioDAO = new UsuarioDAO(conexao);
        this.emailDAO = new EmailDAO(conexao);
    }

    public Usuario verificarEmail(String email) {
        return usuarioDAO.verificarEmail(email);
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuarioDAO.verificarEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já está em uso.");
        }
        usuarioDAO.cadastrarUsuario(usuario);
        
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public void adicionarEmailRecebido(Usuario destinatario, Email email) {
        emailDAO.adicionarEmailRecebido(destinatario, email);
    }

    public void adicionarEmailEnviado(Usuario remetente, Email email) {
        emailDAO.adicionarEmailEnviado(remetente, email);
    }

    public List<Email> listarEmailsRecebidos(Usuario usuario) {
        return emailDAO.listarEmailsRecebidos(usuario);
    }

    public List<Email> listarEmailsEnviados(Usuario usuario) {
        return emailDAO.listarEmailsEnviados(usuario);
    }

    public void excluirEmailParaUsuario(int idUsuario, int idEmail){
        emailDAO.excluirEmailParaUsuario(idUsuario, idEmail);
    }

    public void excluirEmail(Email email) {
        emailDAO.excluirEmail(email);
    }
}
