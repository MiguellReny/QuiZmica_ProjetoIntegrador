package quizquimica.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.model.Sessao;
import quizquimica.service.PartidaService;
import quizquimica.util.Constantes;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.PopUpDicaQuiz;
import quizquimica.view.PopupQuizFinalizado;
import quizquimica.view.TelaQuiz;

public class TelaQuizController {

    private final TelaQuiz view;
    private final String nivel;
    private final PartidaService partidaService = new PartidaService();

    private List<Questao> questoes = new ArrayList<>();
    private String[] respostasSelecionadas;
    private boolean[] usouDica;
    private int dicasUsadasTotal = 0;

    private int indiceQuestaoAtual = 0;

    private final Color COR_PADRAO = new Color(255, 255, 255);
    private final Color COR_SELECIONADA = new Color(179, 40, 36);
    private final Color COR_TEXTO_PADRAO = new Color(20, 25, 45);
    private final Color COR_TEXTO_SELECIONADO = new Color(255, 255, 255);

    public TelaQuizController(TelaQuiz view, String nivel) {
        this.view = view;
        this.nivel = nivel;

        // 1. Inicia a partida PRIMEIRO (isso monta as questões internamente)
        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();
        partidaService.iniciarPartida(idUsuario, nivel);

        // 2. Agora busca a lista já montada
        questoes = new ArrayList<>(partidaService.getQuestoesDaPartida());
        Collections.shuffle(questoes);

        if (questoes.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nenhuma questão encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        // 3. Aloca arrays com tamanho correto
        respostasSelecionadas = new String[questoes.size()];
        usouDica = new boolean[questoes.size()];

        configurarEventos();
        carregarQuestaoAtual();
    }

        private void abrirDica() {
            if (dicasUsadasTotal >= Constantes.maximoDicas) {
                JOptionPane.showMessageDialog(view, "Você já usou todas as suas dicas!");
                return;
            }

            usouDica[indiceQuestaoAtual] = true;
            dicasUsadasTotal++;

            Questao q = questoes.get(indiceQuestaoAtual);
            PopUpDicaQuiz popup = new PopUpDicaQuiz(view, true);
            popup.setDadosDica(
                    q.getPersonagem() != null ? q.getPersonagem() : "mendeleev",
                    q.getDica() != null ? q.getDica() : "Sem dica disponível"
            );
            popup.setVisible(true);
        }


    private void configurarEventos() {

        view.getBtnDica().addActionListener(e -> abrirDica());
        view.getBtnVoltar().addActionListener(e -> voltar());
        view.getBtnProxima().addActionListener(e -> proximaQuestao());

        view.getBtnAlternativaA().addActionListener(e -> selecionar("A"));
        view.getBtnAlternativaB().addActionListener(e -> selecionar("B"));
        view.getBtnAlternativaC().addActionListener(e -> selecionar("C"));
        view.getBtnAlternativaD().addActionListener(e -> selecionar("D"));
    }

    private void carregarQuestaoAtual() {
        if (questoes.isEmpty()) return;

        Questao q = questoes.get(indiceQuestaoAtual);
        System.out.println("[DEBUG] questão: " + q.getIdQuestao() + " - " + q.getEnunciado());

        view.getLblQuestaoAtual().setText("QUESTÃO " + (indiceQuestaoAtual + 1));
        view.getLblProgresso().setText((indiceQuestaoAtual + 1) + " / " + questoes.size());
        view.getLblEnunciado().setText(q.getEnunciado());

        carregarImagemEnunciado(q.getImagemUrl());

        List<Alternativa> alts = q.getAlternativas();
        for (Alternativa a : alts) {
            System.out.println("[DEBUG] alt: " + a.getIdAlternativa()
                    + " - " + a.getAlternativa()
                    + " - idQuestao: " + a.getIdQuestao());
        }

        if (alts.size() >= 4) {
            view.getBtnAlternativaA().setText(alts.get(0).getAlternativa());
            view.getBtnAlternativaB().setText(alts.get(1).getAlternativa());
            view.getBtnAlternativaC().setText(alts.get(2).getAlternativa());
            view.getBtnAlternativaD().setText(alts.get(3).getAlternativa());
        }

        restaurarSelecao();
        atualizarBotaoProxima();
    }

    private void carregarImagemEnunciado(String urlImagem) {
        if (urlImagem != null && !urlImagem.isBlank()) {
            try {
                java.net.URL url = java.net.URI.create(urlImagem).toURL();
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setInstanceFollowRedirects(true);
                java.net.HttpURLConnection.setFollowRedirects(true);

                int status = conn.getResponseCode();
                System.out.println("[DEBUG] HTTP status imagem: " + status + " | url: " + urlImagem);

                if (status == 200) {
                    byte[] bytes = conn.getInputStream().readAllBytes();
                    System.out.println("[DEBUG] bytes recebidos: " + bytes.length);
                    java.awt.image.BufferedImage buffered =
                        javax.imageio.ImageIO.read(new java.io.ByteArrayInputStream(bytes));
                    if (buffered != null) {
                        java.awt.Image img = buffered.getScaledInstance(410, 250, java.awt.Image.SCALE_SMOOTH);
                        view.getLblImagemQuestao().setIcon(new javax.swing.ImageIcon(img));
                        view.getLblImagemQuestao().setVisible(true);
                        return;
                    }
                }
            } catch (Exception ex) {
                System.out.println("[DEBUG] Erro ao carregar imagem: " + ex.getMessage());
            }
        }
        exibirLogoNoLugarDaImagem();
        view.getLblImagemQuestao().setVisible(true);
    }

        private void exibirLogoNoLugarDaImagem() {
        // ← substitua este aqui
        javax.swing.ImageIcon logo = new javax.swing.ImageIcon(
            getClass().getResource("/quizquimica/images/quizmica.png")
        );
        java.awt.Image img = logo.getImage().getScaledInstance(
                410, 250, java.awt.Image.SCALE_SMOOTH
        );
        view.getLblImagemQuestao().setIcon(new javax.swing.ImageIcon(img));
    }

    private void selecionar(String letra) {
        respostasSelecionadas[indiceQuestaoAtual] = letra;

        resetBotoes();

        switch (letra) {
            case "A" -> destacar(view.getBtnAlternativaA());
            case "B" -> destacar(view.getBtnAlternativaB());
            case "C" -> destacar(view.getBtnAlternativaC());
            case "D" -> destacar(view.getBtnAlternativaD());
        }
    }

    private void restaurarSelecao() {
        resetBotoes();

        String r = respostasSelecionadas[indiceQuestaoAtual];
        if (r != null) selecionar(r);
    }

    private void resetBotoes() {
        reset(view.getBtnAlternativaA());
        reset(view.getBtnAlternativaB());
        reset(view.getBtnAlternativaC());
        reset(view.getBtnAlternativaD());
    }

    private void reset(JButton b) {
        b.setBackground(COR_PADRAO);
        b.setForeground(COR_TEXTO_PADRAO);
    }

    private void destacar(JButton b) {
        b.setBackground(COR_SELECIONADA);
        b.setForeground(COR_TEXTO_SELECIONADO);
    }

    private void proximaQuestao() {

        if (respostasSelecionadas[indiceQuestaoAtual] == null) {
            JOptionPane.showMessageDialog(view, "Selecione uma alternativa.");
            return;
        }

        if (indiceQuestaoAtual < questoes.size() - 1) {
            indiceQuestaoAtual++;
            carregarQuestaoAtual();
        } else {
            finalizar();
        }
    }

    private void voltar() {

        if (indiceQuestaoAtual > 0) {
            indiceQuestaoAtual--;
            carregarQuestaoAtual();
            return;
        }

        int r = JOptionPane.showConfirmDialog(view,
                "Sair do quiz?",
                "Sair",
                JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            new DashboardAlunoNovo().setVisible(true);
            view.dispose();
        }
    }

    private void finalizar() {
        int acertos = 0;
        int erros   = 0;

        for (int i = 0; i < questoes.size(); i++) {
            Questao q = questoes.get(i);
            String respostaAluno = respostasSelecionadas[i];
            List<Alternativa> alts = q.getAlternativas();

            if (respostaAluno != null) {
                int idx = switch (respostaAluno) {
                    case "A" -> 0; case "B" -> 1;
                    case "C" -> 2; case "D" -> 3;
                    default  -> -1;
                };
                if (idx >= 0 && idx < alts.size()) {
                    Alternativa alt = alts.get(idx);
                    partidaService.responder(q.getIdQuestao(), alt.getIdAlternativa(), usouDica[i]);
                    if (alt.isAlternativaCorreta()) acertos++;
                    else erros++;
                }
            }
        }

        partidaService.finalizarPartida();

        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();
        DashboardAlunoNovoController.verificarDesbloqueio(view, idUsuario, nivel);

        PopupQuizFinalizado popup = new PopupQuizFinalizado(view, true);
        popup.setDadosResultado(nivel, acertos, erros);
        popup.getBtnVoltar().addActionListener(e -> {
            popup.dispose();
            new DashboardAlunoNovo().setVisible(true);
            view.dispose();
        });
        popup.setVisible(true);
    }

    private void atualizarBotaoProxima() {
        if (indiceQuestaoAtual == questoes.size() - 1) {
            view.getBtnProxima().setText("FINALIZAR");
        } else {
            view.getBtnProxima().setText("PRÓXIMA");
        }
    }
}