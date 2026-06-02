package quizquimica.dao;

import quizquimica.model.Alternativa;
import quizquimica.model.Questao;
import quizquimica.util.ConexaoDB;
import quizquimica.util.ConversorImagemUrl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestaoDAO {

    public List<Questao> listarPorDificuldade(String dificuldade, int quantidade) {
        List<Questao> questoes = new ArrayList<>();
        String sql = "SELECT * FROM perguntas WHERE dificuldade = ? ORDER BY RAND() LIMIT ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dificuldade);
            ps.setInt(2, quantidade);
            ResultSet rs = ps.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                Questao questao = mapearQuestao(rs);
                ids.add(questao.getIdQuestao());
                questoes.add(questao);
            }
            rs.close();
            for (int i = 0; i < questoes.size(); i++) {
                questoes.get(i).setAlternativas(buscarAlternativas(ids.get(i)));
            }
        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao listar questoes por dificuldade: " + e.getMessage());
        }
        return questoes;
    }

    public Questao buscarPorId(int idQuestao) {
        String sql = "SELECT * FROM perguntas WHERE idPerguntas = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idQuestao);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Questao questao = mapearQuestao(rs);
                rs.close();
                questao.setAlternativas(buscarAlternativas(questao.getIdQuestao()));
                return questao;
            }
        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao buscar questao por id: " + e.getMessage());
        }
        return null;
    }

    public boolean inserir(Questao questao) {
        String sql = "INSERT INTO perguntas (enunciado, perguntaImagem, dificuldade, dica, tipo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, questao.getEnunciado());
            ps.setString(2, questao.getImagemUrl());
            ps.setString(3, questao.getDificuldade());
            ps.setString(4, questao.getDica());
            ps.setString(5, questao.getTipo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao inserir questao: " + e.getMessage());
        }
        return false;
    }

    public boolean atualizar(Questao questao) {
        String sql = "UPDATE perguntas SET enunciado = ?, perguntaImagem = ?, dificuldade = ?, dica = ?, tipo = ? WHERE idPerguntas = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, questao.getEnunciado());
            ps.setString(2, questao.getImagemUrl());
            ps.setString(3, questao.getDificuldade());
            ps.setString(4, questao.getDica());
            ps.setString(5, questao.getTipo());
            ps.setInt(6, questao.getIdQuestao());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao atualizar questao: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(int idQuestao) {
        String sql = "DELETE FROM perguntas WHERE idPerguntas = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idQuestao);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao remover questao: " + e.getMessage());
        }
        return false;
    }

    private List<Alternativa> buscarAlternativas(int idQuestao) {
        List<Alternativa> alternativas = new ArrayList<>();
        String sql = "SELECT * FROM alternativa WHERE perguntas_idPerguntas = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idQuestao);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alternativa alternativa = new Alternativa();
                alternativa.setIdAlternativa(rs.getInt("idAlternativa"));
                alternativa.setAlternativa(rs.getString("alternativa"));
                alternativa.setAlternativaCorreta(rs.getBoolean("alternativaCorreta"));
                alternativa.setIdQuestao(rs.getInt("perguntas_idPerguntas"));
                alternativa.setAlternativaImagem(ConversorImagemUrl.converter(rs.getString("alternativaImagem")));
                alternativas.add(alternativa);
            }
        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao buscar alternativas: " + e.getMessage());
        }
        return alternativas;
    }

    private Questao mapearQuestao(ResultSet rs) throws SQLException {
        Questao questao = new Questao();
        questao.setIdQuestao(rs.getInt("idPerguntas"));
        questao.setEnunciado(rs.getString("enunciado"));
        questao.setImagemUrl(rs.getString("perguntaImagem"));
        questao.setDificuldade(rs.getString("dificuldade"));
        questao.setDica(rs.getString("dica"));
        questao.setTipo(rs.getString("tipo"));
        return questao;
    }
}