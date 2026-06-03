package quizquimica;

import quizquimica.view.AlunosPesquisa;

public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            AlunosPesquisa tela = new AlunosPesquisa();

            tela.setVisible(true);

        });

    }
}