package quizquimica.view;

import quizquimica.controller.JogarController;
import javax.swing.*;
import java.awt.*;

public class TelaJogar extends JFrame {

    private JButton BotaoJogar;
    private JButton BotaoProfessor;
    private JLabel TermoUso;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel9;
    private JPanel jPanel3;

    public TelaJogar() {

        // deixa visual mais moderno
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel3 = new JPanel();
        jLabel9 = new JLabel();
        jLabel4 = new JLabel();
        BotaoJogar = new JButton();
        BotaoProfessor = new JButton();
        jLabel5 = new JLabel();
        jLabel3 = new JLabel();
        TermoUso = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("QuiZmica");
        setSize(430, 360);
        setResizable(false);

        getContentPane().setLayout(null);

        // ===== PAINEL =====
        jPanel3.setBackground(new Color(238, 243, 249));
        jPanel3.setLayout(null);
        jPanel3.setBounds(0, 0, 430, 360);

        // ===== LOGO =====
        jLabel9.setIcon(new ImageIcon(
                getClass().getResource("/quizquimica/images/quizmica_titulo.png")
        ));
        jLabel9.setBounds(15, 10, 320, 100);
        jPanel3.add(jLabel9);

        // ===== TEXTO =====
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabel4.setForeground(new Color(40, 40, 40));
        jLabel4.setText("Misture ideias. Descubra respostas.");
        jLabel4.setBounds(80, 105, 300, 30);
        jPanel3.add(jLabel4);

        // ===== BOTÃO JOGAR =====
        BotaoJogar.setText("JOGAR");
        BotaoJogar.setFont(new Font("Arial", Font.BOLD, 18));
        BotaoJogar.setForeground(Color.WHITE);
        BotaoJogar.setBackground(new Color(179, 40, 36));

        // IMPORTANTE
        BotaoJogar.setFocusPainted(false);
        BotaoJogar.setBorderPainted(false);
        BotaoJogar.setContentAreaFilled(true);
        BotaoJogar.setOpaque(true);

        BotaoJogar.setBounds(95, 155, 140, 40);

        BotaoJogar.addActionListener(evt -> {
            JogarController controller = new JogarController(this);
            controller.abrirLogin();
        });

        jPanel3.add(BotaoJogar);

        // ===== BOTÃO PROFESSOR =====
        BotaoProfessor.setText("Acesso do professor");
        BotaoProfessor.setFont(new Font("Arial", Font.PLAIN, 11));
        BotaoProfessor.setForeground(Color.WHITE);
        BotaoProfessor.setBackground(new Color(120, 120, 120));

        // IMPORTANTE
        BotaoProfessor.setFocusPainted(false);
        BotaoProfessor.setBorderPainted(false);
        BotaoProfessor.setContentAreaFilled(true);
        BotaoProfessor.setOpaque(true);

        BotaoProfessor.setBounds(285, 15, 120, 25);

        BotaoProfessor.addActionListener(evt -> {
            JogarController controller = new JogarController(this);
            controller.abrirLogin();
        });

        jPanel3.add(BotaoProfessor);

        // ===== LOGO ETEC =====
        jLabel5.setIcon(new ImageIcon(
                getClass().getResource("/quizquimica/images/etec.png")
        ));
        jLabel5.setBounds(20, 220, 120, 80);
        jPanel3.add(jLabel5);

        // ===== PERSONAGEM =====
        jLabel3.setIcon(new ImageIcon(
                getClass().getResource("/quizquimica/images/inicialCient.png")
        ));
        jLabel3.setBounds(245, 135, 160, 180);
        jPanel3.add(jLabel3);

        // ===== TERMO DE USO =====
        TermoUso.setText("Termo de Uso");
        TermoUso.setFont(new Font("Arial", Font.ITALIC, 11));
        TermoUso.setForeground(new Color(100, 100, 100));
        TermoUso.setBounds(160, 300, 100, 20);

        TermoUso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JogarController controller = new JogarController(TelaJogar.this);
                controller.abrirTermos();
            }
        });

        jPanel3.add(TermoUso);

        // ===== ADD PANEL =====
        getContentPane().add(jPanel3);
    }
}