package quizquimica.controller;

import quizquimica.view.TelaJogar;
import quizquimica.view.TelaLogin;
import quizquimica.view.TermodeUso;

public class JogarController {
    private TelaJogar tela;
    public JogarController(TelaJogar tela) {
        this.tela = tela;
    }
    public void abrirLogin() {
        TelaLogin login = new TelaLogin();
        login.setVisible(true);
        tela.dispose();
    }
    public void abrirTermos() {
        TermodeUso termo = new TermodeUso();
        termo.setVisible(true);
        tela.dispose();
    }
}