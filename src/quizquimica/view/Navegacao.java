package quizquimica.view;

public class Navegacao {

    public static void irParaTelaJogar(javax.swing.JDialog dialog) {
        if (dialog.getOwner() != null) dialog.getOwner().dispose();
        dialog.dispose();
        new TelaJogar().setVisible(true);
    }
    public static void irParaAlunosPesquisa(javax.swing.JDialog dialog) {
        if (dialog.getOwner() != null) dialog.getOwner().dispose();
        dialog.dispose();
        new AlunosPesquisa().setVisible(true);
    }
    public static void irParaDashboardProfessor(javax.swing.JDialog dialog) {
        if (dialog.getOwner() != null) dialog.getOwner().dispose();
        dialog.dispose();
        new DashboardProfessor().setVisible(true);
    }
}