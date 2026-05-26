package quizquimica.model;

import java.util.List;

public class Questao {

    private int idQuestao;
    private String enunciado;
    private String imagemUrl;
    private String dificuldade;
    private String dica;
    private List<Alternativa> alternativas;
    private String tipo;

    public Questao() {}

    public Questao(int idQuestao, String enunciado, String imagemUrl, String dificuldade, String dica, String tipo) {
        this.idQuestao = idQuestao;
        this.enunciado = enunciado;
        this.imagemUrl = imagemUrl;
        this.dificuldade = dificuldade;
        this.dica = dica;
        this.tipo = tipo;
    }

    public int getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(int idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public List<Alternativa> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<Alternativa> alternativas) {
        this.alternativas = alternativas;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Questao{id=" + idQuestao + ", dificuldade=" + dificuldade + ", enunciado=" + enunciado + "}";
    }
}