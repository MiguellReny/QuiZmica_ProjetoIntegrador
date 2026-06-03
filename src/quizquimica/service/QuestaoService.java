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

    /*public List<Questao> listarTodas() {
        // Busca todas as questões sem filtro de dificuldade (limite alto)
        List<Questao> todas = new ArrayList<>();
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelFacil, 999));
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelMedio, 999));
        todas.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelDificil, 999));
        return todas;
    }*/

        public List<Questao> listarTodas() {

    List<Questao> lista = new ArrayList<>();

    Questao q1 = new Questao();

    q1.setIdQuestao(1);
    q1.setEnunciado("Qual é o símbolo químico do oxigênio?");
    q1.setDificuldade(Constantes.nivelFacil);
    q1.setTipo("textual");
    q1.setDica("É um gás presente no ar");


    Questao q2 = new Questao();

    q2.setIdQuestao(2);
    q2.setEnunciado("Qual elemento possui número atômico 6?");
    q2.setDificuldade(Constantes.nivelMedio);
    q2.setTipo("textual");
    q2.setDica("Está presente no carbono");


    Questao q3 = new Questao();

    q3.setIdQuestao(3);
    q3.setEnunciado("Qual é o metal líquido em temperatura ambiente?");
    q3.setDificuldade(Constantes.nivelDificil);
    q3.setTipo("textual");
    q3.setDica("Era usado em termômetros");


    lista.add(q1);
    lista.add(q2);
    lista.add(q3);


    return lista;
}

    /*public Questao buscarPorId(int idQuestao) {
        return questaoDAO.buscarPorId(idQuestao);
    }*/
   public Questao buscarPorId(int idQuestao) {

    for(Questao q : listarTodas()){

        if(q.getIdQuestao() == idQuestao){

            return q;
        }
    }

    return null;
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