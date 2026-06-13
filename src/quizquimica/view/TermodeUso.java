package quizquimica.view;

import quizquimica.model.Usuario;
import quizquimica.controller.TermoController;
import javax.swing.*;
import java.awt.*;

public class TermodeUso extends javax.swing.JFrame {
    private Usuario usuario;


    public TermodeUso() {
    this(null);
    }

    public TermodeUso(Usuario usuario) {
    initComponents();
    setLocationRelativeTo(null);

    this.usuario = usuario;

    jTextArea1.setEditable(false);
    jTextArea1.setFocusable(false);

    javax.swing.SwingUtilities.invokeLater(() -> {
        jScrollPane2.getVerticalScrollBar().setValue(0);
    });

    TermoController controller = new TermoController(this, usuario);

    btnContinuar.addActionListener(e -> {
        controller.aceitarTermos();
    });
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        chkTermos = new javax.swing.JCheckBox();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        //jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Quizmica 2 sem fundo 1.png"))); // NOI18N
        //jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quizmica");
        setBackground(new java.awt.Color(230, 240, 251));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(238, 243, 249));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(238, 243, 249));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("1. Aceitação dos Termos \nAo instalar, acessar ou utilizar o jogo QuiZmica (\"Jogo\"), o usuário declara ter lido, compreendido e concordado com todos os termos e condições estabelecidos neste documento. Caso não concorde com qualquer disposição aqui prevista, o uso do Jogo deve ser imediatamente interrompido. \nPara usuários com idade inferior a 18 (dezoito) anos, a aceitação destes Termos deverá ser realizada pelos pais ou responsáveis legais, que assumem a responsabilidade pelo uso. \n\n2. Descrição do Jogo \nO QuiZmica é um jogo educacional desktop voltado ao ensino de química para estudantes do ensino médio e professores. Seu objetivo é proporcionar uma experiência de aprendizagem interativa e lúdica, complementando o conteúdo curricular da disciplina. \n\n3. Cadastro e Conta de Usuário \n\n3.1 Dados coletados no cadastro \nPara utilizar o Jogo, o usuário deverá criar uma conta fornecendo as seguintes informações: \nNome completo \nEndereço de e-mail válido \nSenha de acesso \nO usuário é o único responsável pela veracidade das informações fornecidas. \n\n3.2 Perfis de usuário \nO Jogo suporta dois tipos de perfil: \nAluno: estudante do ensino médio que utiliza o Jogo para aprendizagem. \nProfessor: docente que utiliza o Jogo como ferramenta pedagógica. \nCada perfil possui funcionalidades e permissões distintas dentro do Jogo. \n\n3.3 Segurança da conta \nO usuário é inteiramente responsável pela confidencialidade de sua senha e por todas as atividades realizadas com sua conta. Em caso de uso não autorizado ou suspeita de violação, o usuário deve comunicar imediatamente a equipe do Jogo. \n\n4. Uso Permitido \nO usuário se compromete a utilizar o Jogo exclusivamente para fins educacionais, respeitando as seguintes diretrizes: \nNão utilizar o Jogo para fins comerciais sem autorização expressa dos desenvolvedores. \nNão tentar burlar, descompilar, modificar ou fazer engenharia reversa do software. \nNão compartilhar sua conta ou credenciais de acesso com terceiros. \nNão inserir dados falsos ou se fazer passar por outra pessoa no cadastro. \nNão utilizar o Jogo de forma que viole leis, regulamentos ou direitos de terceiros. \n\n5. Privacidade e Proteção de Dados \nO Jogo coleta e armazena os dados de cadastro (nome, e-mail e senha) com a única finalidade de identificar o usuário e personalizar sua experiência. Os dados são tratados em conformidade com a Lei Geral de Proteção de Dados Pessoais (LGPD — Lei nº 13.709/2018). \nOs dados pessoais dos usuários não serão compartilhados com terceiros para fins comerciais ou publicitários. Informações poderão ser utilizadas de forma agregada e anonimizada para melhorias no Jogo. \nO usuário poderá, a qualquer momento, solicitar a exclusão de sua conta e de seus dados pessoais por meio dos canais de suporte do Jogo.\n \n6. Propriedade Intelectual \nTodo o conteúdo do Jogo — incluindo, mas não se limitando a, código-fonte, gráficos, textos, sons, personagens e lógica de jogo — é de propriedade exclusiva dos desenvolvedores do Quizmica e está protegido pela legislação brasileira de direitos autorais e propriedade intelectual. \nÉ vedada qualquer reprodução, distribuição, modificação ou uso comercial do conteúdo do Jogo sem autorização prévia e por escrito dos desenvolvedores.\n \n7. Isenção de Responsabilidade \nO Jogo é fornecido \"no estado em que se encontra\", sem garantias de qualquer natureza. Os desenvolvedores não se responsabilizam por: \nInterrupções, falhas técnicas ou indisponibilidade do Jogo. \nDanos causados por uso inadequado ou não autorizado do Jogo. \nPerda de dados decorrente de problemas técnicos ou de segurança. \nO uso do Jogo para fins exclusivamente educacionais é de responsabilidade do usuário e de seus responsáveis legais, quando aplicável. \n\n8. Alterações nos Termos \nOs desenvolvedores reservam-se o direito de modificar estes Termos de Uso a qualquer momento. Alterações relevantes serão comunicadas aos usuários por meio do próprio Jogo ou por e-mail cadastrado. O uso continuado do Jogo após a publicação das alterações implica aceitação dos novos termos. \n\n9. Cancelamento e Exclusão de Conta \nO usuário pode solicitar o cancelamento de sua conta a qualquer momento. Após a exclusão, todos os dados pessoais associados serão removidos dos sistemas, observados os prazos legais de retenção previstos na legislação brasileira. \nOs desenvolvedores se reservam o direito de suspender ou encerrar contas que violem estes Termos de Uso, sem aviso prévio. \n\n10. Legislação Aplicável e Foro \nEstes Termos de Uso são regidos pelas leis da República Federativa do Brasil. Fica eleito o foro da comarca de domicílio do usuário para dirimir quaisquer controvérsias decorrentes deste instrumento, com renúncia expressa a qualquer outro, por mais privilegiado que seja. ");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 25, 1180, 590));

        chkTermos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        chkTermos.setText("Li e aceito os Termos de Uso");
        chkTermos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTermosActionPerformed(evt);
            }
        });
        jPanel1.add(chkTermos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 630, 300, 30));

        btnContinuar.setBackground(new java.awt.Color(227, 227, 227));
        btnContinuar.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnContinuar.setText("Cotinuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        jPanel1.add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 630, 220, 30));

        btnCancelar.setBackground(new java.awt.Color(179, 40, 36));
        btnCancelar.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 630, 220, 30));

        jScrollPane1.setViewportView(jPanel1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 1220, 670));

        jPanel2.setBackground(new java.awt.Color(169, 192, 225));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel6.setText("Termos de Uso");
        jPanel2.add(jLabel6);

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 540, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1366, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkTermosActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {
    }

    public JCheckBox getChkTermos() {
        return chkTermos;
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }

    public JButton getBtnCancelar(){
        return btnCancelar;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TermodeUso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TermodeUso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TermodeUso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TermodeUso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TermodeUso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JCheckBox chkTermos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}