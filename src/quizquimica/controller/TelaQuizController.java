package quizquimica.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.PopUpDicaQuiz;
import quizquimica.view.PopupQuizFinalizado;
import quizquimica.view.TelaQuiz;

public class TelaQuizController {

    private final TelaQuiz view;
    private final String categoriaQuiz;

    private int indiceQuestaoAtual = 0;

    private List<QuestaoQuiz> questoes;
    private String[] respostasSelecionadas;

    private final Color COR_PADRAO = new Color(255, 255, 255);
    private final Color COR_SELECIONADA = new Color(179, 40, 36);
    private final Color COR_TEXTO_PADRAO = new Color(20, 25, 45);
    private final Color COR_TEXTO_SELECIONADO = new Color(255, 255, 255);

    public TelaQuizController(TelaQuiz view, String categoriaQuiz) {
        this.view = view;
        this.categoriaQuiz = categoriaQuiz;

        carregarQuestoesPorCategoria();
        respostasSelecionadas = new String[questoes.size()];

        configurarEventos();
        carregarQuestaoAtual();
    }

    private void configurarEventos() {
        view.getBtnDica().addActionListener(e -> abrirDica());

        view.getBtnVoltar().addActionListener(e -> voltar());

        view.getBtnProxima().addActionListener(e -> proximaQuestao());

        view.getBtnAlternativaA().addActionListener(e -> selecionarAlternativa("A"));
        view.getBtnAlternativaB().addActionListener(e -> selecionarAlternativa("B"));
        view.getBtnAlternativaC().addActionListener(e -> selecionarAlternativa("C"));
        view.getBtnAlternativaD().addActionListener(e -> selecionarAlternativa("D"));
    }

