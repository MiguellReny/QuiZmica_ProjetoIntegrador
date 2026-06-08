package quizquimica.controller;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import quizquimica.dao.RespostaDAO;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.util.Constantes;
import quizquimica.view.AdicionarQuestao;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.EditarQuestao;
import quizquimica.view.PopUpDeleteQuiz;

public class ProfessorController {

    private final DashboardProfessor view;
    private final QuestaoService questaoService = new QuestaoService();
    private TableRowSorter<DefaultTableModel> sorter;

    public ProfessorController(DashboardProfessor view) {
        this.view = view;
        configurarEventos();
        carregarQuestoes();
        carregarEstatisticas();
    }

    private void configurarEventos() {
        view.getBtnNovaQuestao().addActionListener(e -> abrirAdicionarQuestao());

        view.getTabelaQuestoes().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linhaView  = view.getTabelaQuestoes().rowAtPoint(evt.getPoint());
                int colunaView = view.getTabelaQuestoes().columnAtPoint(evt.getPoint());

                if (linhaView < 0) return;

                int linhaModel  = view.getTabelaQuestoes().convertRowIndexToModel(linhaView);
                int colunaModel = view.getTabelaQuestoes().convertColumnIndexToModel(colunaView);

                Object idObj = view.getTabelaQuestoes().getModel().getValueAt(linhaModel, 3);
                if (idObj == null) return;
                int idQuestao = Integer.parseInt(idObj.toString());

                if (colunaModel == 1) {
                    abrirEditarQuestao(idQuestao);
                } else if (colunaModel == 2) {
                    abrirDeleteQuiz(idQuestao);
                }
            }
        });

        view.getCampoBusca().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarQuestoes(view.getCampoBusca().getText());
            }
        });
    }

    private void carregarQuestoes() {
        List<Questao> lista = questaoService.listarTodas();
        DefaultTableModel model = (DefaultTableModel) view.getTabelaQuestoes().getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Questão", "Editar", "Remover", "ID"});

        for (Questao q : lista) {
            model.addRow(new Object[]{
                q.getEnunciado(),
                "Editar",
                "Remover",
                q.getIdQuestao()
            });
        }

        // Configura o sorter para filtro em memória (sem banco)
        sorter = new TableRowSorter<>(model);
        view.getTabelaQuestoes().setRowSorter(sorter);

        // Oculta coluna ID (índice 3)
        var colID = view.getTabelaQuestoes().getColumnModel().getColumn(3);
        colID.setMinWidth(0);
        colID.setMaxWidth(0);
        colID.setWidth(0);

        view.getTabelaQuestoes().setRowHeight(25);
    }

    private void filtrarQuestoes(String texto) {
        if (sorter == null) return;

        String placeholder = "Buscar questões...";
        if (texto.isEmpty() || texto.equals(placeholder)) {
            sorter.setRowFilter(null); // mostra tudo
            return;
        }

        // Filtra apenas na coluna 0 (enunciado), case-insensitive, sem banco
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(texto), 0));
    }

    private void abrirAdicionarQuestao() {
        AdicionarQuestao tela = new AdicionarQuestao();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        view.dispose();
    }

    private void abrirEditarQuestao(int idQuestao) {
        Questao q = questaoService.buscarPorId(idQuestao);
        if (q == null) {
            JOptionPane.showMessageDialog(view, "Questão não encontrada.");
            return;
        }
        EditarQuestao tela = new EditarQuestao(q);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
        view.dispose();
    }

    private void abrirDeleteQuiz(int idQuestao) {
        PopUpDeleteQuiz popup = new PopUpDeleteQuiz(view, true);
        popup.setIdQuestao(idQuestao);
        popup.setOnConfirmar(() -> {
            boolean ok = questaoService.removerQuestao(idQuestao);
            if (ok) {
                carregarQuestoes();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao remover questão.");
            }
        });
        popup.setLocationRelativeTo(view);
        popup.setVisible(true);
    }

    private void carregarEstatisticas() {
        RespostaDAO respostaDAO = new RespostaDAO();
        Map<Integer, Integer> erros = respostaDAO.questoesMaisErradas();

        List<Map.Entry<Integer, Integer>> top3 = erros.entrySet()
                .stream()
                .limit(3)
                .collect(java.util.stream.Collectors.toList());

        int totalErros = erros.values().stream().mapToInt(Integer::intValue).sum();

        javax.swing.JLabel[] labels = {
            view.getLblQuestoesErros(),
            view.getLblQuestoesErros1(),
            view.getLblQuestoesErros2()
        };
        javax.swing.JProgressBar[] bars = {
            view.getProgressBar1(),
            view.getProgressBar2(),
            view.getProgressBar3()
        };
        Color[] cores = { Color.RED, Color.ORANGE, Color.YELLOW };

        for (int i = 0; i < top3.size(); i++) {
            int idQuestao = top3.get(i).getKey();
            int qtdErros  = top3.get(i).getValue();

            Questao q = questaoService.buscarPorId(idQuestao);
            String enunciado = (q != null) ? q.getEnunciado() : "Questão #" + idQuestao;
            if (enunciado.length() > 50) enunciado = enunciado.substring(0, 47) + "...";

            int percentual = (totalErros > 0) ? (qtdErros * 100 / totalErros) : 0;

            labels[i].setText((i + 1) + ". " + enunciado);
            bars[i].setValue(percentual);
            bars[i].setForeground(cores[i]);
        }

        for (int i = top3.size(); i < 3; i++) {
            labels[i].setText("—");
            bars[i].setValue(0);
        }
    }

    private Color corDificuldade(String dificuldade) {
        if (Constantes.nivelFacil.equals(dificuldade))   return new Color(34, 139, 34);
        if (Constantes.nivelMedio.equals(dificuldade))   return new Color(200, 140, 0);
        if (Constantes.nivelDificil.equals(dificuldade)) return new Color(179, 40, 36);
        return Color.GRAY;
    }
}