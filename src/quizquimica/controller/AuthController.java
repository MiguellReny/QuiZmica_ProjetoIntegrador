package quizquimica.controller;

import quizquimica.model.Aluno;
import quizquimica.model.Professor;
import quizquimica.model.Usuario;
import quizquimica.service.AuthService;

public class AuthController {

    private AuthService authService = new AuthService();

    // Retorna "aluno", "professor" ou null se falhar
    public String realizarLogin(String login, String senha) {
        Usuario usuario = authService.login(login, senha);

        if (usuario == null) return null;

        if (usuario instanceof Professor) {
            System.out.println("[AuthController] Login professor: " + usuario.getNome());
            return "professor";
        } else if (usuario instanceof Aluno) {
            System.out.println("[AuthController] Login aluno: " + usuario.getNome());
            return "aluno";
        }

        return null;
    }

    // Redefine senha de aluno — retorna nova senha para o professor informar
    public String redefinirSenhaAluno(String loginAluno) {
        String novaSenha = authService.redefinirSenha(loginAluno);
        if (novaSenha != null) {
            System.out.println("[AuthController] Senha redefinida para: " + loginAluno);
        }
        return novaSenha;
    }
}
