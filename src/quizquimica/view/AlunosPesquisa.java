package quizquimica.view;

public class AlunosPesquisa extends javax.swing.JFrame {

    public AlunosPesquisa() {
        initComponents();
        setLocationRelativeTo(null);

            javax.swing.table.DefaultTableModel model = 
            (javax.swing.table.DefaultTableModel) tabelaAlunos.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Nome", "Email", "Ação", "Ação", "Ação"});
        model.addRow(new Object[]{"Carregando alunos...", "", "", "", ""});

        tabelaAlunos.setRowHeight(40);
        tabelaAlunos.setDefaultEditor(Object.class, null);

        jButton1.setOpaque(true);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setBorder(null);

        jToggleButton1.setOpaque(true);
        jToggleButton1.setBorderPainted(false);
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setBorder(null);

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (jTextField1.getText().equals("Buscar aluno...")) {
                    jTextField1.setText("");
                    jTextField1.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (jTextField1.getText().isBlank()) {
                    jTextField1.setText("Buscar aluno...");
                    jTextField1.setForeground(new java.awt.Color(102, 102, 102));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAlunos = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        labelTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelQuiz = new javax.swing.JLabel();
        labelMedia = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelMelhor = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(238, 243, 249));
        jPanel6.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/quizmicamenor.png"))); // NOI18N
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, -1));

        tabelaAlunos.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        tabelaAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
            },
            new String [] {
                "Nome", "Email", "Ação", "Ação", "Ação"
            }
        ));
        jScrollPane1.setViewportView(tabelaAlunos);
        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 610, 490));

        jTextField1.setBackground(new java.awt.Color(245, 247, 250));
        jTextField1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setText("Buscar aluno...");
        jPanel6.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 610, 40));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTotal.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(179, 40, 36));
        labelTotal.setText("-");
        jPanel10.add(labelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 110, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel6.setText("Quizzes Concluídos");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 20));

        labelQuiz.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        labelQuiz.setForeground(new java.awt.Color(179, 40, 36));
        labelQuiz.setText("-");
        jPanel10.add(labelQuiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 60, 40));

        labelMedia.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        labelMedia.setForeground(new java.awt.Color(179, 40, 36));
        labelMedia.setText("-");
        jPanel10.add(labelMedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 60, 40));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel12.setText("Média Geral da Turma");
        jPanel10.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        labelMelhor.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        labelMelhor.setForeground(new java.awt.Color(179, 40, 36));
        labelMelhor.setText("-");
        jPanel10.add(labelMelhor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 60, 40));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel14.setText("Melhor Média");
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, -1, -1));

        jButton1.setBackground(new java.awt.Color(179, 40, 36));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Adicionar Aluno");
        jPanel10.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 340, 50));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("Resumo da Turma");
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jLabel7.setText("Total de Alunos");
        jPanel10.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, -1));

        jPanel2.setBackground(new java.awt.Color(179, 40, 36));
        jPanel10.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 330));

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 480, 550));
        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -30, -1, -1));

        jPanel20.setBackground(new java.awt.Color(179, 40, 36));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Alunos");
        jPanel20.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 510, 40));

        jToggleButton1.setBackground(new java.awt.Color(179, 40, 36));
        jToggleButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 48)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("☰");
        jToggleButton1.setToolTipText("");
        jToggleButton1.setBorder(null);
        jPanel20.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 100, 60));

        jPanel6.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 75));
        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, 480, 550));

        jPanel11.setBackground(new java.awt.Color(179, 40, 36));
        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 190, 2));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Todos os alunos:");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAlunosMouseClicked
    }//GEN-LAST:event_tabelaAlunosMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlunosPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            AlunosPesquisa tela = new AlunosPesquisa();
            new quizquimica.controller.AlunosPesquisaController(tela);
            tela.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel labelMedia;
    private javax.swing.JLabel labelMelhor;
    private javax.swing.JLabel labelQuiz;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable tabelaAlunos;
    // End of variables declaration//GEN-END:variables

    // Getters para o controller
    public javax.swing.JLabel getLabelTotal()            { return labelTotal; }
    public javax.swing.JLabel getLabelQuiz()             { return labelQuiz; }
    public javax.swing.JLabel getLabelMedia()            { return labelMedia; }
    public javax.swing.JLabel getLabelMelhor()           { return labelMelhor; }
    public javax.swing.JTextField getCampoBusca()        { return jTextField1; }
    public javax.swing.JTable getTabelaAlunos()          { return tabelaAlunos; }
    public javax.swing.JButton getjButton1()             { return jButton1; }
    public javax.swing.JToggleButton getjToggleButton1() { return jToggleButton1; }
}