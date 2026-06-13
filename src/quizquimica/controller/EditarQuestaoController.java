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
        view.getComboPersonagem().setSelectedItem(
            converterParaExibicao(questao.getPersonagem() != null ? questao.getPersonagem() : "mendeleev")
        );
        view.getTxtEnunciado().setText(questao.getEnunciado());
        view.getTxtDica().setText(questao.getDica() != null ? questao.getDica() : "");
        view.getComboDificuldade().setSelectedItem(
            converterDificuldadeExibicao(questao.getDificuldade())
        );
        atualizarContador();

        imagemEnunciadoUrl = questao.getImagemUrl();

        List<Alternativa> alts = questao.getAlternativas();
        if (alts != null && alts.size() >= 4) {
            view.getTxtAlternativaA().setText(alts.get(0).getAlternativa());
            view.getTxtAlternativaB().setText(alts.get(1).getAlternativa());
            view.getTxtAlternativaC().setText(alts.get(2).getAlternativa());
            view.getTxtAlternativaD().setText(alts.get(3).getAlternativa());

            imagemAltAUrl = alts.get(0).getAlternativaImagem();
            imagemAltBUrl = alts.get(1).getAlternativaImagem();
            imagemAltCUrl = alts.get(2).getAlternativaImagem();
            imagemAltDUrl = alts.get(3).getAlternativaImagem();

            if (alts.get(0).isAlternativaCorreta())      view.getComboResposta().setSelectedItem("Alternativa A");
            else if (alts.get(1).isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa B");
            else if (alts.get(2).isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa C");
            else if (alts.get(3).isAlternativaCorreta()) view.getComboResposta().setSelectedItem("Alternativa D");
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

        view.getLblImagemEnunciado().setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemEnunciado().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem do Enunciado");
                if (url != null) { imagemEnunciadoUrl = url; mostrarAvisoImagem(); }
            }
        });

        view.getLblImagemAltA().setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltA().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa A");
                if (url != null) { imagemAltAUrl = url; mostrarAvisoImagem(); }
            }
        });

        view.getLblImagemAltB().setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltB().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa B");
                if (url != null) { imagemAltBUrl = url; mostrarAvisoImagem(); }
            }
        });

        view.getLblImagemAltC().setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltC().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa C");
                if (url != null) { imagemAltCUrl = url; mostrarAvisoImagem(); }
            }
        });

        view.getLblImagemAltD().setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltD().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa D");
                if (url != null) { imagemAltDUrl = url; mostrarAvisoImagem(); }
            }
        });
    }

    private String pedirUrlImagem(String titulo) {
        String url = JOptionPane.showInputDialog(view,
                "⚠️ Cole aqui o link do Google Drive.\nAtenção: o arquivo deve estar compartilhado como 'Qualquer pessoa com o link'.",
                titulo, JOptionPane.PLAIN_MESSAGE);
        return (url != null && !url.isBlank()) ? url.trim() : null;
    }

    private void mostrarAvisoImagem() {
        java.awt.Frame frame = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(view);
        new PopUpImagem(frame, true).setVisible(true);
    }

    private void atualizarContador() {
        int tam = view.getTxtEnunciado().getText().length();
        view.getLblContadorEnunciado().setText(tam + "/500");
    }

    private String converterParaBanco(String exibido) {
        switch (exibido) {
            case "Dmitri Mendeleev":  return "mendeleev";
            case "Ernest Rutherford": return "rutherford";
            case "Marie Curie":       return "curie";
            case "Rosalind Franklin": return "franklin";
            default:                  return "mendeleev";
        }
    }

    private String converterParaExibicao(String banco) {
        switch (banco) {
            case "mendeleev":  return "Dmitri Mendeleev";
            case "rutherford": return "Ernest Rutherford";
            case "curie":      return "Marie Curie";
            case "franklin":   return "Rosalind Franklin";
            default:           return "Dmitri Mendeleev";
        }
    }

    private String converterDificuldade(String exibido) {
        System.out.println("[DEBUG] dificuldade exibida no combo: '" + exibido + "'");
        switch (exibido) {
            case "Fácil":   return "FACIL";
            case "Médio":   return "MEDIO";
            case "Dificil": return "DIFICIL";
            default:        return "FACIL";
        }
    }

    private String converterDificuldadeExibicao(String banco) {
        switch (banco) {
            case "FACIL":   return "Fácil";
            case "MEDIO":   return "Médio";
            case "DIFICIL": return "Dificil";
            default:        return "Fácil";
        }
    }

    private void salvarEdicao() {
        String enunciado         = view.getTxtEnunciado().getText().trim();
        String dica              = view.getTxtDica().getText().trim();
        String dificuldade       = converterDificuldade((String) view.getComboDificuldade().getSelectedItem());
        System.out.println("[DEBUG] dificuldade a salvar: " + dificuldade);
        String altA              = view.getTxtAlternativaA().getText().trim();
        String altB              = view.getTxtAlternativaB().getText().trim();
        String altC              = view.getTxtAlternativaC().getText().trim();
        String altD              = view.getTxtAlternativaD().getText().trim();
        String corretaSelecionada = (String) view.getComboResposta().getSelectedItem();
        String personagemExibido  = (String) view.getComboPersonagem().getSelectedItem();
        String personagem         = converterParaBanco(personagemExibido);
        System.out.println("[DEBUG] personagem a salvar: " + personagem);

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
        questao.setImagemUrl(ConversorImagemUrl.converter(imagemEnunciadoUrl));
        questao.setPersonagem(personagem);

        List<Alternativa> alternativas = new ArrayList<>();
        Alternativa a1 = new Alternativa(); a1.setAlternativa(altA); a1.setAlternativaCorreta(corretaSelecionada.equals("Alternativa A")); a1.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltAUrl));
        Alternativa a2 = new Alternativa(); a2.setAlternativa(altB); a2.setAlternativaCorreta(corretaSelecionada.equals("Alternativa B")); a2.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltBUrl));
        Alternativa a3 = new Alternativa(); a3.setAlternativa(altC); a3.setAlternativaCorreta(corretaSelecionada.equals("Alternativa C")); a3.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltCUrl));
        Alternativa a4 = new Alternativa(); a4.setAlternativa(altD); a4.setAlternativaCorreta(corretaSelecionada.equals("Alternativa D")); a4.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltDUrl));
        alternativas.add(a1); alternativas.add(a2); alternativas.add(a3); alternativas.add(a4);
        questao.setAlternativas(alternativas);

        boolean ok = questaoService.editarQuestao(questao);
        if (ok) {
            JOptionPane.showMessageDialog(view, "Questão atualizada com sucesso!");
            voltarDashboard();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao atualizar questão.");
        }
    }

    private void voltarDashboard() {
        quizquimica.util.CacheQuestoes.getInstance().invalidar();
        new DashboardProfessor().setVisible(true);
        view.dispose();
    }

}