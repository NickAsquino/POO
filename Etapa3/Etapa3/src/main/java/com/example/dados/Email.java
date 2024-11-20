package com.example.dados;

import java.time.LocalDateTime;

public class Email {

    private int id;
    private Usuario remetente;
    private Usuario destinatario;
    private String mensagem;
    private LocalDateTime data;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getRemetente() {
        return remetente;
    }
    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }
    public Usuario getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    /*public String toString() {
        return "Remetente\n" + remetente.toString() + "\n"
        + "\nDestinatario\n" + destinatario.toString();
    }*/
    

}