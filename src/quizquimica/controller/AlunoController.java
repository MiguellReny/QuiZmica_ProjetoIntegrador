package quizquimica.controller;

import quizquimica.service.AuthService;
import quizquimica.view.DashboardAluno;
import quizquimica.view.TelaJogar;

public class AlunoController {

    private DashboardAluno view;

    public AlunoController(DashboardAluno view) {
        this.view = view;
        carregarDesempenho();
        configurarEventos();
    }

    private void carregarDesempenho() {
        quizquimica.model.Usuario usuario = AuthService.getInstance().getUsuarioLogado();
        if (usuario == null) return;

        int acertos = usuario.getAcertos();
        int erros = usuario.getErros();
        int aproveitamento = (acertos + erros) > 0 ? (acertos * 100) / (acertos + erros) : 0;

        // Progress bars
        view.getProgressAcertos().setValue(acertos);
        view.getProgressErros().setValue(erros);
        view.getProgressAproveitamento().setValue(aproveitamento);

        // Labels coloridos
        view.getLabelAcertosValor().setText(acertos + "%");
        view.getLabelAcertosValor().setForeground(new java.awt.Color(76, 175, 80));

        view.getLabelErrosValor().setText(erros + "%");
        view.getLabelErrosValor().setForeground(new java.awt.Color(211, 47, 47));

        view.getLabelAproveitamentoValor().setText(aproveitamento + "%");
        view.getLabelAproveitamentoValor().setForeground(new java.awt.Color(33, 150, 243));
    }

    private void configurarEventos() {
        view.getBtnQuiz1().addActionListener(e -> iniciarQuiz());
        view.getBtnQuiz2().addActionListener(e -> iniciarQuiz());
        view.getBtnQuiz3().addActionListener(e -> iniciarQuiz());
        view.getBtnSair().addActionListener(e -> sair());
    }

    private void iniciarQuiz() {
        System.out.println("Quiz iniciado");
    }

    private void sair() {
        view.dispose();
        TelaJogar tela = new TelaJogar();
        tela.setVisible(true);
    }
}