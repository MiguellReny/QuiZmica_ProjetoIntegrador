package quizquimica.controller;

import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import quizquimica.model.Questao;
import quizquimica.service.AuthService;
import quizquimica.service.QuestaoService;
import quizquimica.util.Constantes;
import quizquimica.view.AdicionarQuestao;
import quizquimica.view.AlunosPesquisa;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.EditarQuestao;
import quizquimica.view.TelaJogar;

public class ProfessorController {

private final DashboardProfessor view;
private final QuestaoService questaoService = new QuestaoService();


public ProfessorController(DashboardProfessor view) {
    this.view = view;
    configurarEventos();
    carregarQuestoes();
    carregarEstatisticas();
}

private void configurarEventos() {
    view.getBtnNovaQuestao().addActionListener(e -> {
        abrirAdicionarQuestao();
    });

    view.getTabelaQuestoes().addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int linha = view.getTabelaQuestoes().rowAtPoint(evt.getPoint());
            int coluna = view.getTabelaQuestoes().columnAtPoint(evt.getPoint());

            System.out.println("Clicou! Linha: " + linha + " | Coluna: " + coluna);

            if (linha < 0) return;

            // Abre editar independente da coluna clicada
            Object idObj = view.getTabelaQuestoes().getModel().getValueAt(linha, 2);
            System.out.println("ID da questão: " + idObj);

            if (idObj == null) return;
            int idQuestao = Integer.parseInt(idObj.toString());
            abrirEditarQuestao(idQuestao);
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
    DefaultTableModel model =
        (DefaultTableModel) view.getTabelaQuestoes().getModel();
    model.setRowCount(0);
    for (Questao q : lista) {
        model.addRow(new Object[] {
            q.getEnunciado(),
            "Editar",
            q.getIdQuestao()
        });
    }
    view.getTabelaQuestoes().getColumnModel().getColumn(2).setMinWidth(0);
    view.getTabelaQuestoes().getColumnModel().getColumn(2).setMaxWidth(0);
    view.getTabelaQuestoes().getColumnModel().getColumn(2).setWidth(0);
        view.getTabelaQuestoes().setRowHeight(25);

}

private void filtrarQuestoes(String texto) {
    List<Questao> lista = questaoService.listarTodas();
    DefaultTableModel model =
        (DefaultTableModel) view.getTabelaQuestoes().getModel();
    model.setRowCount(0);
    for (Questao q : lista) {
        if (q.getEnunciado().toLowerCase().contains(texto.toLowerCase())) {
            model.addRow(new Object[] {
                q.getEnunciado(),
                "Editar",
                q.getIdQuestao()
            });
        }
    }
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
        JOptionPane.showMessageDialog(
            view,
            "Questão não encontrada."
        );
        return;
    }

    EditarQuestao tela = new EditarQuestao(q);
    tela.setLocationRelativeTo(null);
    tela.setVisible(true);
    view.dispose();
}

private void carregarEstatisticas() {
    view.getProgressBar1().setValue(73);
    view.getProgressBar2().setValue(62);
    view.getProgressBar3().setValue(58);

    view.getProgressBar1().setForeground(Color.RED);
    view.getProgressBar2().setForeground(Color.ORANGE);
    view.getProgressBar3().setForeground(Color.YELLOW);

    view.getLblQuestoesErros().setText("1. O que é um catalisador?");
    view.getLblQuestoesErros1().setText("2. Qual o nome do material?");
    view.getLblQuestoesErros2().setText("3. Qual material é usado na filtração?");
}

private void sair() {
    AuthService.getInstance().setUsuarioLogado(null);
    view.dispose();
    new TelaJogar().setVisible(true);
}
private Color corDificuldade(String dificuldade) {
    if (Constantes.nivelFacil.equals(dificuldade)) {
        return new Color(34, 139, 34);
    }
    if (Constantes.nivelMedio.equals(dificuldade)) {
        return new Color(200, 140, 0);
    }
    if (Constantes.nivelDificil.equals(dificuldade)) {
        return new Color(179, 40, 36);
    }
    return Color.GRAY;
}
}
