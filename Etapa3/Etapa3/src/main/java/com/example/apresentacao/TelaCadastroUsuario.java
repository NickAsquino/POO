package com.example.apresentacao;

import javax.swing.*;
import com.example.negocio.CorreioEletronico;
import com.example.dados.Usuario;

public class TelaCadastroUsuario extends JFrame {

    private JPanel painel = new JPanel();
    private JLabel infoNome = new JLabel("Nome:");
    private JTextField caixaTextoNome = new JTextField();
    private JLabel infoEmail = new JLabel("Email:");
    private JTextField caixaTextoEmail = new JTextField();
    private JLabel infoSenha = new JLabel("Senha:");
    private JTextField caixaTextoSenha = new JTextField();
    private JButton botaoCadastrar = new JButton("Cadastrar");

    public TelaCadastroUsuario(CorreioEletronico correioEletronico) {

        setTitle("Cadastro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 400, 300);

        setContentPane(painel);
        painel.setLayout(null);

        infoNome.setBounds(50, 30, 200, 20);
        painel.add(infoNome);

        caixaTextoNome.setBounds(50, 60, 200, 20);
        painel.add(caixaTextoNome);

        infoEmail.setBounds(50, 90, 200, 20);
        painel.add(infoEmail);

        caixaTextoEmail.setBounds(50, 120, 200, 20);
        painel.add(caixaTextoEmail);

        infoSenha.setBounds(50, 150, 200, 20);
        painel.add(infoSenha);

        caixaTextoSenha.setBounds(50, 180, 200, 20);
        painel.add(caixaTextoSenha);

        botaoCadastrar.setBounds(50, 220, 100, 20);
        painel.add(botaoCadastrar);

        botaoCadastrar.addActionListener(e -> {
            Usuario u = new Usuario();

            u.setNome(caixaTextoNome.getText());
            u.setEmail(caixaTextoEmail.getText());
            u.setSenha(caixaTextoSenha.getText());
        
            correioEletronico.cadastrarUsuario(u);

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            dispose();
        });
    }
}