package quizquimica.controller;

import java.util.List;
import quizquimica.dao.PartidaDAO;
import quizquimica.model.Partida;
import quizquimica.model.Sessao;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.TelaLogin;
import quizquimica.view.TelaQuiz;

public class DashboardAlunoNovoController {

    private final DashboardAlunoNovo view;

    public static final String QUIZ_EXPERIMENTOS = "Experimentos Químicos";
    public static final String QUIZ_MATERIAIS    = "Materiais do laboratório";
    public static final String QUIZ_SEGURANCA    = "Equipamentos de segurança";

    public DashboardAlunoNovoController(DashboardAlunoNovo view) {
        this.view = view;
        configurarBotoes();
        configurarEventos();
        carregarDesempenho();
    }

    private void configurarEventos() {
        view.getBtnParticiparExperimentos().addActionListener(e -> iniciarQuiz(QUIZ_EXPERIMENTOS));
        view.getBtnParticiparMateriaisLab().addActionListener(e -> iniciarQuiz(QUIZ_MATERIAIS));
        view.getBtnParticiparSeguranca().addActionListener(e -> iniciarQuiz(QUIZ_SEGURANCA));
        view.getBtnSair().addActionListener(e -> sair());
    }

    private void carregarDesempenho() {
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();
        List<Partida> partidas = new PartidaDAO().buscarPorAluno(idUsuario);

        if (partidas.isEmpty()) {
            configurarProgressBar(view.getProgressAcertos(),  0, "0%");
            configurarProgressBar(view.getProgressErros(),    0, "0%");
            configurarProgressBar(view.getProgressProgresso(), 0, "0%");
            return;
        }

        // Cada quiz tem 2 questoes * 10 pontos = 20 pontos máximo por partida
        int maxPontosPorPartida = 2 * 10;
        int totalPontos = partidas.stream().mapToInt(Partida::getPontuacao).sum();
        int maxTotal = partidas.size() * maxPontosPorPartida;

        int acertos  = (int) Math.round((double) totalPontos / maxTotal * 100);
        int erros    = 100 - acertos;
        int progresso = Math.min(partidas.size() * 10, 100); // 10% por partida concluída

        configurarProgressBar(view.getProgressAcertos(),   acertos,   acertos  + "%");
        configurarProgressBar(view.getProgressErros(),     erros,     erros    + "%");
        configurarProgressBar(view.getProgressProgresso(), progresso, progresso + "%");
    }

    private void configurarProgressBar(javax.swing.JProgressBar bar, int valor, String texto) {
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(valor);
        bar.setString(texto);
        bar.setStringPainted(true);
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