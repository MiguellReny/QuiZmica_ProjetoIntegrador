package quizquimica.controller;

import quizquimica.model.Usuario;
import quizquimica.service.AuthService;

public class LoginController {
    private final AuthService authService;
    public LoginController() {
        authService = new AuthService();
    }
    public Usuario autenticar(String login, String senha) {
        return authService.login(login, senha);
    }
}