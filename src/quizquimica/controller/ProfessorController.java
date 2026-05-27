package quizquimica.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import quizquimica.model.Questao;
import quizquimica.service.AuthService;
import quizquimica.service.QuestaoService;
import quizquimica.util.Constantes;
import quizquimica.view.AdicionarQuestao;
import quizquimica.view.EditarQuestao;
import quizquimica.view.TelaJogar;
import quizquimica.view.TelaProfessor;

public class ProfessorController {

    private final TelaProfessor view;
    private final QuestaoService questaoService = new QuestaoService();
    private List<Questao> questoesAtuais = new ArrayList<>();

    public ProfessorController(TelaProfessor view) {
        this.view = view;
        carregarQuestoes();
        configurarEventos();
    }

    private void carregarQuestoes() {
        questoesAtuais = questaoService.listarTodas();
        renderizarLista(questoesAtuais);
    }

    private void configurarEventos() {
        // Botão NOVA QUESTÃO
        view.getBtnNova().addActionListener(e -> abrirAdicionarQuestao());

        // Botão SAIR
        view.getBtnSair().addActionListener(e -> sair());

        // Campo de busca — filtrar ao digitar
        view.getCampoBusca().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarQuestoes(view.getCampoBusca().getText().trim());
            }
        });

        // Limpar placeholder ao clicar
        view.getCampoBusca().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (view.getCampoBusca().getText().equals("Buscar questões....")) {
                    view.getCampoBusca().setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (view.getCampoBusca().getText().isBlank()) {
                    view.getCampoBusca().setText("Buscar questões....");
                }
            }
        });
    }

    private void filtrarQuestoes(String texto) {
        if (texto.isBlank() || texto.equals("Buscar questões....")) {
            renderizarLista(questoesAtuais);
            return;
        }
        List<Questao> filtradas = new ArrayList<>();
        for (Questao q : questoesAtuais) {
            if (q.getEnunciado().toLowerCase().contains(texto.toLowerCase())) {
                filtradas.add(q);
            }
        }
        renderizarLista(filtradas);
    }

    private void renderizarLista(List<Questao> questoes) {
        javax.swing.JPanel pnl = view.getPnlLista();
        pnl.removeAll();
        pnl.setLayout(new java.awt.GridLayout(0, 1, 0, 2));

        for (Questao q : questoes) {
            JPanel linha = new JPanel(new BorderLayout());
            linha.setBackground(new Color(235, 240, 245));
            linha.setBorder(BorderFactory.createMatteBorder(0, 4, 1, 0, corDificuldade(q.getDificuldade())));

            String enunciado = q.getEnunciado().length() > 45
                ? q.getEnunciado().substring(0, 45) + "..."
                : q.getEnunciado();
            JLabel lbl = new JLabel("  " + enunciado);
            lbl.setFont(new Font("Arial", Font.PLAIN, 11));
            linha.add(lbl, BorderLayout.CENTER);

            JPanel botoes = new JPanel();
            botoes.setOpaque(false);

            JButton btnEditar = new JButton("Editar");
            btnEditar.setBackground(Color.WHITE);
            btnEditar.setForeground(new Color(50, 70, 100));
            btnEditar.setFont(new Font("Arial", Font.ITALIC, 11));
            btnEditar.setPreferredSize(new Dimension(70, 24));
            btnEditar.setFocusPainted(false);
            int id = q.getIdQuestao();
            btnEditar.addActionListener(e -> abrirEditarQuestao(id));

            JButton btnRemover = new JButton("Remover");
            btnRemover.setBackground(new Color(179, 40, 36));
            btnRemover.setForeground(Color.WHITE);
            btnRemover.setFont(new Font("Arial", Font.PLAIN, 10));
            btnRemover.setPreferredSize(new Dimension(75, 24));
            btnRemover.setFocusPainted(false);
            btnRemover.addActionListener(e -> removerQuestao(id));

            botoes.add(btnEditar);
            botoes.add(btnRemover);
            linha.add(botoes, BorderLayout.EAST);

            pnl.add(linha);
        }

        pnl.revalidate();
        pnl.repaint();
    }

    private void abrirAdicionarQuestao() {
        AdicionarQuestao tela = new AdicionarQuestao();
        tela.setVisible(true);
        view.dispose();
    }

    public void abrirEditarQuestao(int idQuestao) {
        Questao q = questaoService.buscarPorId(idQuestao);
        if (q == null) {
            JOptionPane.showMessageDialog(view, "Questão não encontrada.");
            return;
        }
        EditarQuestao tela = new EditarQuestao(q);
        tela.setVisible(true);
        view.dispose();
    }

    public void removerQuestao(int idQuestao) {
        int confirm = JOptionPane.showConfirmDialog(view,
            "Deseja remover esta questão?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = questaoService.removerQuestao(idQuestao);
            if (ok) {
                JOptionPane.showMessageDialog(view, "Questão removida com sucesso!");
                carregarQuestoes();
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao remover questão.");
            }
        }
    }

    private void sair() {
        AuthService.getInstance().setUsuarioLogado(null);
        view.dispose();
        new TelaJogar().setVisible(true);
    }

    private Color corDificuldade(String d) {
        if (Constantes.nivelFacil.equals(d))   return new Color(34, 139, 34);
        if (Constantes.nivelMedio.equals(d))   return new Color(200, 140, 0);
        if (Constantes.nivelDificil.equals(d)) return new Color(179, 40, 36);
        return Color.GRAY;
    }
}