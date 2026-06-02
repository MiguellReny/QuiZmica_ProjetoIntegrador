package quizquimica.view;

import javax.swing.JOptionPane;
import quizquimica.model.Usuario;
import quizquimica.service.AuthService;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.controller.DashboardAlunoNovoController;
import quizquimica.model.Professor;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
        jButton1.setOpaque(true);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(230, 240, 251));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("DecoType Naskh", 2, 24)); // NOI18N
        jLabel4.setText("Bem vindo(a) ao QuiZmica!");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 550, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(179, 40, 36));
        jPanel4.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(
            new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/email 1.png"))
            .getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)
        ));
        jLabel1.setText("E-mail:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jPanel5.setBackground(new java.awt.Color(179, 40, 36));
        jPanel5.setPreferredSize(new java.awt.Dimension(10, 3));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 0, 20));

        jTextField1.setBackground(new java.awt.Color(204, 222, 231));
        jTextField1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTextField1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 430, 50));

        jLabel3.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon(
            new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/locked-computer 1.png"))
            .getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)
        ));
        jLabel3.setText("Senha:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jTextField2.setBackground(new java.awt.Color(204, 222, 231));
        jTextField2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTextField2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 430, 50));

        jButton1.setBackground(new java.awt.Color(179, 40, 36));
        jButton1.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ENTRAR");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 190, 57));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 470, 380));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/quizmica.png"))); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -30, 1130, 330));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String loginUsuario = jTextField1.getText();
        String senhaUsuario = jTextField2.getText();
        AuthService authService = new AuthService();
        Usuario usuario = authService.login(loginUsuario, senhaUsuario);
        if (usuario != null) {
            if (usuario instanceof Professor) {
                TelaProfessor professor = new TelaProfessor();
                professor.setVisible(true);
            } else {
                DashboardAlunoNovo aluno = new DashboardAlunoNovo();
                new DashboardAlunoNovoController(aluno);
                aluno.setVisible(true);
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Login ou senha inválidos");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}