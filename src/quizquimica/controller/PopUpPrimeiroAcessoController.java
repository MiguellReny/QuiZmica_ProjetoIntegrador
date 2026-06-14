package quizquimica.controller;

import javax.swing.JOptionPane;
import quizquimica.view.PopUpPrimeiroAcesso;

public class PopUpPrimeiroAcessoController {

    private final PopUpPrimeiroAcesso view;

    public PopUpPrimeiroAcessoController(PopUpPrimeiroAcesso view) {
        this.view = view;
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

        // Temporário: depois vamos trocar isso pela chamada ao banco
        JOptionPane.showMessageDialog(
                view,
                "Senha redefinida com sucesso!"
        );

        view.dispose();
    }
}