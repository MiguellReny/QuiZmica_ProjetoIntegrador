package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.util.ConversorImagemUrl;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.EditarQuestao;
import quizquimica.view.PopUpImagem;

public class EditarQuestaoController {

    private final EditarQuestao view;
    private final QuestaoService questaoService = new QuestaoService();
    private final Questao questao;

    // URLs das imagens — inicializadas com o valor já salvo no banco
    private String imagemEnunciadoUrl;
    private String imagemAltAUrl = null;
    private String imagemAltBUrl = null;
    private String imagemAltCUrl = null;
    private String imagemAltDUrl = null;

    public EditarQuestaoController(EditarQuestao view, Questao questao) {
        this.view = view;
        this.questao = questao;
        preencherCampos();
        configurarEventos();
    }

    private void preencherCampos() {
        view.getTxtEnunciado().setText(questao.getEnunciado());
        view.getTxtDica().setText(questao.getDica() != null ? questao.getDica() : "");
        view.getComboDificuldade().setSelectedItem(questao.getDificuldade());
        atualizarContador();

        // Carrega a URL de imagem já salva no banco
        imagemEnunciadoUrl = questao.getImagemUrl();

        // Carrega as imagens das alternativas já salvas
        List<Alternativa> alts = questao.getAlternativas();
        if (alts != null && alts.size() >= 4) {
            Alternativa altA = alts.get(0);
            Alternativa altB = alts.get(1);
            Alternativa altC = alts.get(2);
            Alternativa altD = alts.get(3);

            view.getTxtAlternativaA().setText(altA.getAlternativa());
            view.getTxtAlternativaB().setText(altB.getAlternativa());
            view.getTxtAlternativaC().setText(altC.getAlternativa());
            view.getTxtAlternativaD().setText(altD.getAlternativa());

            // Carrega URLs existentes das alternativas para não perder ao salvar
            imagemAltAUrl = altA.getAlternativaImagem();
            imagemAltBUrl = altB.getAlternativaImagem();
            imagemAltCUrl = altC.getAlternativaImagem();
            imagemAltDUrl = altD.getAlternativaImagem();

            if (altA.isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa A");
            else if (altB.isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa B");
            else if (altC.isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa C");
            else if (altD.isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa D");
        }
    }

    private void configurarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarEdicao());
        view.getBtnCancelar().addActionListener(e -> voltarDashboard());
        view.getBtnSair().addActionListener(e -> voltarDashboard());

        view.getTxtEnunciado().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { atualizarContador(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { atualizarContador(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { atualizarContador(); }
        });

        // ── Botões de imagem ────────────────────────────────────────────────────

        // Imagem do enunciado
        view.getLblImagemEnunciado().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemEnunciado().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem do Enunciado");
                if (url != null) {
                    imagemEnunciadoUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa A
        view.getLblImagemAltA().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltA().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa A");
                if (url != null) {
                    imagemAltAUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa B
        view.getLblImagemAltB().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltB().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa B");
                if (url != null) {
                    imagemAltBUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa C
        view.getLblImagemAltC().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltC().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa C");
                if (url != null) {
                    imagemAltCUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa D
        view.getLblImagemAltD().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltD().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa D");
                if (url != null) {
                    imagemAltDUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });
    }

    /** Abre um JOptionPane para o professor colar a URL da imagem. */
    private String pedirUrlImagem(String titulo) {
        String url = JOptionPane.showInputDialog(
                view,
                "Cole aqui o link público da imagem (Google Drive, Imgur, etc.):",
                titulo,
                JOptionPane.PLAIN_MESSAGE);
        return (url != null && !url.isBlank()) ? url.trim() : null;
    }

    /** Exibe o PopUpImagem com o aviso sobre manter o arquivo no Google Drive. */
    private void mostrarAvisoImagem() {
        java.awt.Frame frame = (java.awt.Frame)
                javax.swing.SwingUtilities.getWindowAncestor(view);
        new PopUpImagem(frame, true).setVisible(true);
    }

    private void atualizarContador() {
        int tam = view.getTxtEnunciado().getText().length();
        view.getLblContadorEnunciado().setText(tam + "/500");
    }

    private void salvarEdicao() {
        String enunciado   = view.getTxtEnunciado().getText().trim();
        String dica        = view.getTxtDica().getText().trim();
        String dificuldade = (String) view.getComboDificuldade().getSelectedItem();
        String altA = view.getTxtAlternativaA().getText().trim();
        String altB = view.getTxtAlternativaB().getText().trim();
        String altC = view.getTxtAlternativaC().getText().trim();
        String altD = view.getTxtAlternativaD().getText().trim();
        String corretaSelecionada = (String) view.getComboResposta().getSelectedItem();

        if (enunciado.isBlank()) {
            JOptionPane.showMessageDialog(view, "O enunciado não pode estar vazio.");
            return;
        }
        if (altA.isBlank() || altB.isBlank() || altC.isBlank() || altD.isBlank()) {
            JOptionPane.showMessageDialog(view, "Preencha todas as alternativas.");
            return;
        }

        questao.setEnunciado(enunciado);
        questao.setDificuldade(dificuldade);
        questao.setDica(dica.isBlank() ? " " : dica);
        questao.setTipo(questao.getTipo() != null ? questao.getTipo() : "textual");
        questao.setImagemUrl(ConversorImagemUrl.converter(imagemEnunciadoUrl)); // converte link do Drive automaticamente
        // Nota: personagem é apenas visual na tela (combo decorativo);
        // o modelo Questao não possui campo personagem, então não é persistido.

        List<Alternativa> alternativas = new ArrayList<>();

        Alternativa a1 = new Alternativa();
        a1.setAlternativa(altA);
        a1.setAlternativaCorreta(corretaSelecionada.equals("Alternativa A"));
        a1.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltAUrl));

        Alternativa a2 = new Alternativa();
        a2.setAlternativa(altB);
        a2.setAlternativaCorreta(corretaSelecionada.equals("Alternativa B"));
        a2.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltBUrl));

        Alternativa a3 = new Alternativa();
        a3.setAlternativa(altC);
        a3.setAlternativaCorreta(corretaSelecionada.equals("Alternativa C"));
        a3.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltCUrl));

        Alternativa a4 = new Alternativa();
        a4.setAlternativa(altD);
        a4.setAlternativaCorreta(corretaSelecionada.equals("Alternativa D"));
        a4.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltDUrl));

        alternativas.add(a1); alternativas.add(a2); alternativas.add(a3); alternativas.add(a4);
        questao.setAlternativas(alternativas);

        boolean ok = questaoService.atualizarQuestao(questao);
        if (ok) {
            JOptionPane.showMessageDialog(view, "Questão atualizada com sucesso!");
            voltarDashboard();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao atualizar questão.");
        }
    }

    private void voltarDashboard() {
        new DashboardProfessor().setVisible(true);
        view.dispose();
    }
}