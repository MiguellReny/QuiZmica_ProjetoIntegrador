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
    //metodo para carregar o desempenho do aluno nos quizzes
    private void carregarDesempenho() {
    int acertos = AuthService.getInstance().getUsuarioLogado().getAcertos();
    int erros = AuthService.getInstance().getUsuarioLogado().getErros();
    int aproveitamento = (acertos + erros) > 0 ? (acertos * 100) / (acertos + erros) : 0;
    view.getProgressAcertos().setValue(acertos);
    view.getLblAcertosValor().setText(acertos + "%");
    view.getProgressErros().setValue(erros);
    view.getLblErrosValor().setText(erros + "%");
    view.getProgressAproveitamento().setValue(aproveitamento);
    view.getLblAproveitamentoValor().setText(aproveitamento + "%");
}
    //metodo para configurar os eventos dos botoes
    private void configurarEventos() {
        view.getBtnQuiz1().addActionListener(e -> iniciarQuiz());
        view.getBtnQuiz2().addActionListener(e -> iniciarQuiz());
        view.getBtnQuiz3().addActionListener(e -> iniciarQuiz());
        view.getBtnQuiz4().addActionListener(e -> iniciarQuiz());
        view.getBtnSair().addActionListener(e -> sair());
    }
    //metodo para iniciar o quiz
    private void iniciarQuiz() {
        System.out.println("Quiz iniciado"); //abrir tela quiz
    }
    //metodo sair
    private void sair() {
        view.dispose();
        TelaJogar tela = new TelaJogar();
        tela.setVisible(true);
    }
}