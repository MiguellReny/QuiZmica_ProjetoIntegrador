package quizquimica.controller;

import javax.swing.JOptionPane;

import quizquimica.dao.UsuarioDAO;
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

        try {

            java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(
                    novaSenha.getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );

            StringBuilder senhaHash = new StringBuilder();

            for (byte b : hash) {
                senhaHash.append(String.format("%02x", b));
            }

            quizquimica.dao.UsuarioDAO usuarioDAO =
                    new quizquimica.dao.UsuarioDAO();

            boolean atualizou =
                    usuarioDAO.atualizarSenha(
                            usuario.getLogin(),
                            senhaHash.toString()
                    );

            if (!atualizou) {
                JOptionPane.showMessageDialog(
                        view,
                        "Erro ao atualizar senha."
                );
                return;
            }

            usuarioDAO.marcarPrimeiroLoginConcluido(usuario.getLogin());

            JOptionPane.showMessageDialog(
                    view,
                    "Senha redefinida com sucesso!"
            );

            view.dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    view,
                    "Erro ao processar senha: " + e.getMessage()
            );
        }
    }

    public static boolean usuarioJaRedefiniuSenhaPrimeiroAcesso(
            Usuario usuario) {

        return !new UsuarioDAO()
                .primeiroLogin(usuario.getLogin());
    }

    public static void marcarPrimeiroAcessoConcluido(
            Usuario usuario) {

        new UsuarioDAO()
            .marcarPrimeiroLoginConcluido(
                usuario.getLogin()
            );
    }
}