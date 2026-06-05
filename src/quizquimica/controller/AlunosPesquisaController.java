package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import quizquimica.dao.AlunoDAO;
import quizquimica.dao.PartidaDAO;
import quizquimica.model.Aluno;
import quizquimica.view.AlunosPesquisa;
import quizquimica.view.MenuProfessor;
import quizquimica.view.PopUpAlterar;
import quizquimica.view.PopUpAdicionarAluno;
import quizquimica.view.PopUpDeleteAluno;

public class AlunosPesquisaController {

    private final AlunosPesquisa view;
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final PartidaDAO partidaDAO = new PartidaDAO();
    private List<Aluno> todosAlunos = new ArrayList<>();

    public AlunosPesquisaController(AlunosPesquisa view) {
        this.view = view;
        configurarEventos();
        carregarDadosAsync();
    }

    private void carregarDadosAsync() {
        new Thread(() -> {
            carregarAlunos();
            carregarResumo();
        }).start();
    }

    private void carregarAlunos() {
        List<Aluno> lista = alunoDAO.listarTodos();
        javax.swing.SwingUtilities.invokeLater(() -> {
            todosAlunos = lista;
            preencherTabela(todosAlunos);
        });
    }

    private void carregarResumo() {
        List<Aluno> lista = new ArrayList<>(todosAlunos);
        int total = lista.size();
        int quizzesConcluidos = 0;
        double somaMedias = 0;
        double melhorMedia = 0;

        for (Aluno aluno : lista) {
            int partidas = partidaDAO.buscarPorAluno(aluno.getIdUsuario()).size();
            quizzesConcluidos += partidas;
            double media = partidaDAO.calcularMedia(aluno.getIdUsuario());
            somaMedias += media;
            if (media > melhorMedia) melhorMedia = media;
        }

        double mediaGeral = total > 0 ? somaMedias / total : 0;
        int qc = quizzesConcluidos;
        double mg = mediaGeral, mm = melhorMedia;

        javax.swing.SwingUtilities.invokeLater(() -> {
            view.getLabelTotal().setText(String.valueOf(total));
            view.getLabelQuiz().setText(String.valueOf(qc));
            view.getLabelMedia().setText(String.format("%.1f", mg));
            view.getLabelMelhor().setText(String.format("%.1f", mm));
        });
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
        view.getTabelaAlunos().clearSelection();
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
            menu.setLocationRelativeTo(view);
            menu.setVisible(true);
        });

        view.getjButton1().addActionListener(e -> {
            PopUpAdicionarAluno popup = new PopUpAdicionarAluno(view, true);
            popup.getBtnAlterar().setOpaque(true);
            popup.getBtnAlterar().setBorderPainted(false);
            popup.getBtnAlterar().setFocusPainted(false);
            popup.getBtnAlterar().addActionListener(ev -> {
                String nome  = popup.getTxtNome().getText().trim();
                String email = popup.getTxtEmail().getText().trim();
                String senha = popup.getTxtSenha().getText().trim();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(popup, "Todos os campos são obrigatórios.");
                    return;
                }

                String turma = javax.swing.JOptionPane.showInputDialog(
                    popup, "Informe a turma do aluno:", "Turma",
                    javax.swing.JOptionPane.QUESTION_MESSAGE
                );
                if (turma == null || turma.isBlank()) {
                    javax.swing.JOptionPane.showMessageDialog(popup, "A turma é obrigatória.");
                    return;
                }

                Aluno novoAluno = new Aluno(0, nome, email, senha, turma.trim());
                boolean ok = alunoDAO.inserir(novoAluno);

                if (ok) {
                    javax.swing.JOptionPane.showMessageDialog(popup, "Aluno adicionado com sucesso!");
                    popup.dispose();
                    new Thread(() -> {
                        carregarAlunos();
                        carregarResumo();
                    }).start();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(popup, "Erro ao adicionar aluno.");
                }
            });
            popup.setLocationRelativeTo(view);
            popup.setVisible(true);
        });

        view.getTabelaAlunos().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tratarCliqueTabela();
            }
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
            removerAluno(login, nome, linha);
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
                new Thread(() -> {
                    carregarAlunos();
                    carregarResumo();
                }).start();
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

    private void removerAluno(String login, String nome, int linha) {
        PopUpDeleteAluno popup = new PopUpDeleteAluno(view, true, linha, view.getTabelaAlunos());

        popup.getBtnDeletar().addActionListener(e -> {
            boolean ok = alunoDAO.remover(login);
            if (ok) {
                popup.dispose();
                new Thread(() -> {
                    carregarAlunos();
                    carregarResumo();
                }).start();
            } else {
                javax.swing.JOptionPane.showMessageDialog(popup, "Erro ao remover aluno.");
            }
        });

        popup.setLocationRelativeTo(view);
        popup.setVisible(true);
    }
}