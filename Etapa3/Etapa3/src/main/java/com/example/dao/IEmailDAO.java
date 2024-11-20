package com.example.dao;

import com.example.dados.Email;
import com.example.dados.Usuario;
import java.util.List;

public interface IEmailDAO {
    void adicionarEmailRecebido(Usuario destinatario, Email email);
    void adicionarEmailEnviado(Usuario remetente, Email email);
    Usuario buscarUsuario(int idUsuario);
    List<Email> listarEmailsRecebidos(Usuario usuario);
    List<Email> listarEmailsEnviados(Usuario usuario);
    void excluirEmail(Email email);
    void excluirEmailParaUsuario(int idUsuario, int idEmail);
}