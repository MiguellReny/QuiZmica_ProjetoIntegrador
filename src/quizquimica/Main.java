package quizquimica;

import quizquimica.controller.AuthController;
import quizquimica.controller.PartidaController;
import quizquimica.model.Partida;
import quizquimica.model.Questao;
import quizquimica.model.Usuario;

public class Main {
    public static void main(String[] args) {

        AuthController auth = new AuthController();
        PartidaController partida = new PartidaController();

        // Login do aluno de teste
        Usuario u = auth.realizarLogin("alunoteste3a@aluno.cps", "xK9#mP2q");
        if (u == null) {
            System.out.println("Login FALHOU");
            return;
        }
        System.out.println("Login OK: " + u.getNome());

        // Inicia partida
        boolean iniciou = partida.iniciarPartida(u.getIdUsuario());
        System.out.println(iniciou ? "Partida iniciada!" : "Falha ao iniciar partida");

        // Responde 5 questões
        for (int i = 0; i < 5; i++) {
            Questao q = partida.getQuestaoAtual();
            if (q == null) break;

            System.out.println("\nQuestão " + (i + 1) + ": " + q.getEnunciado());
            int idPrimeira = q.getAlternativas().get(0).getIdAlternativa();
            boolean acertou = partida.responder(idPrimeira, false);
            System.out.println(acertou ? "✔ Acertou!" : "✘ Errou!");
        }

        // Finaliza
        Partida resultado = partida.finalizarPartida();
        System.out.println("\n--- Resultado ---");
        System.out.println("Pontuação: " + resultado.getPontuacao());
        System.out.println("Id da partida: " + resultado.getIdPartida());
    }
}