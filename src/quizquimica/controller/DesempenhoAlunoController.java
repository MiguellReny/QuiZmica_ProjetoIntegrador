package quizquimica.controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import quizquimica.dao.PartidaDAO;
import quizquimica.dao.RespostaDAO;
import quizquimica.model.Aluno;
import quizquimica.model.Partida;
import quizquimica.view.AlunosPesquisa;
import quizquimica.view.DashboardAlunoNovo;
import quizquimica.view.DesempenhoAluno;

public class DesempenhoAlunoController {

    private final DesempenhoAluno view;
    private final Aluno aluno;
    private final PartidaDAO partidaDAO = new PartidaDAO();
    

    // origem: "professor" (volta para AlunosPesquisa) ou "aluno" (volta para DashboardAlunoNovo)
    private final String origem;

    // Construtor padrão — chamado pelo professor
    public DesempenhoAlunoController(DesempenhoAluno view, Aluno aluno) {
        this(view, aluno, "professor");
    }

    // Construtor com origem — usado pelo próprio aluno
    public DesempenhoAlunoController(DesempenhoAluno view, Aluno aluno, String origem) {
        this.view   = view;
        this.aluno  = aluno;
        this.origem = origem;

        carregarDadosAluno();
        carregarHistorico();
        configurarEventos();
    }

    private void carregarDadosAluno() {
        List<Partida> partidas = partidaDAO.buscarPorAluno(aluno.getIdUsuario());

        int quizzesRealizados = partidas.size();
        int melhorPontuacao = calcularMelhorPontuacao(partidas);
        double media = partidaDAO.calcularMedia(aluno.getIdUsuario());
        RespostaDAO respostaDAO = new RespostaDAO();

        int acertos = respostaDAO.contarAcertos(aluno.getIdUsuario());
        int erros = respostaDAO.contarErros(aluno.getIdUsuario());

        double taxaAcerto = 0;

        if (acertos + erros > 0) {
            taxaAcerto = (acertos * 100.0) / (acertos + erros);
        }

        view.getLblAvatar().setText(gerarIniciais(aluno.getNome()));
        view.getLblNomeAluno().setText(aluno.getNome());
        view.getLblTurmaAluno().setText("Turma: " + aluno.getTurma());

        view.getLblQuizzesConcluidos().setText(String.valueOf(quizzesRealizados));
        view.getLblMediaGeral().setText(
            String.format("%.1f %%", media)
        );
        view.getLblMelhorPontuacao().setText(melhorPontuacao + " %");

        view.getLblAcertosTotais().setText(String.valueOf(acertos));
        view.getLblErrosTotais().setText(String.valueOf(erros));
        view.getLblTaxaAcerto().setText(
            String.format("%.1f %%", taxaAcerto)
        );
        view.getLblQuizzesRealizados().setText(String.valueOf(quizzesRealizados));
    }

    private void carregarHistorico() {
        List<Partida> partidas = partidaDAO.buscarPorAluno(aluno.getIdUsuario());

        DefaultTableModel modelo = (DefaultTableModel) view.getTblHistorico().getModel();
        modelo.setRowCount(0);

        for (Partida partida : partidas) {
            modelo.addRow(new Object[]{
                montarNomeQuiz(partida),
                partida.getPontuacao(),
                partida.getDicasUsadas(),
                "DETALHES"
            });
        }
    }

    private void configurarEventos() {
        view.getBtnVoltar().addActionListener(e -> voltarParaAlunos());

        view.getTblHistorico().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tratarCliqueTabela();
            }
        });
    }

    private void voltarParaAlunos() {
        if ("aluno".equals(origem)) {
            new DashboardAlunoNovo().setVisible(true);
        } else {
            AlunosPesquisa telaAlunos = new AlunosPesquisa();
            new AlunosPesquisaController(telaAlunos);
            telaAlunos.setVisible(true);
        }
        view.dispose();
    }

    private void tratarCliqueTabela() {
        int linha = view.getTblHistorico().getSelectedRow();
        int coluna = view.getTblHistorico().getSelectedColumn();

        if (linha < 0) {
            return;
        }

        int colunaAcoes = 3;

        if (coluna == colunaAcoes) {
            javax.swing.JOptionPane.showMessageDialog(
                view,
                "Aqui futuramente vamos exibir as questões que o aluno acertou e errou.",
                "Detalhes do Quiz",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private String montarNomeQuiz(Partida partida) {
        if (partida.getNivel() == null || partida.getNivel().isBlank()) {
            return "Quiz de Química";
        }

        return "Quiz " + partida.getNivel();
    }

    private int calcularMelhorPontuacao(List<Partida> partidas) {

        double melhor = 0;

        for (Partida partida : partidas) {

            int maximo = partidaDAO.calcularMaximo(partida.getNivel());

            if (maximo > 0) {

                double percentual =
                    (partida.getPontuacao() * 100.0) / maximo;

                if (percentual > melhor) {
                    melhor = percentual;
                }
            }
        }

        return (int)Math.round(melhor);
    }

    private String gerarIniciais(String nome) {
        if (nome == null || nome.isBlank()) {
            return "?";
        }

        String[] partes = nome.trim().split("\\s+");

        if (partes.length == 1) {
            return partes[0].substring(0, 1).toUpperCase();
        }

        String primeira = partes[0].substring(0, 1);
        String ultima = partes[partes.length - 1].substring(0, 1);

        return (primeira + ultima).toUpperCase();
    }
}