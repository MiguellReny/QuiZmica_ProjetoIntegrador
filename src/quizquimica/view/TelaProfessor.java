package quizquimica.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author caiod
 */
public class TelaProfessor extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaProfessor.class.getName());

    /**
     * Creates new form TelaProfessor
     */
    public TelaProfessor() {
        initComponents();
        setSize(935, 680);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new java.awt.Color(230, 240, 251));

        // HEADER
        javax.swing.JPanel header = new javax.swing.JPanel(null);
        header.setBackground(new java.awt.Color(179, 40, 36));
        header.setBounds(0, 0, 935, 70);
        javax.swing.JLabel logo = new javax.swing.JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/Quizmica 230px.png")));
        logo.setBounds(0, 5, 210, 60);
        header.add(logo);
        javax.swing.JLabel titulo = new javax.swing.JLabel("Painel do professor");
        titulo.setForeground(java.awt.Color.WHITE);
        titulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        titulo.setBounds(380, 20, 280, 30);
        header.add(titulo);
        javax.swing.JButton btnSair = new javax.swing.JButton("Sair");
        btnSair.setBounds(860, 20, 55, 28);
        btnSair.setForeground(new java.awt.Color(179, 40, 36));
        btnSair.addActionListener(e -> dispose());
        header.add(btnSair);
        add(header);

        // COLUNA ESQUERDA
        javax.swing.JLabel lblTodas = new javax.swing.JLabel("Todas as questões");
        lblTodas.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        lblTodas.setBounds(20, 85, 220, 25);
        add(lblTodas);
        javax.swing.JPanel linhaEsq = new javax.swing.JPanel();
        linhaEsq.setBackground(new java.awt.Color(179, 40, 36));
        linhaEsq.setBounds(20, 113, 80, 3);
        add(linhaEsq);
        javax.swing.JTextField campoBusca = new javax.swing.JTextField("Buscar questões....");
        campoBusca.setBounds(20, 125, 455, 32);
        add(campoBusca);

        // LISTA COM 6 LINHAS
        javax.swing.JPanel pnlLista = new javax.swing.JPanel(new java.awt.GridLayout(6, 1, 0, 2));
        pnlLista.setBackground(java.awt.Color.WHITE);
        for (int i = 0; i < 6; i++) {
            javax.swing.JPanel linha = new javax.swing.JPanel(new java.awt.BorderLayout());
            linha.setBackground(new java.awt.Color(235, 240, 245));
            linha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(200, 205, 210)));
            javax.swing.JButton btnEditar = new javax.swing.JButton("Editar");
            btnEditar.setBackground(java.awt.Color.WHITE);
            btnEditar.setForeground(new java.awt.Color(50, 70, 100));
            btnEditar.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 11));
            btnEditar.setPreferredSize(new java.awt.Dimension(80, 25));
            linha.add(btnEditar, java.awt.BorderLayout.EAST);
            pnlLista.add(linha);
        }
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(pnlLista);
        scroll.setBounds(20, 170, 455, 390);
        add(scroll);

        // SEPARADOR
        javax.swing.JSeparator sep = new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL);
        sep.setBounds(500, 80, 2, 510);
        add(sep);

        // COLUNA DIREITA
        javax.swing.JLabel lblErradas = new javax.swing.JLabel("Questões mais erradas");
        lblErradas.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        lblErradas.setBounds(530, 85, 280, 25);
        add(lblErradas);
        javax.swing.JPanel linhaDir = new javax.swing.JPanel();
        linhaDir.setBackground(new java.awt.Color(179, 40, 36));
        linhaDir.setBounds(530, 113, 80, 3);
        add(linhaDir);

        // CARD
        javax.swing.JPanel card = new javax.swing.JPanel(null);
        card.setBackground(java.awt.Color.WHITE);
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        card.setBounds(530, 130, 380, 310);
        javax.swing.JLabel q1 = new javax.swing.JLabel("1. O que é um catalisador?");
        q1.setBounds(15, 20, 280, 20);
        card.add(q1);
        javax.swing.JLabel p1 = new javax.swing.JLabel("73%");
        p1.setForeground(new java.awt.Color(200, 50, 50));
        p1.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        p1.setBounds(330, 20, 40, 20);
        card.add(p1);
        javax.swing.JProgressBar pb1 = new javax.swing.JProgressBar(0, 100);
        pb1.setValue(73);
        pb1.setForeground(new java.awt.Color(200, 50, 50));
        pb1.setBorderPainted(false);
        pb1.setBounds(15, 44, 355, 12);
        card.add(pb1);
        javax.swing.JLabel q2 = new javax.swing.JLabel("2. Qual o nome do material a seguir?");
        q2.setBounds(15, 75, 280, 20);
        card.add(q2);
        javax.swing.JLabel p2 = new javax.swing.JLabel("62%");
        p2.setForeground(new java.awt.Color(220, 140, 30));
        p2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        p2.setBounds(330, 75, 40, 20);
        card.add(p2);
        javax.swing.JProgressBar pb2 = new javax.swing.JProgressBar(0, 100);
        pb2.setValue(62);
        pb2.setForeground(new java.awt.Color(220, 140, 30));
        pb2.setBorderPainted(false);
        pb2.setBounds(15, 99, 355, 12);
        card.add(pb2);
        javax.swing.JLabel q3 = new javax.swing.JLabel("3. Qual desses materiais é utilizado na filtração?");
        q3.setBounds(15, 130, 300, 20);
        card.add(q3);
        javax.swing.JLabel p3 = new javax.swing.JLabel("58%");
        p3.setForeground(new java.awt.Color(200, 180, 30));
        p3.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        p3.setBounds(330, 130, 40, 20);
        card.add(p3);
        javax.swing.JProgressBar pb3 = new javax.swing.JProgressBar(0, 100);
        pb3.setValue(58);
        pb3.setForeground(new java.awt.Color(200, 180, 30));
        pb3.setBorderPainted(false);
        pb3.setBounds(15, 154, 355, 12);
        card.add(pb3);
        javax.swing.JPanel balao = new javax.swing.JPanel(new java.awt.BorderLayout());
        balao.setBackground(new java.awt.Color(255, 252, 220));
        balao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 210, 150)));
        balao.setBounds(15, 185, 230, 55);
        javax.swing.JLabel dica = new javax.swing.JLabel("<html>💡 Aqui estão as questões que seus alunos mais erram. Que tal revisar esses temas?</html>");
        dica.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));
        balao.add(dica, java.awt.BorderLayout.CENTER);
        card.add(balao);
        add(card);

        // RODAPÉ
        javax.swing.JLabel etec = new javax.swing.JLabel(new javax.swing.ImageIcon(getClass().getResource("/images/etec.png")));
        etec.setBounds(20, 590, 80, 50);
        add(etec);
        javax.swing.JButton btnNova = new javax.swing.JButton("NOVA QUESTÃO");
        btnNova.setBounds(350, 595, 190, 40);
        btnNova.setBackground(new java.awt.Color(44, 74, 82));
        btnNova.setForeground(java.awt.Color.WHITE);
        btnNova.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        add(btnNova);

        // MASCOTE EINSTEIN
        javax.swing.JLabel einstein = new javax.swing.JLabel(new javax.swing.ImageIcon(
                new javax.swing.ImageIcon(getClass().getResource("/images/Einsten.png"))
                        .getImage().getScaledInstance(120, 180, java.awt.Image.SCALE_SMOOTH)));
        einstein.setBounds(790, 460, 120, 180);
        add(einstein);

        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaProfessor().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
