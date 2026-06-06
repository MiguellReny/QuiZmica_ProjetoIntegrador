package quizquimica.service;

import quizquimica.dao.PartidaDAO;
import quizquimica.dao.QuestaoDAO;
import quizquimica.dao.RespostaDAO;
import quizquimica.model.Partida;
import quizquimica.model.Questao;
import quizquimica.util.Constantes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartidaService {

    private final QuestaoDAO questaoDAO = new QuestaoDAO();
    private final PartidaDAO partidaDAO = new PartidaDAO();
    private final RespostaDAO respostaDAO = new RespostaDAO();

    private List<Questao> questoesDaPartida;
    private int questaoAtual;
    private int pontuacao;
    private int dicasUsadas;
    private String nivel;
    private int idUsuario;
    private int idPartidaAtual;

    public boolean iniciarPartida(int idUsuario, String nivel) {
        this.idUsuario = idUsuario;
        this.nivel = nivel;
        this.pontuacao = 0;
        this.dicasUsadas = 0;
        this.questaoAtual = 0;
        this.questoesDaPartida = montarQuestoes(nivel);

        Partida partida = new Partida();
        partida.setIdUsuario(idUsuario);
        partida.setNivel(nivel);
        partida.setPontuacao(0);
        partida.setData(LocalDate.now());
        partida.setDicasUsadas(0);
        this.idPartidaAtual = partidaDAO.salvar(partida);

        return idPartidaAtual != -1;
    }

    // ✅ NOVO: expõe montarQuestoes para o TelaQuizController usar
    public List<Questao> montarQuestoesPorNivel(String nivel) {
        return montarQuestoes(nivel);
    }

    private List<Questao> montarQuestoes(String nivel) {
        List<Questao> lista = new ArrayList<>();

        if (nivel.equals(Constantes.nivelFacil)) {
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelFacil,   Constantes.facilFaceis));
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelMedio,   Constantes.facilMedias));
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelDificil, Constantes.facilDificeis));
        } else if (nivel.equals(Constantes.nivelMedio)) {
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelFacil,   Constantes.medioFaceis));
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelMedio,   Constantes.medioMedias));
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelDificil, Constantes.medioDificeis));
        } else {
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelFacil,   Constantes.dificilFaceis));
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelMedio,   Constantes.dificilMedias));
            lista.addAll(questaoDAO.listarPorDificuldade(Constantes.nivelDificil, Constantes.dificilDificeis));
        }

        Collections.shuffle(lista);
        return lista;
    }

    public Questao getQuestaoAtual() {
        if (questaoAtual < questoesDaPartida.size()) {
            return questoesDaPartida.get(questaoAtual);
        }
        return null;
    }

    public boolean responder(int idQuestao, int idAlternativa, boolean usouDica) {
        // busca a questão pelo id, não pelo índice
        Questao questao = questoesDaPartida.stream()
            .filter(q -> q.getIdQuestao() == idQuestao)
            .findFirst().orElse(null);

        if (questao == null) return false;

        boolean acertou = questao.getAlternativas().stream()
            .anyMatch(a -> a.getIdAlternativa() == idAlternativa && a.isAlternativaCorreta());

        if (acertou) {
            int pontos = getPontosPorDificuldade(questao.getDificuldade());
            if (usouDica) pontos = (int)(pontos * (1 - Constantes.descontoDica));
            pontuacao += pontos;
        }

        if (usouDica) dicasUsadas++;
        respostaDAO.salvar(idPartidaAtual, questao.getIdQuestao(), idAlternativa);
        return acertou;
    }

    private int getPontosPorDificuldade(String dificuldade) {
        if (dificuldade.equals(Constantes.nivelFacil)) return Constantes.pontoF;
        if (dificuldade.equals(Constantes.nivelMedio)) return Constantes.pontoM;
        return Constantes.pontoD;
    }

    public boolean podePedirDica() {
        return dicasUsadas < Constantes.maximoDicas;
    }

    public String getDicaAtual() {
        if (!podePedirDica()) return null;
        Questao questao = getQuestaoAtual();
        return questao != null ? questao.getDica() : null;
    }

    public boolean partidaEncerrada() {
        return questaoAtual >= Constantes.totalQuestoes;
    }

    public List<Questao> getQuestoesDaPartida() {
        return questoesDaPartida;
    }

    public Partida finalizarPartida() {
        Partida partida = new Partida();
        partida.setIdPartida(idPartidaAtual);
        partida.setIdUsuario(idUsuario);
        partida.setNivel(nivel);
        partida.setPontuacao(pontuacao);
        partida.setData(LocalDate.now());
        partida.setDicasUsadas(dicasUsadas);
        partidaDAO.atualizar(partida);
        return partida;
    }

    public int getPontuacao()          { return pontuacao; }
    public int getDicasUsadas()        { return dicasUsadas; }
    public int getQuestaoAtualNumero() { return questaoAtual + 1; }
}