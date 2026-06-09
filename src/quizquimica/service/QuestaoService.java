package quizquimica.service;

import quizquimica.dao.QuestaoDAO;
import quizquimica.util.ConversorImagemUrl;
import quizquimica.util.CacheQuestoes;
import quizquimica.model.Questao;
import quizquimica.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class QuestaoService {

    private final QuestaoDAO questaoDAO = new QuestaoDAO();

    public boolean salvarQuestao(Questao q) {
        boolean ok = questaoDAO.inserir(q);
        if (ok) CacheQuestoes.getInstance().invalidar();
        return ok;
    }

    public boolean atualizarQuestao(Questao q) {
        boolean ok = questaoDAO.atualizar(q);
        if (ok) CacheQuestoes.getInstance().invalidar();
        return ok;
    }

    public List<Questao> listarTodas() {
        CacheQuestoes cache = CacheQuestoes.getInstance();
        if (cache.estaValido()) {
            System.out.println("[QuestaoService] Retornando do cache");
            return cache.getQuestoes();
        }

        System.out.println("[QuestaoService] Buscando do banco...");
        List<Questao> todas = new ArrayList<>();
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelFacil,   999));
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelMedio,   999));
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelDificil, 999));

        cache.setQuestoes(todas);
        return todas;
    }

    public Questao buscarPorId(int idQuestao) {
        // Tenta achar no cache antes de ir ao banco
        CacheQuestoes cache = CacheQuestoes.getInstance();
        if (cache.estaValido()) {
            return cache.getQuestoes().stream()
                    .filter(q -> q.getIdQuestao() == idQuestao)
                    .findFirst()
                    .orElse(null);
        }
        return questaoDAO.buscarPorId(idQuestao);
    }

    public List<Questao> listarPorDificuldade(String dificuldade) {
        if (!dificuldade.equals(Constantes.nivelFacil) &&
            !dificuldade.equals(Constantes.nivelMedio) &&
            !dificuldade.equals(Constantes.nivelDificil)) {
            System.out.println("[QuestaoService] Dificuldade inválida: " + dificuldade);
            return new ArrayList<>();
        }

        // Se o cache tiver tudo, filtra direto sem bater no banco
        CacheQuestoes cache = CacheQuestoes.getInstance();
        if (cache.estaValido()) {
            List<Questao> filtradas = new ArrayList<>();
            for (Questao q : cache.getQuestoes()) {
                if (q.getDificuldade().equals(dificuldade)) filtradas.add(q);
            }
            return filtradas;
        }

        return questaoDAO.listarPorDificuldade(dificuldade, 999);
    }

    public boolean adicionarQuestao(Questao questao) {
        if (questao == null) { System.out.println("[QuestaoService] Questão nula"); return false; }
        if (questao.getEnunciado().isBlank()) { System.out.println("[QuestaoService] Enunciado vazio"); return false; }
        if (!questao.getDificuldade().equals(Constantes.nivelFacil) &&
            !questao.getDificuldade().equals(Constantes.nivelMedio) &&
            !questao.getDificuldade().equals(Constantes.nivelDificil)) {
            System.out.println("[QuestaoService] Dificuldade inválida"); return false;
        }
        if (!questao.getTipo().equals("textual") && !questao.getTipo().equals("imagem")) {
            System.out.println("[QuestaoService] Tipo inválido: " + questao.getTipo()); return false;
        }
        if (questao.getDica().isBlank()) { System.out.println("[QuestaoService] Dica vazia"); return false; }

        questao.setImagemUrl(ConversorImagemUrl.converter(questao.getImagemUrl()));
        boolean ok = questaoDAO.inserir(questao);
        if (ok) CacheQuestoes.getInstance().invalidar();
        return ok;
    }

    public boolean removerQuestao(int idQuestao) {
        // buscarPorId já usa cache, sem viagem extra ao banco
        if (buscarPorId(idQuestao) == null) {
            System.out.println("[QuestaoService] Id não existe: " + idQuestao);
            return false;
        }
        boolean ok = questaoDAO.remover(idQuestao);
        if (ok) CacheQuestoes.getInstance().invalidar();
        return ok;
    }

    public boolean editarQuestao(Questao questao) {
        if (questao == null) { System.out.println("[SERVICE] questão nula"); return false; }
        if (questao.getEnunciado().isBlank()) { System.out.println("[SERVICE] enunciado vazio"); return false; }
        if (!questao.getDificuldade().equals(Constantes.nivelFacil) &&
            !questao.getDificuldade().equals(Constantes.nivelMedio) &&
            !questao.getDificuldade().equals(Constantes.nivelDificil)) {
            System.out.println("[SERVICE] dificuldade inválida: " + questao.getDificuldade()); return false;
        }
        if (!questao.getTipo().equals("textual") && !questao.getTipo().equals("imagem")) {
            System.out.println("[SERVICE] tipo inválido: " + questao.getTipo()); return false;
        }
        if (questao.getDica().isBlank()) { System.out.println("[SERVICE] dica vazia"); return false; }

        // ← buscarPorId removido aqui, era uma viagem ao banco desnecessária
        System.out.println("[SERVICE] personagem: " + questao.getPersonagem());
        questao.setImagemUrl(ConversorImagemUrl.converter(questao.getImagemUrl()));
        boolean ok = questaoDAO.atualizar(questao);
        if (ok) CacheQuestoes.getInstance().invalidar();
        return ok;
    }
}