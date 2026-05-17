package quizquimica.view;

import quizquimica.controller.AlunoController;

public class DashboardAluno extends javax.swing.JFrame {
    public DashboardAluno() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel(); 
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setSize(400, 330);
        setResizable(false);
        setLayout(null);

        // FUNDO
        jPanel6.setBackground(new java.awt.Color(238, 243, 249));
        jPanel6.setBounds(0, 0, 400, 330);
        jPanel6.setLayout(null);
        add(jPanel6);

        // TOPO
        jPanel2.setBackground(new java.awt.Color(179, 40, 36));
        jPanel2.setBounds(0, 0, 400, 40);
        jPanel2.setLayout(null);
        jPanel6.add(jPanel2);

        // LOGO
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Quizmica 230px.png")));
        jLabel9.setBounds(-40, 0, 150, 50);
        jPanel2.add(jLabel9);

        // TITULO
        jLabel1.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("Painel do aluno");
        jLabel1.setBounds(160, 10, 150, 20);
        jPanel2.add(jLabel1);

        // BOTAO SAIR
        jButton2.setText("Sair");
        jButton2.setBackground(java.awt.Color.WHITE);
        jButton2.setForeground(new java.awt.Color(179, 40, 36));
        jButton2.setFocusPainted(false);
        jButton2.setBounds(330, 10, 50, 20);
        jPanel2.add(jButton2);

        // TITULO QUIZZES
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel4.setText("Seus quizzes:");
        jLabel4.setBounds(20, 60, 120, 20);
        jPanel6.add(jLabel4);

        // LINHA
        jPanel4.setBackground(new java.awt.Color(179, 40, 36));
        jPanel4.setBounds(20, 75, 80, 2);
        jPanel6.add(jPanel4);

        // TITULO DESEMPENHO
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel3.setText("Seu desempenho:");
        jLabel3.setBounds(230, 60, 140, 20);
        jPanel6.add(jLabel3);

        // LINHA
        jPanel11.setBackground(new java.awt.Color(179, 40, 36));
        jPanel11.setBounds(230, 75, 100, 2);
        jPanel6.add(jPanel11);

        // DIVISORIA
        jPanel3.setBackground(new java.awt.Color(120, 120, 120));
        jPanel3.setBounds(200, 60, 2, 220);
        jPanel6.add(jPanel3);

        // QUIZ 1
        jPanel8.setBackground(new java.awt.Color(230, 230, 230));
        jPanel8.setBounds(20, 90, 60, 70);
        jLabel11.setText("Experimento");
        jPanel8.add(jLabel11);
        jPanel6.add(jPanel8);

        // QUIZ 2
        jPanel9.setBackground(new java.awt.Color(230, 230, 230));
        jPanel9.setBounds(120, 90, 60, 70);
        jLabel10.setText("Experimento");
        jPanel9.add(jLabel10);
        jPanel6.add(jPanel9);

        // QUIZ 3
        jPanel5.setBackground(new java.awt.Color(230, 230, 230));
        jPanel5.setBounds(20, 190, 60, 70);
        jLabel12.setText("Experimento");
        jPanel5.add(jLabel12);
        jPanel6.add(jPanel5);

        // QUIZ 4
        jPanel7.setBackground(new java.awt.Color(230, 230, 230));
        jPanel7.setBounds(120, 190, 60, 70);
        jLabel13.setText("Experimento");
        jPanel7.add(jLabel13);
        jPanel6.add(jPanel7);

        // BOTAO 1
        configurarBotao(jButton4);
        jButton4.setBounds(20, 160, 60, 20);
        jPanel6.add(jButton4);

        // BOTAO 2
        configurarBotao(jButton5);
        jButton5.setBounds(120, 160, 60, 20);
        jPanel6.add(jButton5);

        // BOTAO 3
        configurarBotao(jButton3);
        jButton3.setBounds(20, 260, 60, 20);
        jPanel6.add(jButton3);

        // BOTAO 4
        configurarBotao(jButton1);
        jButton1.setBounds(120, 260, 60, 20);
        jPanel6.add(jButton1);


        // PAINEL DESEMPENHO
        jPanel10.setBackground(java.awt.Color.WHITE);
        jPanel10.setBounds(230, 100, 140, 170);
        jPanel10.setLayout(null);
        jPanel6.add(jPanel10);

        // ACERTOS
        jLabel6.setText("Acertos:");
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel6.setBounds(10, 10, 100, 20);
        jPanel10.add(jLabel6);

