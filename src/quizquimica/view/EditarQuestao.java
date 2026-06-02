package quizquimica.view;

import javax.swing.JOptionPane;
import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.util.Constantes;
import java.util.List;

public class EditarQuestao extends javax.swing.JFrame {

    // ── Conexão com backend ───────────────────────────────────────────────────
    private final QuestaoService questaoService = new QuestaoService();
    private Questao questaoAtual;
    // ─────────────────────────────────────────────────────────────────────────

    // Construtor chamado pelo ProfessorController: new EditarQuestao(q)
    public EditarQuestao(Questao questao) {
        initComponents();
        setLocationRelativeTo(null);
        this.questaoAtual = questao;
        // Substituir itens do ComboBox de dificuldade pelas constantes do banco
        jComboBox6.removeAllItems();
        jComboBox6.addItem(Constantes.nivelFacil);   // "FACIL"
        jComboBox6.addItem(Constantes.nivelMedio);   // "MEDIO"
        jComboBox6.addItem(Constantes.nivelDificil); // "DIFICIL"
        // Preencher campos com os dados da questão vinda do banco
        preencherCampos(questao);
        // Conectar botões SALVAR e CANCELAR (não têm listener no initComponents)
        jButton1.addActionListener(e -> salvarEdicao());
        jButton2.addActionListener(e -> voltarParaProfessor());
    }

    // Construtor vazio mantido para compatibilidade
    public EditarQuestao() {
        initComponents();
        setLocationRelativeTo(null);
        jButton1.addActionListener(e -> salvarEdicao());
        jButton2.addActionListener(e -> voltarParaProfessor());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Quizmica 2 sem fundo 1.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, -30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(179, 40, 36));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EDITAR QUESTÃO");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, -10, -1, 40));

