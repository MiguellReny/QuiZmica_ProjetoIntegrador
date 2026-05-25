package quizquimica.util;

public class ValidadorEmail {

    private static final String dominioProf = "@cps.sp.gov.br";
    private static final String dominioALuno = "@aluno.cps.sp.gov.br";

    public static boolean emailValido(String login) {
        if (login == null) return false;
        String l = login.toLowerCase();
        return l.endsWith(dominioProf) || l.endsWith(dominioALuno);
    }

    public static String identificarPerfil(String login) {
        if (login == null) return null;
        return login.toLowerCase().endsWith(dominioALuno) ? "aluno" : "professor";
    }
}