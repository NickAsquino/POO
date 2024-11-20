package com.example.dao;

import java.util.List;

import com.example.dados.Usuario;

public interface IUsuarioDAO {
    Usuario verificarEmail(String email);
    void cadastrarUsuario(Usuario usuario);
    Usuario buscarUsuario(int idUsuario);
    List<Usuario> listarUsuarios();
}