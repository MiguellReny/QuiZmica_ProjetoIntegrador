package quizquimica.view;

import javax.swing.JOptionPane;
import quizquimica.model.Usuario;
import quizquimica.service.AuthService;
import quizquimica.model.Professor;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        login = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        senha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Quizmica 300px.png")));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));

        getContentPane().setLayout(null);

        jPanel3.setBackground(new java.awt.Color(230, 240, 251));
        jPanel3.setLayout(null);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Quizmica 300px.png")));
        jLabel9.setBounds(60, 10, 270, 80);
        jPanel3.add(jLabel9);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11));
        jLabel4.setText("Bem vindo(a) ao QuiZmica!");
        jLabel4.setBounds(150, 70, 170, 20);
        jPanel3.add(jLabel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(179, 40, 36));
        jPanel4.setBounds(0, 0, 180, 3);
        jPanel2.add(jPanel4);

        jLabel1.setFont(new java.awt.Font("Arial", 2, 11));
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/email 1.png")));
        jLabel1.setText("E-mail:");
        jLabel1.setBounds(10, 20, 100, 20);
        jPanel2.add(jLabel1);

        login.setBackground(new java.awt.Color(204, 222, 231));
        login.setFont(new java.awt.Font("Helvetica Neue", 0, 10));
        login.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        login.setBounds(10, 40, 160, 20);
        jPanel2.add(login);

        jLabel3.setFont(new java.awt.Font("Arial", 2, 11));
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/locked-computer 1.png")));
        jLabel3.setText("Senha:");
        jLabel3.setBounds(10, 70, 100, 20);
        jPanel2.add(jLabel3);

        senha.setBackground(new java.awt.Color(204, 222, 231));
        senha.setFont(new java.awt.Font("Helvetica Neue", 0, 10));
        senha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        senha.setBounds(10, 90, 160, 20);
        jPanel2.add(senha);

        jButton1 = new javax.swing.JButton();
        jButton1.setText("ENTRAR");
        jButton1.setBackground(new java.awt.Color(179, 40, 36));
        jButton1.setForeground(java.awt.Color.WHITE);
        jButton1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
        jButton1.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(179, 40, 36)
        )
    );

jButton1.setFocusPainted(false);

jButton1.setContentAreaFilled(true);

jButton1.setOpaque(true);

jButton1.setBounds(55, 125, 70, 25);

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        jPanel2.add(jButton1);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/etec.png")));
        jLabel5.setBounds(10, 145, 100, 30);
        jPanel2.add(jLabel5);

        jPanel2.setBounds(130, 100, 180, 180);
        jPanel3.add(jPanel2);

        jPanel3.setBounds(0, 0, 420, 320);

        getContentPane().add(jPanel3);

        setSize(420, 320);
    }

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {

        String loginUsuario = login.getText();
        String senhaUsuario = senha.getText();
        AuthService authService = new AuthService();
        Usuario usuario =
                authService.login(loginUsuario, senhaUsuario);
        if (usuario != null) {
            if (usuario instanceof Professor) {
                DashboardProfessor professor =
                        new DashboardProfessor();
                professor.setVisible(true);
            } else {
                DashboardAluno aluno =
                        new DashboardAluno();
                aluno.setVisible(true);
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Login ou senha inválidos"
            );
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField login;
    private javax.swing.JTextField senha;
}