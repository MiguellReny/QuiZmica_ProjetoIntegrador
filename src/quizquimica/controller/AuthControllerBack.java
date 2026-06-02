package quizquimica.controller;

import quizquimica.model.Usuario;
import quizquimica.service.AuthService;

public class AuthControllerBack {

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
        String[] credenciais = authService.cadastrarAluno(nome, turma, senhaDefinida);
        if (credenciais != null) {
            System.out.println("[AuthController] Aluno cadastrado: " + credenciais[0]);
        }
        return credenciais;
    }
    
    public boolean redefinirSenhaAluno(String loginAluno, String novaSenha) {
        boolean ok = authService.redefinirSenha(loginAluno, novaSenha);
        if (ok) {
            System.out.println("[AuthController] Senha redefinida para: " + loginAluno);
        }
        return ok;
    }
}