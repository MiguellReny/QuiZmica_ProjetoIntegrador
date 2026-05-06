package quizquimica.controller;

import quizquimica.model.Usuario;
import quizquimica.service.AuthService;

public class AuthController {

    private final AuthService authService = new AuthService();

    public Usuario realizarLogin(String login, String senha) {
        Usuario usuario = authService.login(login, senha);
        if (usuario != null) {
            System.out.println("[AuthController] Login realizado: "
                    + usuario.getNome() + " (" + usuario.getTipo() + ")");
        }
        return usuario;
    }

    public String[] cadastrarAluno(String nome, String turma, String senhaDefinida) {
        String[] credenciais = authService.cadastrarAluno(nome, turma,senhaDefinida);
        if (credenciais != null) {
            System.out.println("[AuthController] Aluno cadastrado: " + credenciais[0]);
        }
        return credenciais;
    }

    // ----------------------------------------------------------------
    // Redefinição de senha (professor redefine para aluno)
    // ----------------------------------------------------------------

    /**
     * Redefine a senha de um aluno com uma nova senha definida pelo professor.
     *
     * @param loginAluno  e-mail institucional do aluno
     * @param novaSenha   nova senha definida pelo professor
     * @return true se redefinida com sucesso
     */
    public boolean redefinirSenhaAluno(String loginAluno, String novaSenha) {
        boolean ok = authService.redefinirSenha(loginAluno, novaSenha);
        if (ok) {
            System.out.println("[AuthController] Senha redefinida para: " + loginAluno);
        }
        return ok;
    }
}