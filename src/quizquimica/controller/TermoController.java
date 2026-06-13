package quizquimica.controller;

import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import quizquimica.model.Professor;
import quizquimica.model.Usuario;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.TelaJogar;
import quizquimica.view.TermodeUso;

public class TermoController {

    private TermodeUso view;
    private Usuario usuario;

    public TermoController(TermodeUso view) {
        this(view, null);
    }

    public TermoController(TermodeUso view, Usuario usuario) {
        this.view = view;
        this.usuario = usuario;

        configurarEventos();
    }

    private void configurarEventos() {
    view.getBtnCancelar().addActionListener(e -> cancelarTermos());
    }

    private void cancelarTermos() {
    new TelaJogar().setVisible(true);
    view.dispose();
    }

    public void aceitarTermos() {

        if (!view.getChkTermos().isSelected()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Você deve aceitar os termos."
            );

            return;
        }

        if (usuario != null) {
            marcarTermosComoAceitos(usuario);
            abrirTelaPrincipal();
        } else {
            new TelaJogar().setVisible(true);
        }

        view.dispose();
    }

    private void abrirTelaPrincipal() {
        if (usuario instanceof Professor) {
            new DashboardProfessor().setVisible(true);
        } else {
            new DashboardAlunoNovo().setVisible(true);
        }
    }

    public static boolean usuarioJaAceitouTermos(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null) {
            return false;
        }

        Preferences prefs = Preferences.userNodeForPackage(TermoController.class);
        return prefs.getBoolean("termos_" + usuario.getLogin(), false);
    }

    public static void marcarTermosComoAceitos(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null) {
            return;
        }

        Preferences prefs = Preferences.userNodeForPackage(TermoController.class);
        prefs.putBoolean("termos_" + usuario.getLogin(), true);
    }
}