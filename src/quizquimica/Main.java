package quizquimica;

import quizquimica.view.TelaInicial;

public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new TelaJogar().setVisible(true);

        });
    }
}