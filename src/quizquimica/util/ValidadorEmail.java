package quizquimica.util;

public class ValidadorEmail {

    private static final String DOMINIO_ETEC = "@etec.sp.gov.br";

    // Valida se o login é um email da ETEC
    public static boolean isEmailValido(String login) {
        return login != null && login.toLowerCase().endsWith(DOMINIO_ETEC);
    }

    // Identifica perfil pelo prefixo do login
    // Convenção: prof.nome@etec... = professor
    public static String identificarPerfil(String login) {
        if (login == null) return null;
        return login.toLowerCase().startsWith("prof.") ? "professor" : "aluno";
    }
}