        jProgressBar1.setBounds(10, 35, 90, 15);
        jProgressBar1.setOpaque(true);
        jProgressBar1.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        jProgressBar1.setValue(50);
        jProgressBar1.setForeground(new java.awt.Color(141, 212, 135));
        jProgressBar1.setBackground(new java.awt.Color(220, 220, 220));
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setMaximum(100);
        jPanel10.add(jProgressBar1);

        // TEXTO %
        lblAcertosValor = new javax.swing.JLabel();
        lblAcertosValor.setText("50%");
        lblAcertosValor.setFont(new java.awt.Font("Arial", 1, 10));
        lblAcertosValor.setBounds(105, 32, 40, 20);
        jPanel10.add(lblAcertosValor);

        // ERROS
        jLabel8.setText("Erros:");
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel8.setBounds(10, 60, 100, 20);
        jPanel10.add(jLabel8);

        jProgressBar3.setBounds(10, 85, 90, 15);
        jProgressBar3.setOpaque(true);
        jProgressBar3.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        jProgressBar3.setValue(30);
        jProgressBar3.setForeground(new java.awt.Color(224, 55, 40));
        jProgressBar3.setBackground(new java.awt.Color(220, 220, 220));
        jProgressBar3.setBorderPainted(false);
        jProgressBar3.setMaximum(100);
        jPanel10.add(jProgressBar3);

        // TEXTO %
        lblErrosValor = new javax.swing.JLabel();
        lblErrosValor.setText("30%");
        lblErrosValor.setFont(new java.awt.Font("Arial", 1, 10));
        lblErrosValor.setBounds(105, 82, 40, 20);
        jPanel10.add(lblErrosValor);

        // APROVEITAMENTO

        jLabel7.setText("Aproveitamento:");
        jLabel7.setFont(new java.awt.Font("Arial", 1, 12));
        jLabel7.setBounds(10, 110, 120, 20);
        jPanel10.add(jLabel7);

        jProgressBar2.setBounds(10, 135, 90, 15);
        jProgressBar2.setOpaque(true);
        jProgressBar2.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        jProgressBar2.setValue(70);
        jProgressBar2.setForeground(new java.awt.Color(51, 79, 242));
        jProgressBar2.setBackground(new java.awt.Color(220, 220, 220));
        jProgressBar2.setBorderPainted(false);
        jProgressBar2.setMaximum(100);
        jPanel10.add(jProgressBar2);


        // TEXTO %
        lblAproveitamentoValor = new javax.swing.JLabel();
        lblAproveitamentoValor.setText("70%");
        lblAproveitamentoValor.setFont(new java.awt.Font("Arial", 1, 10));
        lblAproveitamentoValor.setBounds(105, 132, 40, 20);
        jPanel10.add(lblAproveitamentoValor);

        // LOGO ETEC
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/etec.png")));
        jLabel5.setBounds(10, 270, 40, 30);

        jPanel6.add(jLabel5);
    }

    private void configurarBotao(javax.swing.JButton botao) {
        botao.setText("Participar");
        botao.setBackground(new java.awt.Color(25, 72, 85));
        botao.setForeground(java.awt.Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);
        botao.setFont(new java.awt.Font("Arial", 0, 8));
    }

    // GETTERS
    public javax.swing.JProgressBar getProgressAcertos() {
        return jProgressBar1;
    }
    public javax.swing.JProgressBar getProgressErros() {
        return jProgressBar3;
    }
    public javax.swing.JProgressBar getProgressAproveitamento() {
        return jProgressBar2;
    }
    public javax.swing.JButton getBtnQuiz1() {
        return jButton1;
    }
    public javax.swing.JButton getBtnQuiz2() {
        return jButton3;
    }
    public javax.swing.JButton getBtnQuiz3() {
        return jButton4;
    }
    public javax.swing.JButton getBtnQuiz4() {
        return jButton5;
    }
    public javax.swing.JButton getBtnSair() {
        return jButton2;
    }
    public javax.swing.JLabel getLblAcertosValor() {
    return lblAcertosValor;
    }
    public javax.swing.JLabel getLblErrosValor() {
        return lblErrosValor;
    }
    public javax.swing.JLabel getLblAproveitamentoValor() {
        return lblAproveitamentoValor;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DashboardAluno tela = new DashboardAluno();
                new AlunoController(tela);
                tela.setVisible(true);
            }
        });
    }

    // VARIABLES
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JLabel lblAcertosValor;
    private javax.swing.JLabel lblErrosValor;
    private javax.swing.JLabel lblAproveitamentoValor;
}