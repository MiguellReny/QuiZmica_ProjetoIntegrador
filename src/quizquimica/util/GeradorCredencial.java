package quizquimica.util;

import java.text.Normalizer;

public class GeradorCredencial {

    public static String gerarLoginAluno(String nome, String turma) {
        String nomeMinus = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "") // remove acentos
                .toLowerCase()
                .replaceAll("\\s+", ""); // remove espaços

        String turmaMinus = turma.toLowerCase().replaceAll("\\s+", "");

        return nomeMinus + turmaMinus + "@aluno.cps";
    }
}