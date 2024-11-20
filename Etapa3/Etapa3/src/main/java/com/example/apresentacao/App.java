package com.example.apresentacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

import com.example.negocio.CorreioEletronico;

public class App {

    public static CorreioEletronico correioEletronico;

    public static void main(String[] args) {
        
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/emails", "postgres", "nick");
            correioEletronico = new CorreioEletronico(conexao);
            if (conexao != null) {
                System.out.println("Conectado ao banco de dados!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new TelaLoginCadastro(correioEletronico).setVisible(true));
    }
}