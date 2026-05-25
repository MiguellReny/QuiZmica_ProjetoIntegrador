package quizquimica.model;

import java.util.List;
import java.util.Map;

public class Desempenho {

    private int idUsuario;
    private List<Partida> partidas;
    private int totalAcertos;
    private int totalErros;
    private Map<Integer, Integer> questoesMaisErradas;

    public Desempenho(){}

    public Desempenho(int idUsuario, List<Partida> partidas, int totalAcertos, int totalErros, Map<Integer, Integer> questoesMaisErradas){
        this.idUsuario = idUsuario;
        this.partidas = partidas;
        this.totalAcertos = totalAcertos;
        this.totalErros = totalErros;
        this.questoesMaisErradas = questoesMaisErradas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public int getTotalAcertos() {
        return totalAcertos;
    }

    public void setTotalAcertos(int totalAcertos) {
        this.totalAcertos = totalAcertos;
    }

    public int getTotalErros() {
        return totalErros;
    }

    public void setTotalErros(int totalErros) {
        this.totalErros = totalErros;
    }

    public Map<Integer, Integer> getQuestoesMaisErradas() {
        return questoesMaisErradas;
    }

    public void setQuestoesMaisErradas(Map<Integer, Integer> questoesMaisErradas) {
        this.questoesMaisErradas = questoesMaisErradas;
    }
    
}