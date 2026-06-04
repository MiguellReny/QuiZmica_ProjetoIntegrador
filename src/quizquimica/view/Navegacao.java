package quizquimica.view;

import quizquimica.controller.AlunosPesquisaController;

public class Navegacao {

    public static void irParaTelaJogar(javax.swing.JDialog dialog) {

        TelaJogar tela = new TelaJogar();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

        dialog.dispose();
    }

    public static void irParaAlunosPesquisa(javax.swing.JDialog dialog) {

        AlunosPesquisa tela = new AlunosPesquisa();

        new AlunosPesquisaController(tela);

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

        dialog.dispose();
    }

    public static void irParaDashboardProfessor(javax.swing.JDialog dialog) {

        DashboardProfessor tela = new DashboardProfessor();

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);

        dialog.dispose();
    }
}