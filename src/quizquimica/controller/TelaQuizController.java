package quizquimica.controller;

import javax.swing.JOptionPane;
import quizquimica.view.PopUpDicaQuiz;
import quizquimica.view.TelaQuiz;

public class TelaQuizController {

    private final TelaQuiz view;

    private int questaoAtual = 1;
    private final int totalQuestoes = 20;

    public TelaQuizController(TelaQuiz view) {
        this.view = view;

        carregarQuestaoExemplo();
        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnDica().addActionListener(e -> abrirDica());

        view.getBtnVoltar().addActionListener(e -> voltar());

        view.getBtnProxima().addActionListener(e -> proximaQuestao());

        view.getBtnAlternativaA().addActionListener(e -> selecionarAlternativa("A"));
        view.getBtnAlternativaB().addActionListener(e -> selecionarAlternativa("B"));
        view.getBtnAlternativaC().addActionListener(e -> selecionarAlternativa("C"));
        view.getBtnAlternativaD().addActionListener(e -> selecionarAlternativa("D"));
    }

    private void carregarQuestaoExemplo() {
        view.getLblQuestaoAtual().setText("QUESTÃO " + questaoAtual);
        view.getLblProgresso().setText(questaoAtual + " / " + totalQuestoes);

        view.getLblEnunciado().setText("Qual material é utilizado para aquecer sólidos?");

        view.getBtnAlternativaA().setText("Bico de Bunsen");
        view.getBtnAlternativaB().setText("Béquer");
        view.getBtnAlternativaC().setText("Funil de decantação");
        view.getBtnAlternativaD().setText("Tubo de ensaio");
    }

    private void abrirDica() {
        PopUpDicaQuiz popup = new PopUpDicaQuiz(view, true);

        popup.setDadosDica(
            "MARIE CURIE",
            "Pioneira da radioatividade",
            "“Esse material é usado para aquecer substâncias em laboratório.”"
        );

        popup.setVisible(true);
    }

    private void selecionarAlternativa(String alternativa) {
        JOptionPane.showMessageDialog(
            view,
            "Você selecionou a alternativa " + alternativa + ".",
            "Alternativa selecionada",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void proximaQuestao() {
        if (questaoAtual < totalQuestoes) {
            questaoAtual++;
            carregarQuestaoExemplo();
        } else {
            JOptionPane.showMessageDialog(
                view,
                "Quiz finalizado!",
                "Fim do Quiz",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void voltar() {
        view.dispose();
    }
}