package quizquimica.service;

import quizquimica.dao.QuestaoDAO;
import quizquimica.util.ConversorImagemUrl;
import quizquimica.model.Questao;
import quizquimica.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class QuestaoService {

    private final QuestaoDAO questaoDAO = new QuestaoDAO();

    public boolean salvarQuestao(Questao q) {
        return questaoDAO.inserir(q); 
    }

    public boolean atualizarQuestao(Questao q) {
        return questaoDAO.atualizar(q); 
    }

            public List<Questao> listarTodas() {

        List<Questao> todas = new ArrayList<>();

        todas.addAll(
            questaoDAO.listarPorDificuldade(
                Constantes.nivelFacil,
                999
            )
        );

        todas.addAll(
            questaoDAO.listarPorDificuldade(
                Constantes.nivelMedio,
                999
            )
        );

        todas.addAll(
            questaoDAO.listarPorDificuldade(
                Constantes.nivelDificil,
                999
            )
        );

        return todas;
    }


   public Questao buscarPorId(int idQuestao) {
        return questaoDAO.buscarPorId(idQuestao);
    }

    public List<Questao> listarPorDificuldade(String dificuldade) {
        if (!dificuldade.equals(Constantes.nivelFacil) && 
        !dificuldade.equals(Constantes.nivelMedio) && 
        !dificuldade.equals(Constantes.nivelDificil)) {
        System.out.println("[QuestaoService] Dificuldade inválida: " + dificuldade);
        return new ArrayList<>();
        }

        return questaoDAO.listarPorDificuldade(dificuldade, 999);
    }

    public boolean adicionarQuestao(Questao questao) {
        if (questao == null) {
        System.out.println("[QuestaoService] Questão nula — objeto não foi criado corretamente");
        return false;
        }

        if (questao.getEnunciado().isBlank()) {
            System.out.println("[QuestaoService] Enunciado não pode ficar vazio");
            return false;
        }

        if (!questao.getDificuldade().equals(Constantes.nivelFacil) && 
        !questao.getDificuldade().equals(Constantes.nivelMedio) && 
        !questao.getDificuldade().equals(Constantes.nivelDificil)) {
        System.out.println("[QuestaoService] Dificuldade inválida");
        return false;
        }

        if (!questao.getTipo().equals("textual") && !questao.getTipo().equals("imagem")) {
            System.out.println("[QuestaoService] Tipo inválido: " + questao.getTipo());
            return false;
        }

        if(questao.getDica().isBlank()){
            System.out.println("[QuestaoService] Dica não pode ficar vazia");
            return false;
        }

        questao.setImagemUrl(ConversorImagemUrl.converter(questao.getImagemUrl()));

        return questaoDAO.inserir(questao);

    }

    public boolean removerQuestao(int idQuestao) {
        if(questaoDAO.buscarPorId(idQuestao) == null){
            System.out.println("[QuestaoService] Id da questão não existe");
            return false;
        }

        return questaoDAO.remover(idQuestao);
    }

    public boolean editarQuestao(Questao questao) {
        if (questao == null) {
            System.out.println("[QuestaoService] Questão nula — objeto não foi criado corretamente");
            return false;
        }

        if (questaoDAO.buscarPorId(questao.getIdQuestao()) == null) {
            System.out.println("[QuestaoService] Id da questão não existe");
            return false;
        }
        if (questao.getEnunciado().isBlank()) {
            System.out.println("[QuestaoService] Enunciado não pode ficar vazio");
            return false;
        }

        if (!questao.getDificuldade().equals(Constantes.nivelFacil) && 
        !questao.getDificuldade().equals(Constantes.nivelMedio) && 
        !questao.getDificuldade().equals(Constantes.nivelDificil)) {
        System.out.println("[QuestaoService] Dificuldade inválida");
        return false;
        }

        if (!questao.getTipo().equals("textual") && !questao.getTipo().equals("imagem")) {
            System.out.println("[QuestaoService] Tipo inválido: " + questao.getTipo());
            return false;
        }

        if(questao.getDica().isBlank()){
            System.out.println("[QuestaoService] Dica não pode ficar vazia");
            return false;
        }

        questao.setImagemUrl(ConversorImagemUrl.converter(questao.getImagemUrl()));

        return questaoDAO.atualizar(questao);
    }

}