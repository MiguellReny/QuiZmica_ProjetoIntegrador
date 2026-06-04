package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import quizquimica.dao.AlunoDAO;
import quizquimica.dao.PartidaDAO;
import quizquimica.model.Aluno;
import quizquimica.model.Usuario;
import quizquimica.service.AuthService;
import quizquimica.view.AlunosPesquisa;
import quizquimica.view.MenuProfessor;
import quizquimica.view.PopUpAlterar;
import quizquimica.view.PopUpAdicionarAluno;

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

    private void carregarAlunos() {
        Usuario usuarioLogado = AuthService.getInstance().getUsuarioLogado();

        if (usuarioLogado == null) {
            System.out.println("Nenhum usuário logado!");
            return;
        }

        String turma = usuarioLogado.getTurma();

        if (turma != null && !turma.isBlank()) {
            todosAlunos = alunoDAO.listarPorTurma(turma);
        } else {
            todosAlunos = alunoDAO.listarTodos();
        }

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
            if (media > melhorMedia) melhorMedia = media;
        }

        double mediaGeral = total > 0 ? somaMedias / total : 0;
        view.getLabelTotal().setText(String.valueOf(total));
        view.getLabelQuiz().setText(String.valueOf(quizzesConcluidos));
        view.getLabelMedia().setText(String.format("%.1f", mediaGeral));
        view.getLabelMelhor().setText(String.format("%.1f", melhorMedia));
    }

    private void preencherTabela(List<Aluno> alunos) {
        DefaultTableModel modelo = (DefaultTableModel) view.getTabelaAlunos().getModel();
        modelo.setRowCount(0);
        for (Aluno aluno : alunos) {
            modelo.addRow(new Object[]{
                aluno.getNome(),
                aluno.getLogin(),
                "Editar",
                "Consultar",
                "Remover"
            });
        }
    }

    private void configurarEventos() {
        view.getCampoBusca().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarAlunos(view.getCampoBusca().getText().trim());
            }
        });
        view.getCampoBusca().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (view.getCampoBusca().getText().equals("Buscar aluno..."))
                    view.getCampoBusca().setText("");
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (view.getCampoBusca().getText().isBlank())
                    view.getCampoBusca().setText("Buscar aluno...");
            }
        });
        view.getjToggleButton1().addActionListener(e -> {
            MenuProfessor menu = new MenuProfessor(view, true);
            menu.setLocationRelativeTo(view.getjToggleButton1());
            menu.setVisible(true);
        });
        view.getTabelaAlunos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tratarCliqueTabela();
            }
        });

        // callback para recarregar tabela após adicionar aluno
        view.setControllerCallback(() -> {
            carregarAlunos();
            carregarResumo();
        });
    }

    private void filtrarAlunos(String texto) {
        if (texto.isBlank() || texto.equals("Buscar aluno...")) {
            preencherTabela(todosAlunos);
            return;
        }
        List<Aluno> filtrados = new ArrayList<>();
        for (Aluno aluno : todosAlunos) {
            if (aluno.getNome().toLowerCase().contains(texto.toLowerCase()) ||
                aluno.getLogin().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(aluno);
            }
        }
        preencherTabela(filtrados);
    }

    private void tratarCliqueTabela() {
        int linha  = view.getTabelaAlunos().getSelectedRow();
        int coluna = view.getTabelaAlunos().getSelectedColumn();

        if (linha < 0) return;
        Object nomeObj = view.getTabelaAlunos().getValueAt(linha, 0);
        if (nomeObj == null) return;

        String nome  = nomeObj.toString();
        String login = view.getTabelaAlunos().getValueAt(linha, 1).toString();

        if (coluna == 2) {
            editarAluno(nome, login);
        } else if (coluna == 3) {
            consultarAluno(login);
        } else if (coluna == 4) {
            removerAluno(login, nome);
        }
    }

    private void editarAluno(String nome, String login) {
        PopUpAlterar popup = new PopUpAlterar(view, true);
        popup.getTxtNome().setText(nome);
        popup.getTxtEmail().setText(login);

        for (java.awt.event.ActionListener al : popup.getBtnAlterar().getActionListeners()) {
            popup.getBtnAlterar().removeActionListener(al);
        }

        popup.getBtnAlterar().addActionListener(e -> {
            String novoNome  = popup.getTxtNome().getText().trim();
            String novoLogin = popup.getTxtEmail().getText().trim();
            String novaSenha = popup.getTxtSenha().getText().trim();

            if (novoNome.isEmpty() || novoLogin.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(popup, "Nome e email são obrigatórios.");
                return;
            }

            boolean ok = alunoDAO.atualizar(login, novoNome, novoLogin,
                novaSenha.isEmpty() ? null : novaSenha);

            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(popup, "Aluno atualizado com sucesso!");
                popup.dispose();
                carregarAlunos();
                carregarResumo();
            } else {
                javax.swing.JOptionPane.showMessageDialog(popup, "Erro ao atualizar aluno.");
            }
        });

        popup.setLocationRelativeTo(view);
        popup.setVisible(true);
    }

    private void consultarAluno(String login) {
        Aluno aluno = alunoDAO.buscarPorLogin(login);
        if (aluno == null) {
            javax.swing.JOptionPane.showMessageDialog(view, "Aluno não encontrado.");
            return;
        }
        quizquimica.view.DesempenhoAluno telaDesempenho =
            new quizquimica.view.DesempenhoAluno(view, aluno.getNome());
        new DesempenhoAlunoController(telaDesempenho, aluno);
        telaDesempenho.setVisible(true);
        view.dispose();
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