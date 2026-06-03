package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.EditarQuestao;

public class EditarQuestaoController {

    private final EditarQuestao view;
    private final QuestaoService questaoService = new QuestaoService();
    private final Questao questao;

    public EditarQuestaoController(EditarQuestao view, Questao questao) {
        this.view = view;
        this.questao = questao;
        preencherCampos();
        configurarEventos();
    }

    // ✅ Preenche os campos com os dados da questão existente
    private void preencherCampos() {
        view.getTxtEnunciado().setText(questao.getEnunciado());
        view.getTxtDica().setText(questao.getDica() != null ? questao.getDica() : "");
        view.getComboDificuldade().setSelectedItem(questao.getDificuldade());
        atualizarContador();

        // Preenche as alternativas a partir da lista
        List<Alternativa> alts = questao.getAlternativas();
        if (alts != null && alts.size() >= 4) {
            // As alternativas são salvas na ordem A, B, C, D no banco
            Alternativa altA = alts.get(0);
            Alternativa altB = alts.get(1);
            Alternativa altC = alts.get(2);
            Alternativa altD = alts.get(3);
            view.getTxtAlternativaA().setText(altA.getAlternativa());
            view.getTxtAlternativaB().setText(altB.getAlternativa());
            view.getTxtAlternativaC().setText(altC.getAlternativa());
            view.getTxtAlternativaD().setText(altD.getAlternativa());
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

        // --- Validações ---
        if (enunciado.isBlank()) {
            JOptionPane.showMessageDialog(view, "O enunciado não pode estar vazio.");
            return;
        }
        if (altA.isBlank() || altB.isBlank() || altC.isBlank() || altD.isBlank()) {
            JOptionPane.showMessageDialog(view, "Preencha todas as alternativas.");
            return;
        }

        // --- Atualiza o objeto Questao ---
        questao.setEnunciado(enunciado);
        questao.setDificuldade(dificuldade);
        questao.setDica(dica.isBlank() ? " " : dica);
        questao.setTipo(questao.getTipo() != null ? questao.getTipo() : "textual");

        List<Alternativa> alternativas = new ArrayList<>();
        Alternativa a1 = new Alternativa(); a1.setAlternativa(altA); a1.setAlternativaCorreta(corretaSelecionada.equals("Alternativa A"));
        Alternativa a2 = new Alternativa(); a2.setAlternativa(altB); a2.setAlternativaCorreta(corretaSelecionada.equals("Alternativa B"));
        Alternativa a3 = new Alternativa(); a3.setAlternativa(altC); a3.setAlternativaCorreta(corretaSelecionada.equals("Alternativa C"));
        Alternativa a4 = new Alternativa(); a4.setAlternativa(altD); a4.setAlternativaCorreta(corretaSelecionada.equals("Alternativa D"));
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