package quizquimica.controller;

import java.util.List;

import javax.swing.JOptionPane;

import quizquimica.dao.PartidaDAO;
import quizquimica.model.Partida;
import quizquimica.model.Sessao;
import quizquimica.model.Usuario;
import quizquimica.util.Constantes;
import quizquimica.dao.AlunoDAO;
import quizquimica.model.Aluno;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.DesempenhoAluno;
import quizquimica.controller.DesempenhoAlunoController;
import quizquimica.view.PopupNivelDesbloqueado;
import quizquimica.view.TelaLogin;
import quizquimica.view.TelaQuiz;

public class DashboardAlunoNovoController {

    private final DashboardAlunoNovo view;
    private final PartidaDAO partidaDAO = new PartidaDAO();

    public static final String QUIZ_EXPERIMENTOS = "Experimentos Químicos";
    public static final String QUIZ_MATERIAIS    = "Materiais do laboratório";
    public static final String QUIZ_SEGURANCA    = "Equipamentos de segurança";

    // FIX 4: textos originais dos botões guardados para restaurar quando desbloquear
    private static final String TEXTO_MATERIAIS = "▷ Participar";
    private static final String TEXTO_SEGURANCA = "▷ Participar";
    private static final java.awt.Color COR_BTN_MATERIAIS = new java.awt.Color(5, 68, 75);
    private static final java.awt.Color COR_BTN_SEGURANCA = new java.awt.Color(5, 68, 75);

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
        view.getBtnVerDesempenho().addActionListener(e -> abrirDesempenho());
    }

    // FIX 4: aplica o estado correto (bloqueado ou desbloqueado) para os dois níveis
    private void aplicarBloqueios() {
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();

        double aprovFacil = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelFacil);
        double aprovMedio = partidaDAO.aproveitamentoNoNivel(idUsuario, Constantes.nivelMedio);

        boolean medioBloqueado   = aprovFacil < Constantes.pontuacaoMin;
        boolean dificilBloqueado = aprovMedio < Constantes.pontuacaoMin;

        if (medioBloqueado) {
            view.getBtnParticiparMateriaisLab().setBackground(new java.awt.Color(150, 150, 150));
            view.getBtnParticiparMateriaisLab().setText("🔒 Bloqueado");
        } else {
            // Restaura visual correto caso tenha desbloqueado após a última sessão
            view.getBtnParticiparMateriaisLab().setBackground(COR_BTN_MATERIAIS);
            view.getBtnParticiparMateriaisLab().setText(TEXTO_MATERIAIS);
        }

        if (dificilBloqueado) {
            view.getBtnParticiparSeguranca().setBackground(new java.awt.Color(150, 150, 150));
            view.getBtnParticiparSeguranca().setText("🔒 Bloqueado");
        } else {
            view.getBtnParticiparSeguranca().setBackground(COR_BTN_SEGURANCA);
            view.getBtnParticiparSeguranca().setText(TEXTO_SEGURANCA);
        }
    }

    private void iniciarQuiz(String categoria) {
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();

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

    // FIX 2: popup dispara quando cruza 70% nesta partida, não quando count==1
    public static void verificarDesbloqueio(
            java.awt.Frame parent, int idUsuario, String nivelJogado) {

        PartidaDAO dao = new PartidaDAO();

        if (nivelJogado.equals(Constantes.nivelFacil)) {
            double aprovAntes = dao.aproveitamentoAnteriorNoNivel(idUsuario, Constantes.nivelFacil);
            double aprovAgora = dao.aproveitamentoNoNivel(idUsuario, Constantes.nivelFacil);

            if (aprovAntes < Constantes.pontuacaoMin && aprovAgora >= Constantes.pontuacaoMin) {
                new PopupNivelDesbloqueado(parent, "Médio").setVisible(true);
            }

        } else if (nivelJogado.equals(Constantes.nivelMedio)) {
            double aprovAntes = dao.aproveitamentoAnteriorNoNivel(idUsuario, Constantes.nivelMedio);
            double aprovAgora = dao.aproveitamentoNoNivel(idUsuario, Constantes.nivelMedio);

            if (aprovAntes < Constantes.pontuacaoMin && aprovAgora >= Constantes.pontuacaoMin) {
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

    private void abrirDesempenho() {
        Usuario usuario = Sessao.getUsuarioLogado();
        // Constrói um Aluno a partir do usuário logado
        Aluno aluno = new Aluno(
            usuario.getIdUsuario(),
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getSenha(),
            usuario.getTurma() != null ? usuario.getTurma() : ""
        );
        DesempenhoAluno tela = new DesempenhoAluno(null, aluno.getNome());
        new DesempenhoAlunoController(tela, aluno, "aluno");
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        view.dispose();
    }

    private void sair() {
        new TelaLogin().setVisible(true);
        view.dispose();
    }
}