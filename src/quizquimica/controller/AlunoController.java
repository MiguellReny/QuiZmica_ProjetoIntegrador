package quizquimica.controller;

import java.util.List;

import quizquimica.dao.PartidaDAO;
import quizquimica.model.Partida;
import quizquimica.service.QuestaoService;
import quizquimica.model.Questao;

public class AlunoController {

    private final PartidaDAO partidaDAO = new PartidaDAO();
    private final QuestaoService questaoService = new QuestaoService();

    public List<Partida> verDesempenho(int idUsuario) {
        return partidaDAO.buscarPorAluno(idUsuario);
    }

    public List<Questao> listarPorDificuldade(String dificuldade){
        return questaoService.listarPorDificuldade(dificuldade);
    }
}