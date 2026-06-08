package quizquimica.controller;

import java.awt.Color;
import java.awt.Image;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

    private List<List<Alternativa>> alternativasEmbaralhadas = new ArrayList<>();

    private String[] respostasSelecionadas;
    private boolean[] usouDica;
    private int dicasUsadasTotal = 0;
    private int indiceQuestaoAtual = 0;
    private boolean[] questaoCorrigida;
    private final Color COR_ACERTO = new Color(46, 204, 113);
    private final Color COR_ERRO = new Color(231, 76, 60);

    private final Color COR_PADRAO            = new Color(255, 255, 255);
    private final Color COR_SELECIONADA       = new Color(179, 40, 36);
    private final Color COR_TEXTO_PADRAO      = new Color(20, 25, 45);
    private final Color COR_TEXTO_SELECIONADO = new Color(255, 255, 255);

    public TelaQuizController(TelaQuiz view, String nivel) {
        this.view  = view;
        this.nivel = nivel;

        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();
        partidaService.iniciarPartida(idUsuario, nivel);

        questoes = new ArrayList<>(partidaService.getQuestoesDaPartida());

        questaoCorrigida = new boolean[questoes.size()];

        if (questoes.isEmpty()) {
            JOptionPane.showMessageDialog(view,
                "Nenhuma questão encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        respostasSelecionadas = new String[questoes.size()];
        usouDica              = new boolean[questoes.size()];

        // FIX 1: embaralha alternativas uma vez por questão e guarda a ordem fixa
        for (Questao q : questoes) {
            List<Alternativa> copia = new ArrayList<>(q.getAlternativas());
            Collections.shuffle(copia);
            alternativasEmbaralhadas.add(copia);
        }

        configurarEventos();
        carregarQuestaoAtual();
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
        view.getLblQuestaoAtual().setText("QUESTÃO " + (indiceQuestaoAtual + 1));
        view.getLblProgresso().setText((indiceQuestaoAtual + 1) + " / " + questoes.size());
        view.getLblEnunciado().setText(q.getEnunciado());

        // FIX 2: imagem do enunciado em thread separada para não travar a UI
        carregarImagemEnunciadoAsync(q.getImagemUrl());

        List<Alternativa> alts = alternativasEmbaralhadas.get(indiceQuestaoAtual);

        JButton[] btns = {
            view.getBtnAlternativaA(),
            view.getBtnAlternativaB(),
            view.getBtnAlternativaC(),
            view.getBtnAlternativaD()
        };

        if (alts.size() >= 4) {
            for (int i = 0; i < 4; i++) {
                Alternativa alt   = alts.get(i);
                String textoAlt   = alt.getAlternativa();
                String imgUrl     = alt.getAlternativaImagem();

                // FIX — imagem da alternativa: carrega do banco ou mostra texto
                if (imgUrl != null && !imgUrl.isBlank()) {

                    btns[i].setIcon(null);
                    btns[i].setText(textoAlt);

                    carregarImagemBotaoAsync(btns[i], imgUrl);

                } else {

                    btns[i].setIcon(null);
                    btns[i].setText(textoAlt);
                }
            }
        }

        restaurarSelecao();
        atualizarBotaoProxima();
    }

    // FIX 2: download da imagem do enunciado fora da EDT
    private void carregarImagemEnunciadoAsync(String urlImagem) {
        // Limpa imagem anterior imediatamente
        javax.swing.SwingUtilities.invokeLater(() ->
            view.getLblImagemQuestao().setIcon(null));

        if (urlImagem == null || urlImagem.isBlank()) {
            javax.swing.SwingUtilities.invokeLater(this::exibirLogoNoLugarDaImagem);
            return;
        }

        new Thread(() -> {
            try {
                URL url = URI.create(urlImagem).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setInstanceFollowRedirects(true);
                HttpURLConnection.setFollowRedirects(true);

                if (conn.getResponseCode() == 200) {
                    byte[] bytes = conn.getInputStream().readAllBytes();
                    java.awt.image.BufferedImage buffered =
                        ImageIO.read(new java.io.ByteArrayInputStream(bytes));
                    if (buffered != null) {
                        Image img = buffered.getScaledInstance(410, 250, Image.SCALE_SMOOTH);
                        javax.swing.SwingUtilities.invokeLater(() -> {
                            view.getLblImagemQuestao().setIcon(new ImageIcon(img));
                            view.getLblImagemQuestao().setVisible(true);
                        });
                        return;
                    }
                }
            } catch (Exception ex) {
                System.out.println("[DEBUG] Erro ao carregar imagem enunciado: " + ex.getMessage());
            }
            javax.swing.SwingUtilities.invokeLater(this::exibirLogoNoLugarDaImagem);
        }).start();
    }

    private void carregarImagemBotaoAsync(JButton btn, String urlStr) {

        new Thread(() -> {

            try {

                URL url = URI.create(urlStr).toURL();

                HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

                conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"
                );

                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setInstanceFollowRedirects(true);

                HttpURLConnection.setFollowRedirects(true);

                if (conn.getResponseCode() == 200) {

                    byte[] bytes = conn.getInputStream().readAllBytes();

                    java.awt.image.BufferedImage buffered =
                        ImageIO.read(
                            new java.io.ByteArrayInputStream(bytes)
                        );

                    if (buffered != null) {

                        int larguraMax = 150;
                        int alturaMax = 50;

                        int larguraOriginal = buffered.getWidth();
                        int alturaOriginal = buffered.getHeight();

                        double proporcao = Math.min(
                            (double) larguraMax / larguraOriginal,
                            (double) alturaMax / alturaOriginal
                        );

                        int novaLargura =
                            (int) (larguraOriginal * proporcao);

                        int novaAltura =
                            (int) (alturaOriginal * proporcao);

                        Image img = buffered.getScaledInstance(
                            novaLargura,
                            novaAltura,
                            Image.SCALE_SMOOTH
                        );

                        javax.swing.SwingUtilities.invokeLater(() -> {
                            btn.setIcon(new ImageIcon(img));
                        });

                        return;
                    }
                }

            } catch (Exception ex) {

                System.out.println(
                    "[DEBUG] Erro imagem alternativa: "
                    + ex.getMessage()
                );
            }

            javax.swing.SwingUtilities.invokeLater(() -> {
                btn.setIcon(null);
            });

        }).start();
    }

    private void exibirLogoNoLugarDaImagem() {
        ImageIcon logo = new ImageIcon(
            getClass().getResource("/quizquimica/images/quizmica.png"));
        Image img = logo.getImage().getScaledInstance(410, 250, Image.SCALE_SMOOTH);
        view.getLblImagemQuestao().setIcon(new ImageIcon(img));
        view.getLblImagemQuestao().setVisible(true);
    }

    private void selecionar(String letra) {
        if (questaoCorrigida[indiceQuestaoAtual]) {
            return;
        }

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
            q.getDica()       != null ? q.getDica()       : "Sem dica disponível"
        );
        popup.setVisible(true);
    }

   private void proximaQuestao() {
        if (respostasSelecionadas[indiceQuestaoAtual] == null) {
            JOptionPane.showMessageDialog(
                view,
                "Selecione uma alternativa."
            );
            return;
        }

        if (!questaoCorrigida[indiceQuestaoAtual]) {

            corrigirQuestaoAtual();

            questaoCorrigida[indiceQuestaoAtual] = true;

            return;
        }

        if (indiceQuestaoAtual < questoes.size() - 1) {

            indiceQuestaoAtual++;
            carregarQuestaoAtual();

        } else {

            finalizar();
        }
    }

    private void corrigirQuestaoAtual() {

        String resposta = respostasSelecionadas[indiceQuestaoAtual];

        int indiceAlternativa = switch (resposta) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            default -> -1;
        };

        List<Alternativa> alts =
            alternativasEmbaralhadas.get(indiceQuestaoAtual);

        Alternativa escolhida = alts.get(indiceAlternativa);

                JButton[] botoes = {
            view.getBtnAlternativaA(),
            view.getBtnAlternativaB(),
            view.getBtnAlternativaC(),
            view.getBtnAlternativaD()
        };

        if (escolhida.isAlternativaCorreta()) {

            botoes[indiceAlternativa].setBackground(COR_ACERTO);

            JOptionPane.showMessageDialog(
                view,
                "✅ Você acertou!"
            );

        } else {

            botoes[indiceAlternativa].setBackground(COR_ERRO);

            for (int i = 0; i < alts.size(); i++) {
                if (alts.get(i).isAlternativaCorreta()) {
                    botoes[i].setBackground(COR_ACERTO);
                    break;
                }
            }

            JOptionPane.showMessageDialog(
                view,
                "❌ Você errou!"
            );
        }
    }

    private void voltar() {
        if (indiceQuestaoAtual > 0) {
            indiceQuestaoAtual--;
            carregarQuestaoAtual();
            return;
        }
        int r = JOptionPane.showConfirmDialog(view, "Sair do quiz?", "Sair",
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
            // FIX 1: usa lista embaralhada, não q.getAlternativas()
            List<Alternativa> alts = alternativasEmbaralhadas.get(i);

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
        int pontuacao = partidaService.getPontuacao();

        int idUsuario = Sessao.getUsuarioLogado().getIdUsuario();
        DashboardAlunoNovoController.verificarDesbloqueio(view, idUsuario, nivel);

        PopupQuizFinalizado popup = new PopupQuizFinalizado(view, true);
        // FIX 5: passa pontuação real para o popup
        popup.setDadosResultado(nivel, acertos, erros, pontuacao);
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