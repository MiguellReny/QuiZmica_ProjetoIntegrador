package quizquimica.controller;

import javax.swing.JOptionPane;
import quizquimica.model.Professor;
import quizquimica.model.Usuario;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.PopUpPrimeiroAcesso;
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
         DashboardProfessor dashboard = new DashboardProfessor();
         dashboard.setVisible(true);
 
         verificarPrimeiroAcesso(dashboard);
     } else {
         DashboardAlunoNovo dashboard = new DashboardAlunoNovo();
         dashboard.setVisible(true);

         verificarPrimeiroAcesso(dashboard);
     }
    }

    private void verificarPrimeiroAcesso(java.awt.Frame dashboard) {
     if (!PopUpPrimeiroAcessoController.usuarioJaRedefiniuSenhaPrimeiroAcesso(usuario)) {
         PopUpPrimeiroAcesso popup = new PopUpPrimeiroAcesso(dashboard, true, usuario);
         popup.setVisible(true);
     }
    }

    public static boolean usuarioJaAceitouTermos(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null) {
            return false;
        }
        return new quizquimica.dao.UsuarioDAO().termoAceito(usuario.getLogin());
    }

    public static void marcarTermosComoAceitos(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null) {
            return;
        }
        new quizquimica.dao.UsuarioDAO().marcarTermoAceito(usuario.getLogin());
    }
}