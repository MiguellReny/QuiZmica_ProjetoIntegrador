package quizquimica;
import quizquimica.view.DashboardAluno;
import quizquimica.controller.AlunoController;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                DashboardAluno tela = new DashboardAluno();
                new AlunoController(tela);
                tela.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}