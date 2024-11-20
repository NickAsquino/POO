package com.example.apresentacao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingUtilities;

import com.example.dados.Usuario;
import com.example.negocio.CorreioEletronico;

public class TelaLoginCadastro extends JFrame {

    private JPanel painel = new JPanel();
    private JLabel infoCaixaEmail = new JLabel("Email:");
    private JTextField caixaTextoEmail = new JTextField();
    private JLabel infoCaixaSenha = new JLabel("Senha:");
    private JTextField caixaTextoSenha = new JTextField();
    private JButton botaoCadastro = new JButton("Cadastrar usuario");
    private JButton botaoEntrar = new JButton("Entrar");

    //private CorreioEletronico correioEletronico = new CorreioEletronico();

    public TelaLoginCadastro(CorreioEletronico correioEletronico) {

        setTitle("Login e Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 500);

        setContentPane(painel);
        painel.setLayout(null);

        infoCaixaEmail.setBounds(200, 100, 200, 20);
        painel.add(infoCaixaEmail);

        caixaTextoEmail.setBounds(200, 130, 200, 20);
        painel.add(caixaTextoEmail);

        infoCaixaSenha.setBounds(200, 160, 200, 20);
        painel.add(infoCaixaSenha);

        caixaTextoSenha.setBounds(200, 190, 200, 20);
        painel.add(caixaTextoSenha);

        botaoCadastro.setBounds(160, 250, 140, 20);
        painel.add(botaoCadastro);

        botaoEntrar.setBounds(350, 250, 120, 20);
        painel.add(botaoEntrar);

        botaoCadastro.addActionListener(e -> {
            new TelaCadastroUsuario(correioEletronico).setVisible(true);
        });

        botaoEntrar.addActionListener(e -> {
            String email = caixaTextoEmail.getText();
            String senha = caixaTextoSenha.getText();

            Usuario usuario = correioEletronico.verificarEmail(email);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                new TelaEmails(correioEletronico, usuario).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Email ou senha inválidos!");
            }
        });
    }
}