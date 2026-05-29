package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import quizquimica.dao.AlunoDAO;
import quizquimica.dao.PartidaDAO;
import quizquimica.model.Aluno;
import quizquimica.service.AuthService;
import quizquimica.view.AlunosPesquisa;

public class AlunosPesquisaController {

    private final AlunosPesquisa view;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final PartidaDAO partidaDAO = new PartidaDAO();
    private List<Aluno> todosAlunos = new ArrayList<>();

    public AlunosPesquisaController(AlunosPesquisa view) {
        this.view = view;
        carregarAlunos();
        carregarResumo();
        configurarEventos();
    }

    // -------------------------------------------------------
    // Carregamento inicial
    // -------------------------------------------------------

    private void carregarAlunos() {
        String turma = AuthService.getInstance().getUsuarioLogado().getTurma();
        todosAlunos = alunoDAO.listarPorTurma(turma);
        preencherTabela(todosAlunos);
    }

    private void carregarResumo() {
        int total = todosAlunos.size();
        int quizzesConcluidos = 0;
        double somaMedias = 0;
        double melhorMedia = 0;

        for (Aluno aluno : todosAlunos) {
            int partidas = partidaDAO.buscarPorAluno(aluno.getIdUsuario()).size();
            quizzesConcluidos += partidas;

            double media = partidaDAO.calcularMedia(aluno.getIdUsuario());
            somaMedias += media;
            if (media > melhorMedia) {
                melhorMedia = media;
            }
        }

        double mediaGeral = total > 0 ? somaMedias / total : 0;

        view.getLabelTotal().setText(String.valueOf(total));
        view.getLabelQuiz().setText(String.valueOf(quizzesConcluidos));
        view.getLabelMedia().setText(String.format("%.1f", mediaGeral));
        view.getLabelMelhor().setText(String.format("%.1f", melhorMedia));
    }

    // -------------------------------------------------------
    // Tabela
    // -------------------------------------------------------

    private void preencherTabela(List<Aluno> alunos) {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaAlunos().getModel();
        modelo.setRowCount(0);

        for (Aluno aluno : alunos) {
            modelo.addRow(new Object[]{
                aluno.getNome(),
                aluno.getLogin(),
                "Consultar | Remover"
            });
        }
    }

    // -------------------------------------------------------
    // Eventos
    // -------------------------------------------------------

    private void configurarEventos() {
        // Campo de busca — filtrar ao digitar
        view.getCampoBusca().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarAlunos(view.getCampoBusca().getText().trim());
            }
        });

        // Placeholder do campo de busca
        view.getCampoBusca().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (view.getCampoBusca().getText().equals("Buscar aluno...")) {
                    view.getCampoBusca().setText("");
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (view.getCampoBusca().getText().isBlank()) {
                    view.getCampoBusca().setText("Buscar aluno...");
                }
            }
        });

        // Clique na tabela
        view.getTabelaAlunos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tratarCliqueTabela();
            }
        });
    }

    // -------------------------------------------------------
    // Filtro
    // -------------------------------------------------------

    private void filtrarAlunos(String texto) {
        if (texto.isBlank() || texto.equals("Buscar aluno...")) {
            preencherTabela(todosAlunos);
            return;
        }

        List<Aluno> filtrados = new ArrayList<>();
        for (Aluno aluno : todosAlunos) {
            boolean nomeContem  = aluno.getNome().toLowerCase().contains(texto.toLowerCase());
            boolean loginContem = aluno.getLogin().toLowerCase().contains(texto.toLowerCase());
            if (nomeContem || loginContem) {
                filtrados.add(aluno);
            }
        }
        preencherTabela(filtrados);
    }

    // -------------------------------------------------------
    // Ações da tabela
    // -------------------------------------------------------

    private void tratarCliqueTabela() {
        int linha  = view.getTabelaAlunos().getSelectedRow();
        int coluna = view.getTabelaAlunos().getSelectedColumn();

        if (linha < 0) return;

        String nome  = view.getTabelaAlunos().getValueAt(linha, 0).toString();
        String login = view.getTabelaAlunos().getValueAt(linha, 1).toString();

        if (coluna == 2) {
            Object[] opcoes = {"Consultar", "Remover", "Cancelar"};
            int escolha = javax.swing.JOptionPane.showOptionDialog(
                view,
                "Escolha a ação para: " + nome,
                "Ações do Aluno",
                javax.swing.JOptionPane.DEFAULT_OPTION,
                javax.swing.JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            if (escolha == 0) {
                consultarAluno(login);
            } else if (escolha == 1) {
                removerAluno(login, nome);
            }
        }
    }

    private void consultarAluno(String login) {
        Aluno aluno = alunoDAO.buscarPorLogin(login);
        if (aluno == null) {
            javax.swing.JOptionPane.showMessageDialog(view, "Aluno não encontrado.");
            return;
        }

        double media  = partidaDAO.calcularMedia(aluno.getIdUsuario());
        String nivel  = partidaDAO.buscarNivelAtual(aluno.getIdUsuario());
        int partidas  = partidaDAO.buscarPorAluno(aluno.getIdUsuario()).size();

        String info = "<html>"
            + "<b>Nome:</b> "               + aluno.getNome()  + "<br>"
            + "<b>Email:</b> "              + aluno.getLogin() + "<br>"
            + "<b>Turma:</b> "              + aluno.getTurma() + "<br>"
            + "<b>Partidas jogadas:</b> "   + partidas         + "<br>"
            + "<b>Média:</b> "              + String.format("%.1f", media) + "<br>"
            + "<b>Nível atual:</b> "        + nivel
            + "</html>";

        javax.swing.JOptionPane.showMessageDialog(view, info, "Dados do Aluno",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void removerAluno(String login, String nome) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            view,
            "Deseja remover o aluno \"" + nome + "\"?",
            "Confirmar Remoção",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            boolean ok = alunoDAO.remover(login);
            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(view, "Aluno removido com sucesso!");
                carregarAlunos();
                carregarResumo();
            } else {
                javax.swing.JOptionPane.showMessageDialog(view, "Erro ao remover o aluno.");
            }
        }
    }
}