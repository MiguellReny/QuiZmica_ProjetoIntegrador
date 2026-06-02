package quizquimica.controller;

import quizquimica.service.PartidaService;
import quizquimica.model.Questao;
import quizquimica.model.Partida;

public class PartidaControllerBack {

    private final PartidaService partidaService = new PartidaService();
    
    public boolean iniciarPartida(int idUsuario){
        boolean iniciou = partidaService.iniciarPartida(idUsuario);
        return iniciou;
    }

    public Questao getQuestaoAtual(){
        return partidaService.getQuestaoAtual();
    }

    public boolean responder(int idAlternativa, boolean usouDica){
        boolean acertou = partidaService.responder(idAlternativa, usouDica);
        return acertou;
    }

    public boolean podePedirDica(){
        boolean pode = partidaService.podePedirDica();
        return pode;
    }

    public String getDicaAtual(){
        return partidaService.getDicaAtual();
    }

    public Partida finalizarPartida(){
        return partidaService.finalizarPartida();
    }

}