package com.example.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//import org.w3c.dom.events.MouseEvent;

import com.example.dados.Email;
import com.example.dados.Usuario;
import com.example.negocio.CorreioEletronico;

public class TelaEmails extends JFrame {

    private JPanel painel = new JPanel();
    private JPanel painelEntrada = new JPanel();
    private JScrollPane painelScrollTabelaEmails = new JScrollPane();
    private JLabel caixaTipoEmails;
    private JTable tabelaEmails;
    private TabelaEmailsEnviados emailsEnviados;
    private TabelaEmailsRecebidos emailsRecebidos;
    
    private JLabel caixaNome;
    private JButton botaoEmailsRecebidos = new JButton("Emails recebidos");
    private JButton botaoEmailsEnviados = new JButton("Emails enviados");
    private JButton botaoEscrever = new JButton("Escrever");
    private JButton botaoSair = new JButton("Sair");

    public TelaEmails(CorreioEletronico correioEletronico, Usuario usuario) {

        setTitle("Emails");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);

        setContentPane(painel);
        painel.setLayout(null);

        painelEntrada.setBounds(1, 1, 250, 600);
        painelEntrada.setLayout(null);
        painel.add(painelEntrada);

        caixaNome = new JLabel(usuario.getNome());
        caixaNome.setBounds(30, 30, 180, 25);
        painelEntrada.add(caixaNome);

        caixaTipoEmails = new JLabel("Emails recebidos");
        caixaTipoEmails.setBounds(300, 30, 150, 25);
        painel.add(caixaTipoEmails);

        emailsRecebidos = new TabelaEmailsRecebidos(correioEletronico, usuario);

        painelScrollTabelaEmails.setBounds(252, 100, 900, 500);
        painel.add(painelScrollTabelaEmails);

        tabelaEmails = new JTable(emailsRecebidos);
        painelScrollTabelaEmails.setViewportView(tabelaEmails);
        tabelaEmails.setRowHeight(40);

        tabelaEmails.getColumnModel().getColumn(3).setCellRenderer(new BotaoTabela());
        tabelaEmails.getColumnModel().getColumn(3).setCellEditor(new BotaoResponderExcluir(correioEletronico, usuario));

        botaoEmailsRecebidos.setBounds(30, 80, 180, 25);
        painelEntrada.add(botaoEmailsRecebidos);

        botaoEmailsEnviados.setBounds(30, 120, 180, 25);
        painelEntrada.add(botaoEmailsEnviados);

        botaoEscrever.setBounds(30, 160, 180, 25);
        painelEntrada.add(botaoEscrever);

        botaoSair.setBounds(30, 400, 180, 25);
        painelEntrada.add(botaoSair);

        tabelaEmails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable tabela = (JTable) e.getSource();
                    int row = tabela.getSelectedRow();
                    if (row != -1) {
                        Email email;
                        if (tabela.getModel() instanceof TabelaEmailsRecebidos) {
                            email = ((TabelaEmailsRecebidos) tabela.getModel()).getEmailAt(row);
                        } else {
                            email = ((TabelaEmailsEnviados) tabela.getModel()).getEmailAt(row);
                        }
                        new TelaVisualizarEmail(correioEletronico, usuario, email).setVisible(true);
                    }
                }
            }
        });

        botaoEmailsRecebidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                caixaTipoEmails.setText("Emails recebidos");

                emailsRecebidos = new TabelaEmailsRecebidos(correioEletronico, usuario);
                tabelaEmails.setModel(emailsRecebidos);

                tabelaEmails.getColumnModel().getColumn(3).setCellRenderer(new BotaoTabela());
                tabelaEmails.getColumnModel().getColumn(3).setCellEditor(new BotaoResponderExcluir(correioEletronico, usuario));
            }
        });

        botaoEmailsEnviados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                caixaTipoEmails.setText("Emails enviadados");

                emailsEnviados = new TabelaEmailsEnviados(correioEletronico, usuario);
                tabelaEmails.setModel(emailsEnviados);
                
                tabelaEmails.getColumnModel().getColumn(3).setCellRenderer(new BotaoTabela());
                tabelaEmails.getColumnModel().getColumn(3).setCellEditor(new BotaoResponderExcluir(correioEletronico, usuario));
            }
        });

        botaoEscrever.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaEscreverEmail(correioEletronico, usuario, null).setVisible(true);
                //dispose();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaLoginCadastro(correioEletronico).setVisible(true);
                dispose();
            }
        });
    }
}