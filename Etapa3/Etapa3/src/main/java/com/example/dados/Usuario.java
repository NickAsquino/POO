package com.example.dados;

import java.util.List;
import java.util.LinkedList;

public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private String senha;

    public List<Email> listaEmailsEnviados = new LinkedList<Email>();
    public List<Email> listaEmailsRecebidos = new LinkedList<Email>();

    /*public Usuario(int id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Email> getListaEmailsEnviados() {
        return listaEmailsEnviados;
    }

    public void setListaEmailsEnviados(List<Email> listaEmailsEnviados) {
        this.listaEmailsEnviados = listaEmailsEnviados;
    }

    public List<Email> getlistaEmailsRecebidos() {
        return listaEmailsRecebidos;
    }

    public void setListaEmailsRecebidos(List<Email> listaEmailsRecebidos) {
        this.listaEmailsRecebidos = listaEmailsRecebidos;
    }
}