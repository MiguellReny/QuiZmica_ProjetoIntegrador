package quizquimica;

import quizquimica.view.DashboardAluno;
import quizquimica.view.TelaJogar;
import quizquimica.controller.AlunoController;
import quizquimica.model.Aluno;
import quizquimica.service.AuthService;



public class Main {

    public static void main(String[] args) {
Aluno aluno = new Aluno();

aluno.setNome("Teste");

aluno.setAcertos(80);

aluno.setErros(20);

AuthService.getInstance().setUsuarioLogado(aluno);
        DashboardAluno tela = new DashboardAluno();
        new AlunoController(tela);
        tela.setVisible(true);
    }
}