    private void carregarQuestoesPorCategoria() {
        questoes = new ArrayList<>();

        if (categoriaQuiz.equals("Experimentos Químicos")) {
            questoes.add(new QuestaoQuiz(
                    "Qual material é utilizado para aquecer sólidos?",
                    "Bico de Bunsen",
                    "Béquer",
                    "Funil de decantação",
                    "Tubo de ensaio",
                    "A",
                    "MARIE CURIE",
                    "Pioneira da radioatividade",
                    "“Esse material é usado para aquecer substâncias em laboratório.”"
            ));

            questoes.add(new QuestaoQuiz(
                    "Em um experimento de filtração simples, qual material ajuda a separar sólido de líquido?",
                    "Pipeta",
                    "Funil com papel filtro",
                    "Proveta",
                    "Termômetro",
                    "B",
                    "LINUS PAULING",
                    "Químico e pesquisador",
                    "“Pense em um material que conduz a mistura e retém partículas sólidas.”"
            ));

        } else if (categoriaQuiz.equals("Materiais do laboratório")) {
            questoes.add(new QuestaoQuiz(
                    "Qual vidraria é mais indicada para medir volume com maior precisão?",
                    "Béquer",
                    "Erlenmeyer",
                    "Proveta",
                    "Placa de Petri",
                    "C",
                    "MENDELEEV",
                    "Criador da tabela periódica",
                    "“Procure a vidraria graduada usada para medir volumes.”"
            ));

            questoes.add(new QuestaoQuiz(
                    "Qual material é usado para transferir pequenas quantidades de líquido?",
                    "Pipeta",
                    "Cadinho",
                    "Tela de amianto",
                    "Tripé",
                    "A",
                    "MARIE CURIE",
                    "Pioneira da radioatividade",
                    "“Esse instrumento permite manipular líquidos em pequenas quantidades.”"
            ));

        } else if (categoriaQuiz.equals("Equipamentos de segurança")) {
            questoes.add(new QuestaoQuiz(
                    "Qual equipamento protege os olhos contra respingos químicos?",
                    "Jaleco",
                    "Óculos de proteção",
                    "Luva térmica",
                    "Máscara de tecido",
                    "B",
                    "LINUS PAULING",
                    "Químico e pesquisador",
                    "“Pense na parte do corpo mais vulnerável a respingos durante um experimento.”"
            ));

            questoes.add(new QuestaoQuiz(
                    "Qual item deve ser usado para proteger as mãos ao manusear substâncias químicas?",
                    "Luva de proteção",
                    "Funil",
                    "Bastão de vidro",
                    "Pisseta",
                    "A",
                    "MENDELEEV",
                    "Criador da tabela periódica",
                    "“Esse equipamento cria uma barreira entre sua pele e a substância.”"
            ));
        }

        if (questoes.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Nenhuma questão encontrada para este quiz.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void carregarQuestaoAtual() {
        if (questoes == null || questoes.isEmpty()) {
            return;
        }

        QuestaoQuiz questao = questoes.get(indiceQuestaoAtual);

        view.getLblQuestaoAtual().setText("QUESTÃO " + (indiceQuestaoAtual + 1));
        view.getLblProgresso().setText((indiceQuestaoAtual + 1) + " / " + questoes.size());

        view.getLblEnunciado().setText(questao.enunciado);

        view.getBtnAlternativaA().setText(questao.alternativaA);
        view.getBtnAlternativaB().setText(questao.alternativaB);
        view.getBtnAlternativaC().setText(questao.alternativaC);
        view.getBtnAlternativaD().setText(questao.alternativaD);

        restaurarAlternativaSelecionada();

        atualizarTextoBotaoProxima();

    }

    private void atualizarTextoBotaoProxima() {
     if (indiceQuestaoAtual == questoes.size() - 1) {
         view.getBtnProxima().setText("FINALIZAR");
     } else {
         view.getBtnProxima().setText("PRÓXIMA");
     }
    }

    private void selecionarAlternativa(String alternativa) {
        respostasSelecionadas[indiceQuestaoAtual] = alternativa;

        limparSelecaoAlternativas();

        if (alternativa.equals("A")) {
            destacarBotao(view.getBtnAlternativaA());
        } else if (alternativa.equals("B")) {
            destacarBotao(view.getBtnAlternativaB());
        } else if (alternativa.equals("C")) {
            destacarBotao(view.getBtnAlternativaC());
        } else if (alternativa.equals("D")) {
            destacarBotao(view.getBtnAlternativaD());
        }
    }

    private void restaurarAlternativaSelecionada() {
        limparSelecaoAlternativas();

        String resposta = respostasSelecionadas[indiceQuestaoAtual];

        if (resposta == null) {
            return;
        }

        if (resposta.equals("A")) {
            destacarBotao(view.getBtnAlternativaA());
        } else if (resposta.equals("B")) {
            destacarBotao(view.getBtnAlternativaB());
        } else if (resposta.equals("C")) {
            destacarBotao(view.getBtnAlternativaC());
        } else if (resposta.equals("D")) {
            destacarBotao(view.getBtnAlternativaD());
        }
    }

    private void limparSelecaoAlternativas() {
        resetarBotao(view.getBtnAlternativaA());
        resetarBotao(view.getBtnAlternativaB());
        resetarBotao(view.getBtnAlternativaC());
        resetarBotao(view.getBtnAlternativaD());
    }

    private void resetarBotao(JButton botao) {
        botao.setBackground(COR_PADRAO);
        botao.setForeground(COR_TEXTO_PADRAO);
        botao.setOpaque(true);
        botao.setBorderPainted(true);
    }

    private void destacarBotao(JButton botao) {
        botao.setBackground(COR_SELECIONADA);
        botao.setForeground(COR_TEXTO_SELECIONADO);
        botao.setOpaque(true);
        botao.setBorderPainted(false);
    }

    private void abrirDica() {
        if (questoes == null || questoes.isEmpty()) {
            return;
        }

        QuestaoQuiz questao = questoes.get(indiceQuestaoAtual);

        PopUpDicaQuiz popup = new PopUpDicaQuiz(view, true);

        popup.setDadosDica(
                questao.nomePersonagem,
                questao.descricaoPersonagem,
                questao.textoDica
        );

        popup.setVisible(true);
    }

    private void proximaQuestao() {
        if (questoes == null || questoes.isEmpty()) {
            return;
        }

        if (respostasSelecionadas[indiceQuestaoAtual] == null) {
            JOptionPane.showMessageDialog(
                    view,
                    "Selecione uma alternativa antes de continuar.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (indiceQuestaoAtual < questoes.size() - 1) {
            indiceQuestaoAtual++;
            carregarQuestaoAtual();
        } else {
            finalizarQuiz();
        }
    }

    private void voltar() {
     if (indiceQuestaoAtual > 0) {
        indiceQuestaoAtual--;
        carregarQuestaoAtual();
     } else {
        int resposta = JOptionPane.showConfirmDialog(
                view,
                "Deseja sair do quiz?\nSeu progresso será perdido.",
                "Sair do quiz",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            DashboardAlunoNovo dashboard = new DashboardAlunoNovo();
            dashboard.setVisible(true);

            view.dispose();
        }
      }
    }

    private void finalizarQuiz() {
     int acertos = calcularAcertos();
     int total = questoes.size();
     int erros = total - acertos;

     PopupQuizFinalizado popup = new PopupQuizFinalizado(view, true);
     popup.setDadosResultado(categoriaQuiz, acertos, erros);

     popup.getBtnRefazerQuiz().addActionListener(e -> {
        popup.dispose();
        reiniciarQuiz();
      });

     popup.getBtnVoltar().addActionListener(e -> {
        popup.dispose();

        DashboardAlunoNovo dashboard = new DashboardAlunoNovo();
        dashboard.setVisible(true);

        view.dispose();
     });

     popup.setVisible(true);
    }

    private void reiniciarQuiz() {
     indiceQuestaoAtual = 0;

      for (int i = 0; i < respostasSelecionadas.length; i++) {
        respostasSelecionadas[i] = null;
      }

      carregarQuestaoAtual();
    }

    private int calcularAcertos() {
        int acertos = 0;

        for (int i = 0; i < questoes.size(); i++) {
            String respostaAluno = respostasSelecionadas[i];
            String respostaCorreta = questoes.get(i).alternativaCorreta;

            if (respostaAluno != null && respostaAluno.equals(respostaCorreta)) {
                acertos++;
            }
        }

        return acertos;
    }

    private static class QuestaoQuiz {
        String enunciado;
        String alternativaA;
        String alternativaB;
        String alternativaC;
        String alternativaD;
        String alternativaCorreta;
        String nomePersonagem;
        String descricaoPersonagem;
        String textoDica;

        public QuestaoQuiz(
                String enunciado,
                String alternativaA,
                String alternativaB,
                String alternativaC,
                String alternativaD,
                String alternativaCorreta,
                String nomePersonagem,
                String descricaoPersonagem,
                String textoDica
        ) {
            this.enunciado = enunciado;
            this.alternativaA = alternativaA;
            this.alternativaB = alternativaB;
            this.alternativaC = alternativaC;
            this.alternativaD = alternativaD;
            this.alternativaCorreta = alternativaCorreta;
            this.nomePersonagem = nomePersonagem;
            this.descricaoPersonagem = descricaoPersonagem;
            this.textoDica = textoDica;
        }
    }
}