package quizquimica.util;

import java.security.SecureRandom;

public class GeradorSenha {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int TAMANHO = 8;

    public static String gerarSenhaAleatoria() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(TAMANHO);
        for (int i = 0; i < TAMANHO; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }

    public static String hashSenha(String senha) {
        return Integer.toHexString(senha.hashCode());
    }
}
