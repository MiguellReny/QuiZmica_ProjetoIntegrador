package quizquimica.view;

import quizquimica.controller.AdicionarQuestaoController;
import quizquimica.controller.JogarController;
import quizquimica.controller.ProfessorController;

public class DashboardProfessor extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardProfessor.class.getName());

    public DashboardProfessor() {
        initComponents();
        new ProfessorController(this);

        btnNovaQuestao.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaQuestaoActionPerformed(evt);
            }
        });

        tabelaQuestoes.setDefaultEditor(Object.class, null);

        btnNovaQuestao.setOpaque(true);
        btnNovaQuestao.setBorderPainted(false);
        btnNovaQuestao.setFocusPainted(false);

        jToggleButton1.setOpaque(true);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setBorder(null);

        jToggleButton1.addActionListener(e -> {
            MenuProfessor menu = new MenuProfessor(this, true);
            menu.setLocationRelativeTo(this);
            menu.setVisible(true);
        });

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (jTextField1.getText().equals("Buscar questões...")) {
                    jTextField1.setText("");
                    jTextField1.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (jTextField1.getText().isBlank()) {
                    jTextField1.setText("Buscar questões...");
                    jTextField1.setForeground(new java.awt.Color(102, 102, 102));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelFundo = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        painelTopo = new javax.swing.JPanel();
        lblTituloTopo = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        lblTitulo = new javax.swing.JLabel();
        linhaTituloQuizzes = new javax.swing.JPanel();
        linhaDivisoria = new javax.swing.JPanel();
        lblTituloErros = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        linhaTituloDesempenho = new javax.swing.JPanel();
        cardDesempenho = new javax.swing.JPanel();
        lblQuestoesErros = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        lblQuestoesErros1 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        lblQuestoesErros2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnNovaQuestao = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaQuestoes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QuiZmica - Painel do Aluno");
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painelFundo.setBackground(new java.awt.Color(238, 246, 252));
        painelFundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/quizmicamenor.png"))); // NOI18N
        painelFundo.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        painelTopo.setBackground(new java.awt.Color(179, 0, 0));
        painelTopo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloTopo.setFont(new java.awt.Font("Segoe UI", 1, 34)); // NOI18N
        lblTituloTopo.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloTopo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloTopo.setText("Painel do professor");
        painelTopo.add(lblTituloTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 5, 410, 60));

        jToggleButton1.setBackground(new java.awt.Color(179, 40, 36));
        jToggleButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 48)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("☰");
        jToggleButton1.setToolTipText("");
        jToggleButton1.setBorder(null);
        painelTopo.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 100, 60));

        painelFundo.add(painelTopo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 75));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(35, 45, 60));
        lblTitulo.setText("Todas as questões");
        painelFundo.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 300, 40));

        linhaTituloQuizzes.setBackground(new java.awt.Color(179, 0, 0));

        javax.swing.GroupLayout linhaTituloQuizzesLayout = new javax.swing.GroupLayout(linhaTituloQuizzes);
        linhaTituloQuizzes.setLayout(linhaTituloQuizzesLayout);
        linhaTituloQuizzesLayout.setHorizontalGroup(
            linhaTituloQuizzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        linhaTituloQuizzesLayout.setVerticalGroup(
            linhaTituloQuizzesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        painelFundo.add(linhaTituloQuizzes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 70, 2));

        linhaDivisoria.setBackground(new java.awt.Color(150, 160, 170));

        javax.swing.GroupLayout linhaDivisoriaLayout = new javax.swing.GroupLayout(linhaDivisoria);
        linhaDivisoria.setLayout(linhaDivisoriaLayout);
        linhaDivisoriaLayout.setHorizontalGroup(
            linhaDivisoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        linhaDivisoriaLayout.setVerticalGroup(
            linhaDivisoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        painelFundo.add(linhaDivisoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 130, 3, 520));

        lblTituloErros.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTituloErros.setText("Questões mais erradas");
        painelFundo.add(lblTituloErros, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 350, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Einsten.png"))); // NOI18N
        painelFundo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 480, 190, -1));

        linhaTituloDesempenho.setBackground(new java.awt.Color(179, 0, 0));

        javax.swing.GroupLayout linhaTituloDesempenhoLayout = new javax.swing.GroupLayout(linhaTituloDesempenho);
        linhaTituloDesempenho.setLayout(linhaTituloDesempenhoLayout);
        linhaTituloDesempenhoLayout.setHorizontalGroup(
            linhaTituloDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        linhaTituloDesempenhoLayout.setVerticalGroup(
            linhaTituloDesempenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        painelFundo.add(linhaTituloDesempenho, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 70, 2));

        cardDesempenho.setBackground(new java.awt.Color(255, 255, 255));
        cardDesempenho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblQuestoesErros.setBackground(new java.awt.Color(255, 255, 255));
        lblQuestoesErros.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblQuestoesErros.setForeground(new java.awt.Color(35, 45, 60));
        lblQuestoesErros.setText("—");
        cardDesempenho.add(lblQuestoesErros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 510, 35));
        cardDesempenho.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 470, 20));
        cardDesempenho.add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 470, 20));

        lblQuestoesErros1.setBackground(new java.awt.Color(255, 255, 255));
        lblQuestoesErros1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblQuestoesErros1.setForeground(new java.awt.Color(35, 45, 60));
        lblQuestoesErros1.setText("—");
        cardDesempenho.add(lblQuestoesErros1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 510, 35));
        cardDesempenho.add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 470, 20));

        lblQuestoesErros2.setBackground(new java.awt.Color(255, 255, 255));
        lblQuestoesErros2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblQuestoesErros2.setForeground(new java.awt.Color(35, 45, 60));
        lblQuestoesErros2.setText("—");
        cardDesempenho.add(lblQuestoesErros2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 510, 35));

        jPanel2.setBackground(new java.awt.Color(245, 244, 249));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel4.setText("Aqui estão as questões que seus alunos ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 370, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel5.setText("mais erram. Que tal revisar esses temas?");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 350, 30));

        cardDesempenho.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 370, 70));

        jPanel3.setBackground(new java.awt.Color(245, 244, 249));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        cardDesempenho.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 40, 30));

        jPanel4.setBackground(new java.awt.Color(245, 244, 249));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        cardDesempenho.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 30, 20));

        painelFundo.add(cardDesempenho, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 580, 390));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/etecRedimensionada.png"))); // NOI18N
        painelFundo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, 100, 80));

        jTextField1.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setText("Buscar questões...");
        painelFundo.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 600, 40));

        btnNovaQuestao.setBackground(new java.awt.Color(0, 153, 153));
        btnNovaQuestao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnNovaQuestao.setForeground(new java.awt.Color(255, 255, 255));
        btnNovaQuestao.setText("Nova Questão");
        painelFundo.add(btnNovaQuestao, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 680, 250, 60));

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelaQuestoes.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        tabelaQuestoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Questão", "Editar", "Remover", "ID"
            }
        ));
        jScrollPane1.setViewportView(tabelaQuestoes);
        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 410));
        painelFundo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 630, 410));
        getContentPane().add(painelFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovaQuestaoActionPerformed(java.awt.event.ActionEvent evt) {
        AdicionarQuestao tela = new AdicionarQuestao();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new DashboardProfessor().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardDesempenho;
    private javax.swing.JButton btnNovaQuestao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblQuestoesErros;
    private javax.swing.JLabel lblQuestoesErros1;
    private javax.swing.JLabel lblQuestoesErros2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloErros;
    private javax.swing.JLabel lblTituloTopo;
    private javax.swing.JPanel linhaDivisoria;
    private javax.swing.JPanel linhaTituloDesempenho;
    private javax.swing.JPanel linhaTituloQuizzes;
    private javax.swing.JPanel painelFundo;
    private javax.swing.JPanel painelTopo;
    private javax.swing.JTable tabelaQuestoes;
    // End of variables declaration//GEN-END:variables

    // Getters para o controller
    public javax.swing.JButton getBtnNovaQuestao()           { return btnNovaQuestao; }
    public javax.swing.JProgressBar getProgressBar1()        { return jProgressBar1; }
    public javax.swing.JProgressBar getProgressBar2()        { return jProgressBar2; }
    public javax.swing.JProgressBar getProgressBar3()        { return jProgressBar3; }
    public javax.swing.JTextField getCampoBusca()            { return jTextField1; }
    public javax.swing.JTable getTabelaQuestoes()            { return tabelaQuestoes; }
    public javax.swing.JLabel getLblQuestoesErros()          { return lblQuestoesErros; }
    public javax.swing.JLabel getLblQuestoesErros1()         { return lblQuestoesErros1; }
    public javax.swing.JLabel getLblQuestoesErros2()         { return lblQuestoesErros2; }
    public javax.swing.JToggleButton getjToggleButton1()     { return jToggleButton1; }
}