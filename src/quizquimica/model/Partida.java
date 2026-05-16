package quizquimica.model;

import java.time.LocalDate;

public class Partida {

    private int idPartida;
    private int idUsuario;
    private String nivel;
    private int pontuacao;
    private LocalDate data;
    private int dicasUsadas;

    public Partida() {}

    public Partida(int idPartida, int idUsuario, String nivel, int pontuacao, LocalDate data, int dicasUsadas) {
        this.idPartida = idPartida;
        this.idUsuario = idUsuario;
        this.nivel = nivel;
        this.pontuacao = pontuacao;
        this.data = data;
        this.dicasUsadas = dicasUsadas;
    }

    public int getIdPartida(){ 
        return idPartida; 
    }

    public void setIdPartida(int idPartida){ 
        this.idPartida = idPartida;
    }

    public int getIdUsuario(){
         return idUsuario; 
    }
    public void setIdUsuario(int idUsuario){ 
        this.idUsuario = idUsuario;
    }

    public String getNivel(){ 
        return nivel; 
    }
    public void setNivel(String nivel){ 
        this.nivel = nivel; 
    }

    public int getPontuacao() { 
        return pontuacao;
    }

    public void setPontuacao(int pontuacao){ 
        this.pontuacao = pontuacao; 
    }

    public LocalDate getData() {
        return data;
    
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public int getDicasUsadas(){ 
        return dicasUsadas;
    }

    public void setDicasUsadas(int dicasUsadas) { 
        this.dicasUsadas = dicasUsadas; 
    }

    @Override
    public String toString() {
        return "Partida{id=" + idPartida + ", idUsuario=" + idUsuario + ", nivel=" + nivel + ", pontuacao=" + pontuacao + ", data=" + data + ", dicasUsadas=" + dicasUsadas + "}";
    }
}