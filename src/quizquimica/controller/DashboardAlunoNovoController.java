package quizquimica.controller;

import java.util.List;

import javax.swing.JOptionPane;

import quizquimica.dao.PartidaDAO;
import quizquimica.model.Partida;
import quizquimica.model.Sessao;
import quizquimica.util.Constantes;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.PopupNivelDesbloqueado;
import quizquimica.view.TelaLogin;
import quizquimica.view.TelaQuiz;

public class DashboardAlunoNovoController {

    private final DashboardAlunoNovo view;
    private final PartidaDAO partidaDAO = new PartidaDAO();

    public static final String QUIZ_EXPERIMENTOS = "Experimentos Químicos";
    public static final String QUIZ_MATERIAIS    = "Materiais do laboratório";
    public static final String QUIZ_SEGURANCA    = "Equipamentos de segurança";

    public DashboardAlunoNovoController(DashboardAlunoNovo view) {
        this.view = view;
        configurarBotoes();
        configurarEventos();
        carregarDesempenho();
        aplicarBloqueios(); 
    }

    private void configurarEventos() {
        view.getBtnParticiparExperimentos().addActionListener(e -> iniciarQuiz(QUIZ_EXPERIMENTOS));
        view.getBtnParticiparMateriaisLab().addActionListener(e -> iniciarQuiz(QUIZ_MATERIAIS));
        view.getBtnParticiparSeguranca().addActionListener(e -> iniciarQuiz(QUIZ_SEGURANCA));
        view.getBtnSair().addActionListener(e -> sair());
    }

    // Verifica aproveitamento e habilita/desabilita botões
    private void aplicarBloqueios() {
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();

        double aprovFacil = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelFacil);
        double aprovMedio = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelMedio);

        boolean medioBloqueado   = aprovFacil < Constantes.pontuacaoMin;
        boolean dificilBloqueado = aprovMedio < Constantes.pontuacaoMin;

        // Médio — visual de bloqueado mas clicável
        if (medioBloqueado) {
            view.getBtnParticiparMateriaisLab().setBackground(new java.awt.Color(150, 150, 150));
            view.getBtnParticiparMateriaisLab().setText("🔒 Bloqueado");
        }

        // Difícil — visual de bloqueado mas clicável
        if (dificilBloqueado) {
            view.getBtnParticiparSeguranca().setBackground(new java.awt.Color(150, 150, 150));
            view.getBtnParticiparSeguranca().setText("🔒 Bloqueado");
        }
    }

   private void iniciarQuiz(String categoria) {
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();

        // Verifica se o nível está bloqueado antes de iniciar
        if (categoria.equals(QUIZ_MATERIAIS)) {
            double aprovFacil = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelFacil);
            if (aprovFacil < Constantes.pontuacaoMin) {
                JOptionPane.showMessageDialog(
                    view,
                    "Você precisa atingir 70% de acertos no nível Fácil para desbloquear este nível!",
                    "Nível bloqueado 🔒",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        if (categoria.equals(QUIZ_SEGURANCA)) {
            double aprovMedio = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelMedio);
            if (aprovMedio < Constantes.pontuacaoMin) {
                JOptionPane.showMessageDialog(
                    view,
                    "Você precisa atingir 70% de acertos no nível Médio para desbloquear este nível!",
                    "Nível bloqueado 🔒",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        String dificuldade = resolverDificuldade(categoria);
        TelaQuiz telaQuiz = new TelaQuiz(dificuldade);
        telaQuiz.setTitle("QuiZmica - " + categoria);
        telaQuiz.setVisible(true);
        view.dispose();
    }

    
    public static String resolverDificuldade(String categoria) {
        switch (categoria) {
            case QUIZ_EXPERIMENTOS: return Constantes.nivelFacil;
            case QUIZ_MATERIAIS:    return Constantes.nivelMedio;
            case QUIZ_SEGURANCA:    return Constantes.nivelDificil;
            default:                return Constantes.nivelFacil;
        }
    }

    public static void verificarDesbloqueio(
            java.awt.Frame parent, int idUsuario, String nivelJogado) {

        PartidaDAO dao = new PartidaDAO();

        if (nivelJogado.equals(Constantes.nivelFacil)) {
            double aprovAtual = dao.aproveitamentoNoNivel(idUsuario, Constantes.nivelFacil);
            int aprovadas = dao.contarPartidasAprovadas(idUsuario, Constantes.nivelFacil);

            // Mostra popup só na primeira vez que passa de 70%
            if (aprovAtual >= Constantes.pontuacaoMin && aprovadas == 1) {
                new PopupNivelDesbloqueado(parent, "Médio").setVisible(true);
            }

        } else if (nivelJogado.equals(Constantes.nivelMedio)) {
            double aprovAtual = dao.aproveitamentoNoNivel(idUsuario, Constantes.nivelMedio);
            int aprovadas = dao.contarPartidasAprovadas(idUsuario, Constantes.nivelMedio);

            if (aprovAtual >= Constantes.pontuacaoMin && aprovadas == 1) {
                new PopupNivelDesbloqueado(parent, "Difícil").setVisible(true);
            }
        }
    }

    private void carregarDesempenho() {
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();
        List<Partida> partidas = new PartidaDAO().buscarPorAluno(idUsuario);

        if (partidas.isEmpty()) {
            configurarProgressBar(view.getProgressAcertos(),   0, "0%");
            configurarProgressBar(view.getProgressErros(),     0, "0%");
            configurarProgressBar(view.getProgressProgresso(), 0, "0%");
            return;
        }

        // Usa o melhor aproveitamento no nível fácil como referência de acertos
        double melhorAprov = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelFacil);

        int acertos   = (int) Math.round(melhorAprov * 100);
        int erros     = 100 - acertos;
        int progresso = Math.min(partidas.size() * 10, 100);

        configurarProgressBar(view.getProgressAcertos(),   acertos,   acertos   + "%");
        configurarProgressBar(view.getProgressErros(),     erros,     erros     + "%");
        configurarProgressBar(view.getProgressProgresso(), progresso, progresso + "%");
    }

    private void configurarProgressBar(javax.swing.JProgressBar bar, int valor, String texto) {
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(valor);
        bar.setString(texto);
        bar.setStringPainted(true);
    }

    private void configurarBotoes() {
        view.getBtnParticiparExperimentos().setOpaque(true);
        view.getBtnParticiparExperimentos().setBorderPainted(false);
        view.getBtnParticiparExperimentos().setFocusPainted(false);

        view.getBtnParticiparMateriaisLab().setOpaque(true);
        view.getBtnParticiparMateriaisLab().setBorderPainted(false);
        view.getBtnParticiparMateriaisLab().setFocusPainted(false);

        view.getBtnParticiparSeguranca().setOpaque(true);
        view.getBtnParticiparSeguranca().setBorderPainted(false);
        view.getBtnParticiparSeguranca().setFocusPainted(false);

        view.getBtnSair().setOpaque(true);
        view.getBtnSair().setBorderPainted(false);
        view.getBtnSair().setFocusPainted(false);
    }

    private void sair() {
        new TelaLogin().setVisible(true);
        view.dispose();
    }
}