        jButton4.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jButton4.setForeground(new java.awt.Color(179, 40, 36));
        jButton4.setText("Voltar");
        jButton4.setToolTipText("");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 50, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 20));

        jPanel3.setBackground(new java.awt.Color(230, 240, 251));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(179, 40, 36));
        jButton1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SALVAR");
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 100, 20));

        jButton2.setBackground(new java.awt.Color(202, 217, 233));
        jButton2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jButton2.setForeground(new java.awt.Color(41, 115, 196));
        jButton2.setText("CANCELAR");
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 100, 20));

        jPanel5.setBackground(new java.awt.Color(202, 217, 233));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField6.setBackground(new java.awt.Color(232, 238, 241));
        jTextField6.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(102, 102, 102));
        jTextField6.setText("Digite a alternativa D");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 170, 15));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Alternativa D.png"))); // NOI18N
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Alternativa A.png"))); // NOI18N
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Alternativa B.png"))); // NOI18N
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/Alternativa C.png"))); // NOI18N
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jTextField7.setBackground(new java.awt.Color(232, 238, 241));
        jTextField7.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(102, 102, 102));
        jTextField7.setText("Digite a alternativa A");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 170, 15));

        jTextField8.setBackground(new java.awt.Color(232, 238, 241));
        jTextField8.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(102, 102, 102));
        jTextField8.setText("Digite a alternativa B");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 170, 15));

        jTextField9.setBackground(new java.awt.Color(232, 238, 241));
        jTextField9.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(102, 102, 102));
        jTextField9.setText("Digite a alternativa C");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 170, 15));

        jLabel28.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Alternativa correta:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, 30));

        jLabel32.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(160, 0, 0));
        jLabel32.setText("1.");
        jPanel5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 20));

        jLabel33.setFont(new java.awt.Font("Helvetica Neue", 1, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Alternativas");
        jPanel5.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/imagem_1.png"))); // NOI18N
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, 40));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/imagem_1.png"))); // NOI18N
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, 30));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/imagem_1.png"))); // NOI18N
        jPanel5.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, 40));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/imagem_1.png"))); // NOI18N
        jPanel5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, 40));

        jLabel34.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(160, 0, 0));
        jLabel34.setText("1.");
        jPanel5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 20));

        jLabel35.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(160, 0, 0));
        jLabel35.setText("1.");
        jPanel5.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 20));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 280, 120));

        jPanel4.setBackground(new java.awt.Color(202, 217, 233));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Categoria");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Quiz");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, -1, 20));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Imagem do enunciado");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizquimica/images/imagem.png"))); // NOI18N
        jLabel5.setToolTipText("");
        jLabel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 250, 30));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Alternativa correta:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, 30));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Enunciado da questão");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, 20));

        jComboBox5.setBackground(new java.awt.Color(232, 238, 241));
        jComboBox5.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox5.setToolTipText("");
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 15));

        jComboBox6.setBackground(new java.awt.Color(232, 238, 241));
        jComboBox6.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fácil", "Médio", "Dificil" }));
        jComboBox6.setToolTipText("");
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 80, 15));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 9)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Dificulade");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 20));

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(160, 0, 0));
        jLabel17.setText("1.");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 20, 20));

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 10)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Informação da Questão");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 160, 20));

        jComboBox2.setBackground(new java.awt.Color(232, 238, 241));
        jComboBox2.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setToolTipText("");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 80, 15));

        jScrollPane2.setBackground(new java.awt.Color(229, 229, 229));

        jTextArea2.setBackground(new java.awt.Color(232, 238, 241));
        jTextArea2.setColumns(9);
        jTextArea2.setRows(2);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 260, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 280, 140));

        jPanel7.setBackground(new java.awt.Color(202, 217, 233));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(160, 0, 0));
        jLabel20.setText("4.");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 20));

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Personagem");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 170, 40));

        jComboBox3.setBackground(new java.awt.Color(232, 238, 241));
        jComboBox3.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dmitri Mendeleev", "Ernest Rutherford", "Marie Curie", "Rosalind Franklin" }));
        jComboBox3.setToolTipText("");
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel7.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 90, 15));

        jScrollPane1.setBackground(new java.awt.Color(229, 229, 229));

        jTextArea1.setBackground(new java.awt.Color(232, 238, 241));
        jTextArea1.setColumns(6);
        jTextArea1.setRows(1);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 40));

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 1, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Enunciado");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 170, 40));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 130, 110));

        jPanel6.setBackground(new java.awt.Color(202, 217, 233));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(160, 0, 0));
        jLabel16.setText("3.");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 20));

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Alternativa Correta");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 170, 20));

        jComboBox1.setBackground(new java.awt.Color(232, 238, 241));
        jComboBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 10)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(102, 102, 102));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alternativa A", "Alternativa B", "Alternativa C", "Alternativa D" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 15));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 130, 80));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -20, 420, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ── Métodos GEN (manter os comentários GEN-FIRST/LAST) ───────────────────

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        voltarParaProfessor();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
    }//GEN-LAST:event_jComboBox3ActionPerformed

    // ── Lógica de integração com backend ─────────────────────────────────────

    private void preencherCampos(Questao questao) {
        // Enunciado → jTextArea1 (dentro do jScrollPane1 em jPanel7)
        jTextArea1.setText(questao.getEnunciado());

        // Dica (campo de texto livre) → jTextArea2 (dentro do jScrollPane2 em jPanel4)
        jTextArea2.setText(questao.getDica() != null ? questao.getDica() : "");

        // Dificuldade → jComboBox6 ("FACIL", "MEDIO", "DIFICIL")
        jComboBox6.setSelectedItem(questao.getDificuldade());

        // Alternativas → jTextField7=A, jTextField8=B, jTextField9=C, jTextField6=D
        List<Alternativa> alts = questao.getAlternativas();
        if (alts != null && alts.size() >= 4) {
            jTextField7.setText(alts.get(0).getAlternativa());
            jTextField8.setText(alts.get(1).getAlternativa());
            jTextField9.setText(alts.get(2).getAlternativa());
            jTextField6.setText(alts.get(3).getAlternativa());

            // Marcar alternativa correta no jComboBox1
            String[] letras = {"Alternativa A", "Alternativa B", "Alternativa C", "Alternativa D"};
            for (int i = 0; i < alts.size(); i++) {
                if (alts.get(i).isAlternativaCorreta()) {
                    jComboBox1.setSelectedItem(letras[i]);
                    break;
                }
            }
        }
    }

    private void salvarEdicao() {
        if (questaoAtual == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma questão carregada.");
            return;
        }

        String enunciado = jTextArea1.getText().trim();
        if (enunciado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O enunciado não pode estar vazio.");
            return;
        }

        String dica = jTextArea2.getText().trim();
        if (dica.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A dica não pode estar vazia.");
            return;
        }

        String dificuldade = (String) jComboBox6.getSelectedItem();

        questaoAtual.setEnunciado(enunciado);
        questaoAtual.setDica(dica);
        questaoAtual.setDificuldade(dificuldade);

        // Envia para o banco via QuestaoService → QuestaoDAO → UPDATE perguntas
        boolean ok = questaoService.editarQuestao(questaoAtual);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Questão editada com sucesso!");
            voltarParaProfessor();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar alterações.");
        }
    }

    private void voltarParaProfessor() {
        new DashboardProfessor().setVisible(true);
        dispose();
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarQuestao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;