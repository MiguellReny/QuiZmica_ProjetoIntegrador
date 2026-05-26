package quizquimica.controller;

import java.util.Map;

import quizquimica.model.Desempenho;
import quizquimica.model.Questao;
import quizquimica.service.AuthService;
import quizquimica.service.QuestaoService;
import quizquimica.service.DesempenhoService;

public class ProfessorController {

    private final QuestaoService questaoService = new QuestaoService();
    private final AuthService authService = new AuthService();
    private final DesempenhoService desempenhoService = new DesempenhoService();

    public String[] cadastrarAluno(String nome, String turma, String senha) {
        return authService.cadastrarAluno(nome, turma, senha);
    }

    public boolean adicionarQuestao(Questao questao) {
        return questaoService.adicionarQuestao(questao);
    }
    public boolean editarQuestao(Questao questao) {
        return questaoService.editarQuestao(questao);
    }
    public boolean removerQuestao(int idQuestao) {
        return questaoService.removerQuestao(idQuestao);
    }

    public Desempenho buscarDesempenho(int idUsuario) {
        return desempenhoService.buscarDesempenho(idUsuario);
    }
    public Map<Integer, Integer> buscarQuestoesMaisErradasTurma() {
        return desempenhoService.buscarQuestoesMaisErradasTurma();
    }

}



