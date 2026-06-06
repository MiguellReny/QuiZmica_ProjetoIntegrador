package quizquimica;
import quizquimica.view.TermodeUso;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TermodeUso tela = new TermodeUso();
            tela.setVisible(true);
        });
    }
}