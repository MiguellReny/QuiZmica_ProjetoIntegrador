/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package quizquimica.view;

/**
 *
 * @author Angela
 */
public class PopupQuizFinalizado extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(PopupQuizFinalizado.class.getName());

    // FIX 5: label de pontuação adicionado via código (sem alterar o .form)
    private javax.swing.JLabel lblPontuacao;

    public PopupQuizFinalizado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);

        btnRefazerQuiz.setOpaque(true);
        btnRefazerQuiz.setBorderPainted(false);
        btnRefazerQuiz.setFocusPainted(false);

        btnVoltar.setOpaque(true);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);

        // Adiciona label de pontuação abaixo dos erros no cardResultado
        lblPontuacao = new javax.swing.JLabel("Pontuação: 0 pts");
        lblPontuacao.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        lblPontuacao.setForeground(new java.awt.Color(20, 25, 45));
        cardResultado.add(lblPontuacao,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 220, 30));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnRefazerQuiz = new javax.swing.JButton();
        cardResultado = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNomeQuiz = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblAcertos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblErros = new javax.swing.JLabel();
        lblImagemQuimica = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quiz Finalizado");
        setMinimumSize(new java.awt.Dimension(520, 330));
        setModal(true);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setBackground(new java.awt.Color(238, 246, 252));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(520, 330));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(520, 30));
        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setBackground(new java.awt.Color(179, 0, 0));
        lblTitulo.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("QUIZ FINALIZADO!");
        lblTitulo.setOpaque(true);
        panelPrincipal.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 260, 40));

        btnRefazerQuiz.setBackground(new java.awt.Color(179, 0, 0));
        btnRefazerQuiz.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRefazerQuiz.setForeground(java.awt.Color.white);
        btnRefazerQuiz.setText("Refazer Quiz");
        btnRefazerQuiz.setFocusPainted(false);
        panelPrincipal.add(btnRefazerQuiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 150, 30));

        cardResultado.setBackground(java.awt.Color.white);
        cardResultado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(179, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        cardResultado.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 45, 200, 2));

        lblNomeQuiz.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lblNomeQuiz.setForeground(java.awt.Color.darkGray);
        lblNomeQuiz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomeQuiz.setText("Experimentos Químicos");
        cardResultado.add(lblNomeQuiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Symbol", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 100, 230));
        jLabel1.setText("✓");
        cardResultado.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 40, 40));

        lblAcertos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAcertos.setText("10");
        cardResultado.add(lblAcertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("X");
        cardResultado.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 20, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(20, 25, 45));
        jLabel3.setText("Acertos: ");
        cardResultado.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(20, 25, 45));
        jLabel4.setText("Erros:");
        cardResultado.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 50, 30));

        lblErros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblErros.setText("05");
        cardResultado.add(lblErros, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 30, 30));

        lblImagemQuimica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagemQuimica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/finalizadoOFC.png"))); // NOI18N
        cardResultado.add(lblImagemQuimica, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 230, 230));

        btnVoltar.setBackground(new java.awt.Color(25, 72, 85));
        btnVoltar.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        btnVoltar.setForeground(java.awt.Color.white);
        btnVoltar.setText("Voltar");
        cardResultado.add(btnVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 150, 30));

        panelPrincipal.add(cardResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 500, 260));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            PopupQuizFinalizado dialog =
                new PopupQuizFinalizado(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // FIX 5: sobrecarga com pontuação real
    public void setDadosResultado(String nomeQuiz, int acertos, int erros, int pontuacao) {
        lblNomeQuiz.setText(nomeQuiz);
        lblAcertos.setText(String.valueOf(acertos));
        lblErros.setText(String.format("%02d", erros));
        lblPontuacao.setText("Pontuação: " + pontuacao + " pts");
    }

    // Mantém compatibilidade se algum lugar chamar sem pontuação
    public void setDadosResultado(String nomeQuiz, int acertos, int erros) {
        setDadosResultado(nomeQuiz, acertos, erros, 0);
    }

    public javax.swing.JButton getBtnRefazerQuiz() { return btnRefazerQuiz; }
    public javax.swing.JButton getBtnVoltar()       { return btnVoltar; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefazerQuiz;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JPanel cardResultado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAcertos;
    private javax.swing.JLabel lblErros;
    private javax.swing.JLabel lblImagemQuimica;
    private javax.swing.JLabel lblNomeQuiz;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}