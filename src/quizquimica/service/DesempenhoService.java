package quizquimica.service;

import java.util.Map;

import quizquimica.dao.PartidaDAO;
import quizquimica.dao.RespostaDAO;
import quizquimica.model.Desempenho;
import quizquimica.model.Partida;

public class DesempenhoService {

    private final RespostaDAO respostaDAO = new RespostaDAO();
    private final PartidaDAO partidaDAO = new PartidaDAO();

    public Desempenho buscarDesempenho(int idUsuario){
        Desempenho desempenho = new Desempenho();
        desempenho.setIdUsuario(idUsuario);
        desempenho.setPartidas(partidaDAO.buscarPorAluno(idUsuario));
        int totalAcertos = 0;
        int totalErros = 0;
        for (Partida p : desempenho.getPartidas()) {
            totalAcertos += respostaDAO.contarAcertosPorAluno(p.getIdPartida());
            totalErros += respostaDAO.contarErrosPorAluno(p.getIdPartida());
        }
        desempenho.setTotalAcertos(totalAcertos);
        desempenho.setTotalErros(totalErros);
        desempenho.setQuestoesMaisErradas(respostaDAO.questoesMaisErradas());

        return desempenho;
    }

    public Map<Integer, Integer> buscarQuestoesMaisErradasTurma() {
        return respostaDAO.questoesMaisErradas();
    }

    public double calcularMedia(int idUsuario) {
        return partidaDAO.calcularMedia(idUsuario);
    }
}