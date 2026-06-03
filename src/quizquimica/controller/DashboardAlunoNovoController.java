package quizquimica.controller;

import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.TelaLogin;
import quizquimica.view.TelaQuiz;

public class DashboardAlunoNovoController {

    private final DashboardAlunoNovo view;

    public static final String QUIZ_EXPERIMENTOS = "Experimentos Químicos";
    public static final String QUIZ_MATERIAIS = "Materiais do laboratório";
    public static final String QUIZ_SEGURANCA = "Equipamentos de segurança";

    public DashboardAlunoNovoController(DashboardAlunoNovo view) {
        this.view = view;

        configurarBarras();
        configurarEventos();
        configurarBotoes();
    }

    private void configurarEventos() {
        view.getBtnParticiparExperimentos().addActionListener(e -> iniciarQuiz(QUIZ_EXPERIMENTOS));

        view.getBtnParticiparMateriaisLab().addActionListener(e -> iniciarQuiz(QUIZ_MATERIAIS));

        view.getBtnParticiparSeguranca().addActionListener(e -> iniciarQuiz(QUIZ_SEGURANCA));

        view.getBtnSair().addActionListener(e -> sair());
    }

    private void configurarBarras() {
        configurarProgressBar(view.getProgressAcertos(), 60, "60%");
        configurarProgressBar(view.getProgressErros(), 10, "10%");
        configurarProgressBar(view.getProgressProgresso(), 70, "70%");
    }

    private void configurarProgressBar(javax.swing.JProgressBar progressBar, int valor, String texto) {
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(valor);
        progressBar.setString(texto);
        progressBar.setStringPainted(true);
    }

    private void configurarBotoes() {
        view.getBtnParticiparExperimentos().setOpaque(true);
        view.getBtnParticiparExperimentos().setBorderPainted(false);
        view.getBtnParticiparExperimentos().setFocusPainted(false);

        view.getBtnParticiparMateriaisLab().setOpaque(true);
        view.getBtnParticiparMateriaisLab().setBorderPainted(false);
        view.getBtnParticiparMateriaisLab().setFocusPainted(false);

        view.getBtnParticiparSeguranca().setOpaque(true);
        view.getBtnParticiparSeguranca().setBorderPainted(false);
        view.getBtnParticiparSeguranca().setFocusPainted(false);

        view.getBtnSair().setOpaque(true);
        view.getBtnSair().setBorderPainted(false);
        view.getBtnSair().setFocusPainted(false);
    }

    private void iniciarQuiz(String categoria) {
    TelaQuiz telaQuiz = new TelaQuiz(categoria);
    new TelaQuizController(telaQuiz, categoria);

    telaQuiz.setTitle("QuiZmica - " + categoria);
    telaQuiz.setVisible(true);

    view.dispose();
}

    private void sair() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);

        view.dispose();
    }
}