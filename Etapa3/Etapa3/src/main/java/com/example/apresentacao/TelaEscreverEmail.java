package com.example.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.example.dados.Email;
import com.example.dados.Usuario;
import com.example.negocio.CorreioEletronico;

public class TelaEscreverEmail extends JFrame {

    private JPanel painel = new JPanel();
    private JLabel destinatario = new JLabel("Insira o destinatario");
    private JTextField campoDestinatario = new JTextField();
    private JLabel mensagem = new JLabel("Insira a mensagem");
    private JTextArea campoMensagem = new JTextArea();
    private JButton botaoEnviar = new JButton("Enviar");
    private JButton botaoCancelar = new JButton("Cancelar");
    

    public TelaEscreverEmail(CorreioEletronico correioEletronico, Usuario usuario, Email email) {

        setTitle("Escrever Email");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 910, 500);

        setContentPane(painel);
        painel.setLayout(null);
        
        destinatario.setBounds(180, 30, 300, 25);
        painel.add(destinatario);

        campoDestinatario.setBounds(180, 80, 300, 25);
        painel.add(campoDestinatario);

        if(email != null) {
            campoDestinatario.setText(email.getRemetente().getEmail());
            campoDestinatario.setEditable(false);
        }

        mensagem.setBounds(180, 140, 500, 25);
        painel.add(mensagem);

        campoMensagem.setBounds(180, 180, 500, 100);
        painel.add(campoMensagem);

        botaoEnviar.setBounds(150, 320, 150, 25);
        painel.add(botaoEnviar);

        botaoCancelar.setBounds(320, 320, 150, 25);
        painel.add(botaoCancelar);

        botaoEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enviarEmail(correioEletronico, usuario);
            }
        });

        botaoCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void enviarEmail(CorreioEletronico correioEletronico, Usuario usuario) {
        String emailDestinatario = campoDestinatario.getText();

        Usuario destinatario = new Usuario();
        destinatario = correioEletronico.verificarEmail(emailDestinatario);

        String mensagem = campoMensagem.getText();

        Email email = new Email();
        email.setRemetente(usuario);
        email.setDestinatario(destinatario);
        email.setMensagem(mensagem);
        email.setData(LocalDateTime.now());

        if (destinatario == null) {
            JOptionPane.showMessageDialog(this, "Destinatario nao encontrado");
            return;
        }

        correioEletronico.adicionarEmailRecebido(destinatario, email);
        correioEletronico.adicionarEmailEnviado(usuario, email);
        
        JOptionPane.showMessageDialog(this, "Email enviado com sucesso!");
        dispose();
    }
}
