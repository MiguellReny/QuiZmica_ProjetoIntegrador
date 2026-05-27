package quizquimica.service;

import quizquimica.dao.QuestaoDAO;
import quizquimica.model.Questao;
import quizquimica.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class QuestaoService {

    private final QuestaoDAO questaoDAO = new QuestaoDAO();

    public List<Questao> listarTodas() {
        List<Questao> todas = new ArrayList<>();
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelFacil, 999));
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelMedio, 999));
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelDificil, 999));
        return todas;
    }

    public Questao buscarPorId(int idQuestao) {
        return questaoDAO.buscarPorId(idQuestao);
    }

    public boolean adicionarQuestao(Questao questao) {
        if (questao == null) return false;
        if (questao.getEnunciado() == null || questao.getEnunciado().isBlank()) return false;
        if (questao.getDificuldade() == null || questao.getDificuldade().isBlank()) return false;
        if (questao.getDica() == null || questao.getDica().isBlank()) return false;
        return questaoDAO.inserir(questao);
    }

    public boolean editarQuestao(Questao questao) {
        if (questao == null) return false;
        if (questaoDAO.buscarPorId(questao.getIdQuestao()) == null) return false;
        if (questao.getEnunciado() == null || questao.getEnunciado().isBlank()) return false;
        if (questao.getDificuldade() == null || questao.getDificuldade().isBlank()) return false;
        if (questao.getDica() == null || questao.getDica().isBlank()) return false;
        return questaoDAO.atualizar(questao);
    }

    public boolean removerQuestao(int idQuestao) {
        if (questaoDAO.buscarPorId(idQuestao) == null) return false;
        return questaoDAO.remover(idQuestao);
    }
}