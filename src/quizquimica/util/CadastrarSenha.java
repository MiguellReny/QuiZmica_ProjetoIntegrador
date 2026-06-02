package quizquimica.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CadastrarSenha {

    private CadastrarSenha() {}

public static String hashSenha(String senhaTextoClaro) {
    if (senhaTextoClaro == null || senhaTextoClaro.isBlank()) return null;
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] bytes = md.digest(senhaTextoClaro.getBytes());
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));}
        return hex.toString();} catch (NoSuchAlgorithmException e) {
        System.err.println("[CadastrarSenha] SHA-256 não disponível: " + e.getMessage());
        return null;}}

public static boolean senhaValida(String senha) {
    return senha != null && senha.length() >= 6;}
}