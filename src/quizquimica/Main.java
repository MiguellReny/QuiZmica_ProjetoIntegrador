package quizquimica;
import quizquimica.view.TermodeUso;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        
        java.awt.EventQueue.invokeLater(() -> {
            TermodeUso tela = new TermodeUso();
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fecha com o X
            tela.setVisible(true);
            
            SwingUtilities.invokeLater(() -> {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                tela.setSize(screenSize);
                tela.setLocation(0, 0);
                tela.setExtendedState(JFrame.MAXIMIZED_BOTH); // maximizado com barra
            });
        });
    }
}