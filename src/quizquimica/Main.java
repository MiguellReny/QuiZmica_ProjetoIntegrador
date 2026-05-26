package quizquimica;

import quizquimica.controller.AuthController;
import quizquimica.model.Usuario;

public class Main {
    public static void main(String[] args) {

        AuthController auth = new AuthController();

        Usuario prof = auth.realizarLogin("mariadosocorro@cps.sp.gov.br", "******");
        System.out.println(prof != null ? "Professora OK: " + prof.getNome() + " (" + prof.getTipo() + ")" : "Professora FALHOU");

        Usuario aluno = auth.realizarLogin("alunoteste3a@aluno.cps.sp.gov.br", "xK9#mP2q");
        System.out.println(aluno != null ? "Aluno OK: " + aluno.getNome() + " (" + aluno.getTipo() + ")" : "Aluno FALHOU");
    }
}