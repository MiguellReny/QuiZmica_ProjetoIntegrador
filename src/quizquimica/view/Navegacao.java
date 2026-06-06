package quizquimica.view;

import quizquimica.controller.AlunosPesquisaController;

public class Navegacao {

    public static void irParaTelaJogar(javax.swing.JDialog dialog) {
        if (dialog.getOwner() != null) dialog.getOwner().dispose();
        dialog.dispose();
        TelaJogar tela = new TelaJogar();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }

    public static void irParaAlunosPesquisa(javax.swing.JDialog dialog) {
        if (dialog.getOwner() != null) dialog.getOwner().dispose();
        dialog.dispose();
        AlunosPesquisa tela = new AlunosPesquisa();
        new AlunosPesquisaController(tela); 
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }

    public static void irParaDashboardProfessor(javax.swing.JDialog dialog) {
        if (dialog.getOwner() != null) dialog.getOwner().dispose();
        dialog.dispose();
        DashboardProfessor tela = new DashboardProfessor();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}