package quizquimica;

import quizquimica.controller.AuthController;
import quizquimica.model.Usuario;

public class Main {
    public static void main(String[] args) {

        AuthController auth = new AuthController();

        // Testa login do professor
        Usuario prof = auth.realizarLogin("mariadosocorro@cps", "******");
        System.out.println(prof != null ? "Professora OK: " + prof.getNome() + " (" + prof.getTipo() + ")" : "Professora FALHOU");

        // Testa login do aluno
        Usuario aluno = auth.realizarLogin("alunoteste3a@aluno.cps", "******");
        System.out.println(aluno != null ? "Aluno OK: " + aluno.getNome() + " (" + aluno.getTipo() + ")" : "Aluno FALHOU");
    }
}