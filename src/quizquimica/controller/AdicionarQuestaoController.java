package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.view.AdicionarQuestao;
import quizquimica.view.DashboardProfessor;

public class AdicionarQuestaoController {

    private final AdicionarQuestao view;
    private final QuestaoService questaoService = new QuestaoService();

    public AdicionarQuestaoController(AdicionarQuestao view) {
        this.view = view;
        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarQuestao());
        view.getBtnCancelar().addActionListener(e -> voltarDashboard());
        view.getBtnSair().addActionListener(e -> voltarDashboard());

        configurarPlaceholder(view.getTxtAlternativaA(), "Digite a alternativa A");
        configurarPlaceholder(view.getTxtAlternativaB(), "Digite a alternativa B");
        configurarPlaceholder(view.getTxtAlternativaC(), "Digite a alternativa C");
        configurarPlaceholder(view.getTxtAlternativaD(), "Digite a alternativa D");

        // Contador de caracteres do enunciado (máx 500)
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

    private void configurarPlaceholder(javax.swing.JTextField campo, String placeholder) {
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isBlank()) {
                    campo.setText(placeholder);
                    campo.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    private void salvarQuestao() {
        String enunciado  = view.getTxtEnunciado().getText().trim();
        String dica       = view.getTxtDica().getText().trim();
        String dificuldade = (String) view.getComboDificuldade().getSelectedItem();
        String altA = view.getTxtAlternativaA().getText().trim();
        String altB = view.getTxtAlternativaB().getText().trim();
        String altC = view.getTxtAlternativaC().getText().trim();
        String altD = view.getTxtAlternativaD().getText().trim();

        // Qual alternativa foi marcada como correta no combo (ex: "Alternativa A")
        String corretaSelecionada = (String) view.getComboResposta().getSelectedItem();

        // --- Validações ---
        if (enunciado.isBlank()) {
            JOptionPane.showMessageDialog(view, "O enunciado não pode estar vazio.");
            return;
        }
        if (altA.isBlank() || altA.equals("Digite a alternativa A") ||
            altB.isBlank() || altB.equals("Digite a alternativa B") ||
            altC.isBlank() || altC.equals("Digite a alternativa C") ||
            altD.isBlank() || altD.equals("Digite a alternativa D")) {
            JOptionPane.showMessageDialog(view, "Preencha todas as alternativas.");
            return;
        }

        // --- Monta a lista de Alternativa ---
        // true = correta, false = incorreta
        List<Alternativa> alternativas = new ArrayList<>();
        Alternativa a1 = new Alternativa(); a1.setAlternativa(altA); a1.setAlternativaCorreta(corretaSelecionada.equals("Alternativa A"));
        Alternativa a2 = new Alternativa(); a2.setAlternativa(altB); a2.setAlternativaCorreta(corretaSelecionada.equals("Alternativa B"));
        Alternativa a3 = new Alternativa(); a3.setAlternativa(altC); a3.setAlternativaCorreta(corretaSelecionada.equals("Alternativa C"));
        Alternativa a4 = new Alternativa(); a4.setAlternativa(altD); a4.setAlternativaCorreta(corretaSelecionada.equals("Alternativa D"));
        alternativas.add(a1);
        alternativas.add(a2);
        alternativas.add(a3);
        alternativas.add(a4);

        // --- Monta a Questao ---
        Questao q = new Questao();
        q.setEnunciado(enunciado);
        q.setDificuldade(dificuldade);
        q.setDica(dica.isBlank() ? " " : dica);
        q.setTipo("textual");
        q.setImagemUrl(null);
        q.setAlternativas(alternativas);

        boolean ok = questaoService.salvarQuestao(q);
        if (ok) {
            JOptionPane.showMessageDialog(view, "Questão adicionada com sucesso!");
            voltarDashboard();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao salvar questão. Tente novamente.");
        }
    }

    private void voltarDashboard() {
        new DashboardProfessor().setVisible(true);
        view.dispose();
    }
}