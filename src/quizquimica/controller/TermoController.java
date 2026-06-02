package quizquimica.controller;

import quizquimica.view.TelaJogar;
import quizquimica.view.TermodeUso;

import javax.swing.JOptionPane;

public class TermoController {

    private TermodeUso view;

    public TermoController(TermodeUso view) {
        this.view = view;
    }

    public void aceitarTermos() {

        if (!view.getChkTermos().isSelected()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Você deve aceitar os termos."
            );

            return;
        }

        view.dispose();

        new TelaJogar().setVisible(true);
    }
}