package quizquimica.view;

public class PopupNivelDesbloqueado extends javax.swing.JDialog {

    public PopupNivelDesbloqueado(java.awt.Frame parent, String nivelDesbloqueado) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
        lblMensagem.setText("Você desbloqueou o nível " + nivelDesbloqueado + "!");
    }

    private void initComponents() {
        painelFundo    = new javax.swing.JPanel();
        lblTitulo      = new javax.swing.JLabel();
        lblMensagem    = new javax.swing.JLabel();
        lblIcone       = new javax.swing.JLabel();
        btnOk          = new javax.swing.JButton();

        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        painelFundo.setBackground(new java.awt.Color(238, 246, 252));
        painelFundo.setPreferredSize(new java.awt.Dimension(420, 250));
        painelFundo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblTitulo.setForeground(new java.awt.Color(179, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("🎉 Parabéns!");
        painelFundo.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 420, 40));

        lblIcone.setFont(new java.awt.Font("Segoe UI Emoji", 0, 48));
        lblIcone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcone.setText("🔓");
        painelFundo.add(lblIcone, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 420, 60));

        lblMensagem.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lblMensagem.setForeground(new java.awt.Color(35, 45, 60));
        lblMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensagem.setText("Você desbloqueou o próximo nível!");
        painelFundo.add(lblMensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 145, 420, 30));

        btnOk.setBackground(new java.awt.Color(179, 0, 0));
        btnOk.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnOk.setForeground(java.awt.Color.WHITE);
        btnOk.setText("OK");
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setOpaque(true);
        btnOk.addActionListener(e -> dispose());
        painelFundo.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 195, 100, 35));

        getContentPane().add(painelFundo,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 250));
        pack();
    }

    private javax.swing.JPanel  painelFundo;
    private javax.swing.JLabel  lblTitulo;
    private javax.swing.JLabel  lblMensagem;
    private javax.swing.JLabel  lblIcone;
    private javax.swing.JButton btnOk;
}