package quizquimica.controller;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import quizquimica.dao.PartidaDAO;
import quizquimica.model.Aluno;
import quizquimica.model.Partida;
import quizquimica.view.AlunosPesquisa;
import quizquimica.view.DesempenhoAluno;

public class DesempenhoAlunoController {

    private final DesempenhoAluno view;
    private final Aluno aluno;
    private final PartidaDAO partidaDAO = new PartidaDAO();

    public DesempenhoAlunoController(DesempenhoAluno view, Aluno aluno) {
        this.view = view;
        this.aluno = aluno;

        carregarDadosAluno();
        carregarHistorico();
        configurarEventos();
    }

    private void carregarDadosAluno() {
        List<Partida> partidas = partidaDAO.buscarPorAluno(aluno.getIdUsuario());

        int quizzesRealizados = partidas.size();
        int melhorPontuacao = calcularMelhorPontuacao(partidas);
        double media = partidaDAO.calcularMedia(aluno.getIdUsuario());

        int acertosEstimados = calcularAcertosEstimados(partidas);
        int errosEstimados = calcularErrosEstimados(partidas);
        int taxaAcerto = (int) Math.round(media);

        view.getLblAvatar().setText(gerarIniciais(aluno.getNome()));
        view.getLblNomeAluno().setText(aluno.getNome());
        view.getLblTurmaAluno().setText("Turma: " + aluno.getTurma());

        view.getLblQuizzesConcluidos().setText(String.valueOf(quizzesRealizados));
        view.getLblMediaGeral().setText(String.format("%.0f %%", media));
        view.getLblMelhorPontuacao().setText(melhorPontuacao + " %");

        view.getLblAcertosTotais().setText(String.valueOf(acertosEstimados));
        view.getLblErrosTotais().setText(String.valueOf(errosEstimados));
        view.getLblTaxaAcerto().setText(taxaAcerto + " %");
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
        AlunosPesquisa telaAlunos = new AlunosPesquisa();
        new AlunosPesquisaController(telaAlunos);

        telaAlunos.setVisible(true);
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
        int melhor = 0;

        for (Partida partida : partidas) {
            if (partida.getPontuacao() > melhor) {
                melhor = partida.getPontuacao();
            }
        }

        return melhor;
    }

    private int calcularAcertosEstimados(List<Partida> partidas) {
        int total = 0;

        for (Partida partida : partidas) {
            total += Math.round(partida.getPontuacao() / 10.0);
        }

        return total;
    }

    private int calcularErrosEstimados(List<Partida> partidas) {
        int totalQuestoesEstimado = partidas.size() * 10;
        int acertosEstimados = calcularAcertosEstimados(partidas);

        return Math.max(0, totalQuestoesEstimado - acertosEstimados);
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