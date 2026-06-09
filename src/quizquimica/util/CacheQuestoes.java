package quizquimica.util;

import java.util.List;
import quizquimica.model.Questao;

public class CacheQuestoes {
    private static CacheQuestoes instancia;
    private List<Questao> questoes;
    private long ultimaAtualizacao = 0;
    private static final long TEMPO_EXPIRACAO = 5 * 60 * 1000; // 5 minutos

    private CacheQuestoes() {}

    public static CacheQuestoes getInstance() {
        if (instancia == null) instancia = new CacheQuestoes();
        return instancia;
    }

    public List<Questao> getQuestoes() { return questoes; }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
        this.ultimaAtualizacao = System.currentTimeMillis();
    }

    public boolean estaValido() {
        return questoes != null &&
               (System.currentTimeMillis() - ultimaAtualizacao) < TEMPO_EXPIRACAO;
    }

    public void invalidar() {
        questoes = null;
        ultimaAtualizacao = 0;
    }
}