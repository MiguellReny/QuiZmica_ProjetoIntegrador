package quizquimica.view;

import quizquimica.controller.AlunoController;

public class DashboardAluno extends javax.swing.JFrame {

    public DashboardAluno() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jProgressBar6 = new javax.swing.JProgressBar();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Quizmica 2 sem fundo 1.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(238, 243, 249));
        jPanel6.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/quizmicamenor.png"))); // NOI18N
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, -1));
        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -30, -1, -1));

        jPanel12.setBackground(new java.awt.Color(238, 243, 249));
        jPanel12.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(179, 40, 36));
        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 190, 2));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(57, 57, 57));
        jLabel14.setText("Acertos:");
        jPanel14.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jProgressBar4.setForeground(new java.awt.Color(141, 212, 135));
        jPanel14.add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 520, 40));

        jProgressBar5.setForeground(new java.awt.Color(51, 79, 242));
        jPanel14.add(jProgressBar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 520, 40));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(57, 57, 57));
        jLabel15.setText("Aproveitamento:");
        jPanel14.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(57, 57, 57));
        jLabel16.setText("Erros:");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jProgressBar6.setForeground(new java.awt.Color(224, 55, 40));
        jPanel14.add(jProgressBar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 520, 40));

        jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 610, 490));

        jButton6.setBackground(new java.awt.Color(25, 72, 85));
        jButton6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Participar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, 180, 40));

        jButton7.setBackground(new java.awt.Color(25, 72, 85));
        jButton7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Participar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, 180, 40));

        jButton8.setBackground(new java.awt.Color(25, 72, 85));
        jButton8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Participar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 670, 180, 40));

        jPanel15.setBackground(new java.awt.Color(230, 230, 230));
        jPanel15.setPreferredSize(new java.awt.Dimension(180, 200));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Materiais do");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel15.add(jLabel17);

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Laboratório");
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel15.add(jLabel23);

        jPanel12.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 180, 200));

        jPanel16.setBackground(new java.awt.Color(230, 230, 230));

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Experimentos ");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel16.add(jLabel18);

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Químicos");
        jLabel19.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel19.setAutoscrolls(true);
        jPanel16.add(jLabel19);

        jPanel12.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 180, 200));

        jPanel18.setBackground(new java.awt.Color(230, 230, 230));

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Materiais");
        jPanel18.add(jLabel20);

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("de Segurança");
        jPanel18.add(jLabel24);

        jPanel12.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, 180, 200));

        jPanel19.setBackground(new java.awt.Color(102, 102, 102));
        jPanel19.setPreferredSize(new java.awt.Dimension(2, 100));
        jPanel12.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(682, 90, 2, 615));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel21.setText("Seu desempenho:");
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, -1));

        jPanel20.setBackground(new java.awt.Color(179, 40, 36));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Painel do aluno");
        jPanel20.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 510, 40));

        jButton10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(179, 40, 36));
        jButton10.setText("Sair");
        jButton10.setToolTipText("");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 10, 100, 40));

        jPanel12.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 60));
        jPanel12.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -30, -1, -1));

        jPanel11.setBackground(new java.awt.Color(179, 40, 36));
        jPanel12.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 190, 2));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Quizzes:");
        jPanel12.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jPanel6.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void configurarBotao(javax.swing.JButton botao) {
        botao.setText("Participar");
        botao.setBackground(new java.awt.Color(25, 72, 85));
        botao.setForeground(java.awt.Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setOpaque(true);
        botao.setFont(new java.awt.Font("Arial", 0, 8));
    }

    public javax.swing.JProgressBar getProgressAcertos() { return jProgressBar1; }
    public javax.swing.JProgressBar getProgressErros() { return jProgressBar3; }
    public javax.swing.JProgressBar getProgressAproveitamento() { return jProgressBar2; }
    public javax.swing.JButton getBtnQuiz1() { return jButton1; }
    public javax.swing.JButton getBtnQuiz2() { return jButton3; }
    public javax.swing.JButton getBtnQuiz3() { return jButton4; }
    public javax.swing.JButton getBtnQuiz4() { return jButton5; }
    public javax.swing.JButton getBtnSair() { return jButton2; }
    public javax.swing.JLabel getLblAcertosValor() { return lblAcertosValor; }
    public javax.swing.JLabel getLblErrosValor() { return lblErrosValor; }
    public javax.swing.JLabel getLblAproveitamentoValor() { return lblAproveitamentoValor; }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DashboardAluno tela = new DashboardAluno();
                new AlunoController(tela);
                tela.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    // End of variables declaration//GEN-END:variables
}