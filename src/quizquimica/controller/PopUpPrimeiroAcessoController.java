package quizquimica.controller;

import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import quizquimica.model.Usuario;
import quizquimica.view.PopUpPrimeiroAcesso;

public class PopUpPrimeiroAcessoController {

    private final PopUpPrimeiroAcesso view;
    private final Usuario usuario;

    public PopUpPrimeiroAcessoController(PopUpPrimeiroAcesso view) {
        this(view, null);
    }

    public PopUpPrimeiroAcessoController(PopUpPrimeiroAcesso view, Usuario usuario) {
        this.view = view;
        this.usuario = usuario;
        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnSalvarContinuar().addActionListener(e -> salvarNovaSenha());
    }

    private void salvarNovaSenha() {
        String novaSenha = new String(view.getTxtNovaSenha().getPassword()).trim();
        String confirmarSenha = new String(view.getTxtConfirmarSenha().getPassword()).trim();

        if (novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Preencha os dois campos de senha."
            );
            return;
        }

        if (!novaSenha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(
                    view,
                    "As senhas não coincidem."
            );
            return;
        }

        if (novaSenha.length() < 6) {
            JOptionPane.showMessageDialog(
                    view,
                    "A senha deve ter pelo menos 6 caracteres."
            );
            return;
        }

        /*
         * Temporário:
         * Depois sua colega vai trocar essa parte por uma atualização real no banco,
         * usando DAO/Service para salvar a nova senha.
         */
        if (usuario != null) {
            marcarPrimeiroAcessoConcluido(usuario);
        }

        JOptionPane.showMessageDialog(
                view,
                "Senha redefinida com sucesso!"
        );

        view.dispose();
    }

    public static boolean usuarioJaRedefiniuSenhaPrimeiroAcesso(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null) {
            return false;
        }

        Preferences prefs = Preferences.userNodeForPackage(PopUpPrimeiroAcessoController.class);
        return prefs.getBoolean("primeiro_acesso_" + usuario.getLogin(), false);
    }

    public static void marcarPrimeiroAcessoConcluido(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null) {
            return;
        }

        Preferences prefs = Preferences.userNodeForPackage(PopUpPrimeiroAcessoController.class);
        prefs.putBoolean("primeiro_acesso_" + usuario.getLogin(), true);
    }
}