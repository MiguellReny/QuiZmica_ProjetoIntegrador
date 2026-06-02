package quizquimica.view;

import quizquimica.controller.JogarController;
import javax.swing.*;
import java.awt.*;

public class TelaJogar extends JFrame {

    public TelaJogar() {
        initComponents();
        setLocationRelativeTo(null);
            // para corrigir as cores dos botões
    BotaoJogar.setOpaque(true);
    BotaoJogar.setBorderPainted(false);
    BotaoJogar.setFocusPainted(false);
 

    BotaoProfessor.setOpaque(true);
    BotaoProfessor.setBorderPainted(false);
    BotaoProfessor.setFocusPainted(false);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        BotaoJogar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        BotaoProfessor = new javax.swing.JButton();
        TermoUso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(238, 243, 249));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/quizmicaPgInicial.png"))); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -30, 1130, 420));
        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 2, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Misture ideias. Descubra respostas.");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 760, 100));

        BotaoJogar.setBackground(new java.awt.Color(179, 40, 36));
        BotaoJogar.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        BotaoJogar.setForeground(new java.awt.Color(255, 255, 255));
        BotaoJogar.setText("JOGAR");
        BotaoJogar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        BotaoJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoJogarActionPerformed(evt);
            }
        });
        jPanel3.add(BotaoJogar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 490, 80));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/etec.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 270, 250));

        BotaoProfessor.setBackground(new java.awt.Color(102, 102, 102));
        BotaoProfessor.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        BotaoProfessor.setForeground(new java.awt.Color(255, 255, 255));
        BotaoProfessor.setText("Acesso do professor");
        jPanel3.add(BotaoProfessor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 20, 300, 40));
        BotaoProfessor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        BotaoProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoProfessorActionPerformed(evt);
            }
        });
        TermoUso.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        TermoUso.setForeground(new java.awt.Color(102, 102, 102));
        TermoUso.setText("Termo de Uso");
        TermoUso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TermoUsoMouseClicked(evt);
            }
        });
        jPanel3.add(TermoUso, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 710, 130, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Cientista pg inicial.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 190, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotaoJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoJogarActionPerformed
        JogarController controller = new JogarController(this);
        controller.abrirLogin();
    }//GEN-LAST:event_BotaoJogarActionPerformed

    private void BotaoProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoProfessorActionPerformed
        JogarController controller = new JogarController(this);
        controller.abrirLogin();
    }//GEN-LAST:event_BotaoProfessorActionPerformed

    private void TermoUsoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TermoUsoMouseClicked
        JogarController controller = new JogarController(this);
        controller.abrirTermos();
    }//GEN-LAST:event_TermoUsoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoJogar;
    private javax.swing.JButton BotaoProfessor;
    private javax.swing.JLabel TermoUso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}