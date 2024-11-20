package com.example.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import com.example.dados.Email;
import com.example.dados.Usuario;
import com.example.negocio.CorreioEletronico;

public class TelaVisualizarEmail extends JFrame {
    private JPanel painel = new JPanel();
    private JLabel labelRemetente = new JLabel("Remetente:");
    private JLabel labelDestinatario = new JLabel("Destinatário:");
    private JLabel labelData = new JLabel("Data:");
    private JLabel labelMensagem = new JLabel("Mensagem:");
    private JTextArea campoMensagem = new JTextArea();
    private JButton botaoResponder = new JButton("Responder");
    private JButton botaoVoltar = new JButton("Voltar");

    public TelaVisualizarEmail(CorreioEletronico correioEletronico, Usuario usuario, Email email) {
        setTitle("Visualizar Email");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 910, 500);

        setContentPane(painel);
        painel.setLayout(null);

        labelRemetente.setBounds(20, 30, 300, 25);
        painel.add(labelRemetente);

        JLabel campoRemetente = new JLabel(email.getRemetente().getEmail());
        campoRemetente.setBounds(150, 30, 300, 25);
        painel.add(campoRemetente);

        labelDestinatario.setBounds(20, 70, 300, 25);
        painel.add(labelDestinatario);

        JLabel campoDestinatario = new JLabel(email.getDestinatario().getEmail());
        campoDestinatario.setBounds(150, 70, 300, 25);
        painel.add(campoDestinatario);

        labelData.setBounds(20, 110, 300, 25);
        painel.add(labelData);

        JLabel campoData = new JLabel(email.getData().toString());
        campoData.setBounds(150, 110, 300, 25);
        painel.add(campoData);

        labelMensagem.setBounds(20, 150, 300, 25);
        painel.add(labelMensagem);

        campoMensagem.setText(email.getMensagem());
        campoMensagem.setBounds(150, 150, 500, 200);
        campoMensagem.setLineWrap(true);
        campoMensagem.setWrapStyleWord(true);
        campoMensagem.setEditable(false);
        painel.add(campoMensagem);

        botaoResponder.setBounds(150, 370, 150, 25);
        painel.add(botaoResponder);

        botaoVoltar.setBounds(320, 370, 150, 25);
        painel.add(botaoVoltar);

        botaoResponder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaEscreverEmail(correioEletronico, usuario, email).setVisible(true);
                dispose();
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //new TelaEmails(correioEletronico, usuario).setVisible(true);
                dispose();
            }
        });
    }
}
