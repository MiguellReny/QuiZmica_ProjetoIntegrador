package quizquimica.util;

import javax.swing.*;
import java.awt.*;

public class TelaUtil {

    public static void prepararTelaCheia(JFrame frame) {
        // removido setUndecorated(true) para mostrar barra de título
    }

    public static void aplicarTelaCheia(JFrame frame) {
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        int w = tela.width;
        int h = tela.height;

        int gameW = 1366;
        int gameH = 768;

        int offsetX = (w - gameW) / 2;
        int offsetY = (h - gameH) / 2;

        // Pega o painel raiz
        Component raiz = frame.getContentPane().getComponent(0);
        frame.getContentPane().remove(raiz);
        raiz.setBounds(offsetX, offsetY, gameW, gameH);

        // Painel de fundo preto centralizado
        JPanel fundo = new JPanel(null);
        fundo.setBackground(new Color(230, 240, 251));
        fundo.setPreferredSize(tela);
        fundo.add(raiz);

        frame.setContentPane(fundo);
        frame.setSize(w, h);
        frame.setLocation(0, 0);
        frame.validate();
        frame.repaint();
    }
}