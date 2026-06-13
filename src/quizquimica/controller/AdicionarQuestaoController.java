package quizquimica.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.service.QuestaoService;
import quizquimica.util.ConversorImagemUrl;
import quizquimica.view.AdicionarQuestao;
import quizquimica.view.DashboardProfessor;
import quizquimica.view.PopUpImagem;

public class AdicionarQuestaoController {

    private final AdicionarQuestao view;
    private final QuestaoService questaoService = new QuestaoService();

    // URLs das imagens coletadas pelos botões
    private String imagemEnunciadoUrl = null;
    private String imagemAltAUrl      = null;
    private String imagemAltBUrl      = null;
    private String imagemAltCUrl      = null;
    private String imagemAltDUrl      = null;

    public AdicionarQuestaoController(AdicionarQuestao view) {
        this.view = view;
        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarQuestao());
        view.getBtnCancelar().addActionListener(e -> voltarDashboard());
        view.getBtnSair().addActionListener(e -> voltarDashboard());

        configurarPlaceholder(view.getTxtAlternativaA(), "Digite a alternativa A");
        configurarPlaceholder(view.getTxtAlternativaB(), "Digite a alternativa B");
        configurarPlaceholder(view.getTxtAlternativaC(), "Digite a alternativa C");
        configurarPlaceholder(view.getTxtAlternativaD(), "Digite a alternativa D");

        // Contador de caracteres do enunciado (máx 500)
        view.getTxtEnunciado().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { atualizarContador(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { atualizarContador(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { atualizarContador(); }
        });

        // ── Botões de imagem ────────────────────────────────────────────────────

        // Imagem do enunciado
        view.getLblImagemEnunciado().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemEnunciado().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem do Enunciado");
                if (url != null) {
                    imagemEnunciadoUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa A
        view.getLblImagemAltA().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltA().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa A");
                if (url != null) {
                    imagemAltAUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa B
        view.getLblImagemAltB().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltB().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa B");
                if (url != null) {
                    imagemAltBUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa C
        view.getLblImagemAltC().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltC().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa C");
                if (url != null) {
                    imagemAltCUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });

        // Imagem da Alternativa D
        view.getLblImagemAltD().setCursor(
                java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        view.getLblImagemAltD().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                String url = pedirUrlImagem("Imagem da Alternativa D");
                if (url != null) {
                    imagemAltDUrl = url;
                    mostrarAvisoImagem();
                }
            }
        });
    }

    /** Abre um JOptionPane para o professor colar a URL da imagem. */
    private String pedirUrlImagem(String titulo) {
        String url = JOptionPane.showInputDialog(
                view,
                "⚠️ Cole aqui o link do Google Drive.\nAtenção: o arquivo deve estar compartilhado como 'Qualquer pessoa com o link'.",
                titulo,
                JOptionPane.PLAIN_MESSAGE);
        return (url != null && !url.isBlank()) ? url.trim() : null;
    }

    /** Exibe o PopUpImagem com o aviso sobre manter o arquivo no Google Drive. */
    private void mostrarAvisoImagem() {
        java.awt.Frame frame = (java.awt.Frame)
                javax.swing.SwingUtilities.getWindowAncestor(view);
        new PopUpImagem(frame, true).setVisible(true);
    }

    private void atualizarContador() {
        int tam = view.getTxtEnunciado().getText().length();
        view.getLblContadorEnunciado().setText(tam + "/500");
    }

    private void configurarPlaceholder(javax.swing.JTextField campo, String placeholder) {
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(java.awt.Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isBlank()) {
                    campo.setText(placeholder);
                    campo.setForeground(java.awt.Color.GRAY);
                }
            }
        });
    }

    private String converterParaBanco(String exibido) {
        switch (exibido) {
            case "Dmitri Mendeleev":  return "mendeleev";
            case "Ernest Rutherford": return "rutherford";
            case "Marie Curie":       return "curie";
            case "Rosalind Franklin": return "franklin";
            default:                  return "mendeleev";
        }
    }


    private void salvarQuestao() {
        String enunciado   = view.getTxtEnunciado().getText().trim();
        String dica        = view.getTxtDica().getText().trim();
        String dificuldade = converterDificuldade((String) view.getComboDificuldade().getSelectedItem());
        String altA = view.getTxtAlternativaA().getText().trim();
        String altB = view.getTxtAlternativaB().getText().trim();
        String altC = view.getTxtAlternativaC().getText().trim();
        String altD = view.getTxtAlternativaD().getText().trim();
        String corretaSelecionada = (String) view.getComboResposta().getSelectedItem();
        String personagemExibido  = (String) view.getComboPersonagem().getSelectedItem();
        String personagem = converterParaBanco(personagemExibido);
        System.out.println("[DEBUG] personagem a salvar: " + personagem);

        // --- Validações ---
        if (enunciado.isBlank()) {
            JOptionPane.showMessageDialog(view, "O enunciado não pode estar vazio.");
            return;
        }
        if (altA.isBlank() || altA.equals("Digite a alternativa A") ||
            altB.isBlank() || altB.equals("Digite a alternativa B") ||
            altC.isBlank() || altC.equals("Digite a alternativa C") ||
            altD.isBlank() || altD.equals("Digite a alternativa D")) {
            JOptionPane.showMessageDialog(view, "Preencha todas as alternativas.");
            return;
        }

        // --- Monta as alternativas ---
        List<Alternativa> alternativas = new ArrayList<>();
        Alternativa a1 = new Alternativa();
        a1.setAlternativa(altA);
        a1.setAlternativaCorreta(corretaSelecionada.equals("Alternativa A"));
        a1.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltAUrl));

        Alternativa a2 = new Alternativa();
        a2.setAlternativa(altB);
        a2.setAlternativaCorreta(corretaSelecionada.equals("Alternativa B"));
        a2.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltBUrl));

        Alternativa a3 = new Alternativa();
        a3.setAlternativa(altC);
        a3.setAlternativaCorreta(corretaSelecionada.equals("Alternativa C"));
        a3.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltCUrl));

        Alternativa a4 = new Alternativa();
        a4.setAlternativa(altD);
        a4.setAlternativaCorreta(corretaSelecionada.equals("Alternativa D"));
        a4.setAlternativaImagem(ConversorImagemUrl.converter(imagemAltDUrl));

        alternativas.add(a1);
        alternativas.add(a2);
        alternativas.add(a3);
        alternativas.add(a4);

        // --- Monta a Questao ---
        Questao q = new Questao();
        q.setEnunciado(enunciado);
        q.setDificuldade(dificuldade);
        q.setDica(dica.isBlank() ? " " : dica);
        q.setTipo("textual");
        q.setImagemUrl(ConversorImagemUrl.converter(imagemEnunciadoUrl));
        q.setAlternativas(alternativas);
        q.setPersonagem(personagem); 

        boolean ok = questaoService.adicionarQuestao(q);
        if (ok) {
            JOptionPane.showMessageDialog(view, "Questão adicionada com sucesso!");
            voltarDashboard();
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao salvar questão. Tente novamente.");
        }
    }

    private void voltarDashboard() {
        quizquimica.util.CacheQuestoes.getInstance().invalidar();
        new DashboardProfessor().setVisible(true);
        view.dispose();
    }


    private String converterDificuldade(String exibido) {
        switch (exibido) {
            case "Fácil":  return "FACIL";
            case "Médio":  return "MEDIO";
            case "Dificil": return "DIFICIL";
            default:       return "FACIL";
        }
    }
}