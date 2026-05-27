package quizquimica;

import quizquimica.controller.AuthController;
import quizquimica.model.Usuario;

public class Main {
    public static void main(String[] args) {

        AuthController auth = new AuthController();

        Usuario prof = auth.realizarLogin("mariadosocorro@cps.sp.gov.br", "482951");
        System.out.println(prof != null ? "Conectado ao Aiven! Login OK: " + prof.getNome() : "FALHOU");
    }
}