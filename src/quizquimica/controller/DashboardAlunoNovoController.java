package quizquimica.controller;

import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.TelaLogin;
import quizquimica.view.TelaQuiz;

public class DashboardAlunoNovoController {

    private final DashboardAlunoNovo view;

    public DashboardAlunoNovoController(DashboardAlunoNovo view) {
        this.view = view;

        configurarBarras();
        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnParticiparExperimentos().addActionListener(e -> iniciarQuiz("Experimentos Químicos"));
        view.getBtnParticiparMateriaisLab().addActionListener(e -> iniciarQuiz("Materiais do laboratório"));
        view.getBtnParticiparSeguranca().addActionListener(e -> iniciarQuiz("Equipamentos de segurança"));

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

    private void iniciarQuiz(String categoria) {
        TelaQuiz telaQuiz = new TelaQuiz();
        new TelaQuizController(telaQuiz);

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