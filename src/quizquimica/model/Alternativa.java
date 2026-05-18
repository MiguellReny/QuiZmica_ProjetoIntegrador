package quizquimica.model;

public class Alternativa {

    private int idAlternativa;
    private String alternativa;
    private boolean alternativaCorreta;
    private int idQuestao;

    public Alternativa() {}

    public Alternativa(int idAlternativa, String alternativa, boolean alternativaCorreta, int idQuestao) {
        this.idAlternativa = idAlternativa;
        this.alternativa = alternativa;
        this.alternativaCorreta = alternativaCorreta;
        this.idQuestao = idQuestao;
    }

    public int getIdAlternativa() {
         return idAlternativa; 
    }

    public void setIdAlternativa(int idAlternativa) {
         this.idAlternativa = idAlternativa; 
    }

    public String getAlternativa(){ 
        return alternativa; 
    }

    public void setAlternativa(String alternativa) {
         this.alternativa = alternativa; 
    }

    public boolean isAlternativaCorreta() {
         return alternativaCorreta; 
    }

    public void setAlternativaCorreta(boolean alternativaCorreta) {
         this.alternativaCorreta = alternativaCorreta; 
    }

    public int getIdQuestao() {
         return idQuestao; 
    }

    public void setIdQuestao(int idQuestao) { 
        this.idQuestao = idQuestao; 
    }

    @Override
    public String toString() {
        return "Alternativa{id=" + idAlternativa + ", alternativa=" + alternativa + ", correta=" + alternativaCorreta + "}";
    }
}