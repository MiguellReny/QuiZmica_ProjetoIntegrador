package quizquimica.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.PopUpDicaQuiz;
import quizquimica.view.PopupQuizFinalizado;
import quizquimica.view.TelaQuiz;

public class TelaQuizController {

    private final TelaQuiz view;
    private final String categoriaQuiz;
    private final QuestaoService service = new QuestaoService();

    private List<Questao> questoes = new ArrayList<>();
    private String[] respostasSelecionadas;

    private int indiceQuestaoAtual = 0;

    private final Color COR_PADRAO = new Color(255, 255, 255);
    private final Color COR_SELECIONADA = new Color(179, 40, 36);
    private final Color COR_TEXTO_PADRAO = new Color(20, 25, 45);
    private final Color COR_TEXTO_SELECIONADO = new Color(255, 255, 255);

    public TelaQuizController(TelaQuiz view, String categoriaQuiz) {
        this.view = view;
        this.categoriaQuiz = categoriaQuiz;

        carregarQuestoes();
        respostasSelecionadas = new String[questoes.size()];

        configurarEventos();
        carregarQuestaoAtual();
    }

    private void carregarQuestoes() {
        List<Questao> todas = service.listarTodas();

        for (Questao q : todas) {
            if (q.getTipo() != null && q.getTipo().equalsIgnoreCase(categoriaQuiz)) {
                questoes.add(q);
            }
        }

        if (questoes.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                    "Nenhuma questão encontrada para este quiz.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void configurarEventos() {

        view.getBtnDica().addActionListener(e -> abrirDica());
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.getBtnProxima().addActionListener(e -> proximaQuestao());

        view.getBtnAlternativaA().addActionListener(e -> selecionar("A"));
        view.getBtnAlternativaB().addActionListener(e -> selecionar("B"));
        view.getBtnAlternativaC().addActionListener(e -> selecionar("C"));
        view.getBtnAlternativaD().addActionListener(e -> selecionar("D"));
    }

    private void carregarQuestaoAtual() {

        if (questoes.isEmpty()) return;

        Questao q = questoes.get(indiceQuestaoAtual);

        view.getLblQuestaoAtual().setText("QUESTÃO " + (indiceQuestaoAtual + 1));
        view.getLblProgresso().setText((indiceQuestaoAtual + 1) + " / " + questoes.size());

        view.getLblEnunciado().setText(q.getEnunciado());

        List<Alternativa> alts = q.getAlternativas();

        if (alts.size() >= 4) {
            view.getBtnAlternativaA().setText(alts.get(0).getAlternativa());
            view.getBtnAlternativaB().setText(alts.get(1).getAlternativa());
            view.getBtnAlternativaC().setText(alts.get(2).getAlternativa());
            view.getBtnAlternativaD().setText(alts.get(3).getAlternativa());
        }

        restaurarSelecao();
        atualizarBotaoProxima();
    }

    private void selecionar(String letra) {
        respostasSelecionadas[indiceQuestaoAtual] = letra;

        resetBotoes();

        switch (letra) {
            case "A" -> destacar(view.getBtnAlternativaA());
            case "B" -> destacar(view.getBtnAlternativaB());
            case "C" -> destacar(view.getBtnAlternativaC());
            case "D" -> destacar(view.getBtnAlternativaD());
        }
    }

    private void restaurarSelecao() {
        resetBotoes();

        String r = respostasSelecionadas[indiceQuestaoAtual];
        if (r != null) selecionar(r);
    }

    private void resetBotoes() {
        reset(view.getBtnAlternativaA());
        reset(view.getBtnAlternativaB());
        reset(view.getBtnAlternativaC());
        reset(view.getBtnAlternativaD());
    }

    private void reset(JButton b) {
        b.setBackground(COR_PADRAO);
        b.setForeground(COR_TEXTO_PADRAO);
    }

    private void destacar(JButton b) {
        b.setBackground(COR_SELECIONADA);
        b.setForeground(COR_TEXTO_SELECIONADO);
    }

    private void proximaQuestao() {

        if (respostasSelecionadas[indiceQuestaoAtual] == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma alternativa.");
            return;
        }

        if (indiceQuestaoAtual < questoes.size() - 1) {
            indiceQuestaoAtual++;
            carregarQuestaoAtual();
        } else {
            finalizar();
        }
    }

    private void voltar() {

        if (indiceQuestaoAtual > 0) {
            indiceQuestaoAtual--;
            carregarQuestaoAtual();
            return;
        }

        int r = JOptionPane.showConfirmDialog(view,
                "Sair do quiz?",
                "Sair",
                JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            new DashboardAlunoNovo().setVisible(true);
            view.dispose();
        }
    }

    private void finalizar() {

        int acertos = 0;

        for (int i = 0; i < questoes.size(); i++) {

            Questao q = questoes.get(i);
            String respostaAluno = respostasSelecionadas[i];

            List<Alternativa> alts = q.getAlternativas();

            String correta = "";

            if (!alts.isEmpty()) {
                for (int j = 0; j < alts.size(); j++) {
                    if (alts.get(j).isAlternativaCorreta()) {
                        correta = switch (j) {
                            case 0 -> "A";
                            case 1 -> "B";
                            case 2 -> "C";
                            case 3 -> "D";
                            default -> "";
                        };
                    }
                }
            }

            if (respostaAluno != null && respostaAluno.equals(correta)) {
                acertos++;
            }
        }

        int erros = questoes.size() - acertos;

        PopupQuizFinalizado popup = new PopupQuizFinalizado(view, true);
        popup.setDadosResultado(categoriaQuiz, acertos, erros);

        popup.getBtnVoltar().addActionListener(e -> {
            popup.dispose();
            new DashboardAlunoNovo().setVisible(true);
            view.dispose();
        });

        popup.setVisible(true);
    }

    private void abrirDica() {
        Questao q = questoes.get(indiceQuestaoAtual);

        PopUpDicaQuiz popup = new PopUpDicaQuiz(view, true);

        popup.setDadosDica(
                "Dica",
                "Revisão do tema",
                q.getDica() != null ? q.getDica() : "Sem dica disponível"
        );

        popup.setVisible(true);
    }

    private void atualizarBotaoProxima() {
        if (indiceQuestaoAtual == questoes.size() - 1) {
            view.getBtnProxima().setText("FINALIZAR");
        } else {
            view.getBtnProxima().setText("PRÓXIMA");
        }
    }
}