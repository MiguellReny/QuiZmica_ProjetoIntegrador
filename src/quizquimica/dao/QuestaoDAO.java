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

            while (rs.next()) {
                questoes.add(mapearQuestao(rs));
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao listar questoes por dificuldade: " + e.getMessage());
        }

        // Conexão separada para alternativas — evita conflito de ResultSet
        for (Questao q : questoes) {
            try (Connection conn2 = ConexaoDB.getConexao()) {
                q.setAlternativas(buscarAlternativas(conn2, q.getIdQuestao()));
            } catch (SQLException e) {
                System.out.println("[QuestaoDAO] Erro ao buscar alternativas: " + e.getMessage());
            }
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

                try (Connection conn2 = ConexaoDB.getConexao()) {
                    questao.setAlternativas(buscarAlternativas(conn2, questao.getIdQuestao()));
                }

                return questao;
            }

        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao buscar questao por id: " + e.getMessage());
        }

        return null;
    }

    public boolean inserir(Questao questao) {
        String sql =
                "INSERT INTO perguntas " +
                "(enunciado, perguntaImagem, dificuldade, dica, tipo, personagem) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, questao.getEnunciado());
            ps.setString(2, questao.getImagemUrl());
            ps.setString(3, questao.getDificuldade());
            ps.setString(4, questao.getDica());
            ps.setString(5, questao.getTipo());
            ps.setString(6, questao.getPersonagem() != null ? questao.getPersonagem() : "mendeleev");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int idQuestao = 0;
            if (rs.next()) {
                idQuestao = rs.getInt(1);
            }
            rs.close();

            String sqlAlternativa =
                    "INSERT INTO alternativa " +
                    "(alternativa, alternativaCorreta, alternativaImagem, perguntas_idPerguntas) " +
                    "VALUES (?, ?, ?, ?)";

            try (PreparedStatement psAlt = conn.prepareStatement(sqlAlternativa)) {
                for (Alternativa alt : questao.getAlternativas()) {
                    psAlt.setString(1, alt.getAlternativa());
                    psAlt.setBoolean(2, alt.isAlternativaCorreta());
                    psAlt.setString(3, alt.getAlternativaImagem());
                    psAlt.setInt(4, idQuestao);
                    psAlt.executeUpdate();
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao inserir questao: " + e.getMessage());
        }

        return false;
    }

    public boolean atualizar(Questao questao) {
        String sql =
                "UPDATE perguntas SET " +
                "enunciado = ?, " +
                "perguntaImagem = ?, " +
                "dificuldade = ?, " +
                "dica = ?, " +
                "tipo = ?, " +
                "personagem = ? " +
                "WHERE idPerguntas = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, questao.getEnunciado());
            ps.setString(2, questao.getImagemUrl());
            ps.setString(3, questao.getDificuldade());
            ps.setString(4, questao.getDica());
            ps.setString(5, questao.getTipo());
            ps.setString(6, questao.getPersonagem() != null ? questao.getPersonagem() : "mendeleev");
            ps.setInt(7, questao.getIdQuestao());

            if (ps.executeUpdate() == 0) return false;

            try (PreparedStatement psDelete = conn.prepareStatement(
                    "DELETE FROM alternativa WHERE perguntas_idPerguntas = ?")) {
                psDelete.setInt(1, questao.getIdQuestao());
                psDelete.executeUpdate();
            }

            try (PreparedStatement psAlt = conn.prepareStatement(
                    "INSERT INTO alternativa " +
                    "(alternativa, alternativaCorreta, alternativaImagem, perguntas_idPerguntas) " +
                    "VALUES (?, ?, ?, ?)")) {
                for (Alternativa alt : questao.getAlternativas()) {
                    psAlt.setString(1, alt.getAlternativa());
                    psAlt.setBoolean(2, alt.isAlternativaCorreta());
                    psAlt.setString(3, alt.getAlternativaImagem());
                    psAlt.setInt(4, questao.getIdQuestao());
                    psAlt.executeUpdate();
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao atualizar questao: " + e.getMessage());
        }

        return false;
    }

    public boolean remover(int idQuestao) {
        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement psPergPartida = conn.prepareStatement(
                    "DELETE FROM pergunta_partida WHERE idPerguntas = ?");
            PreparedStatement psAlt = conn.prepareStatement(
                    "DELETE FROM alternativa WHERE perguntas_idPerguntas = ?");
            PreparedStatement psQuestao = conn.prepareStatement(
                    "DELETE FROM perguntas WHERE idPerguntas = ?")) {

            // 1. Remove da pergunta_partida
            psPergPartida.setInt(1, idQuestao);
            psPergPartida.executeUpdate();

            // 2. Remove alternativas
            psAlt.setInt(1, idQuestao);
            psAlt.executeUpdate();

            // 3. Remove a questão
            psQuestao.setInt(1, idQuestao);
            return psQuestao.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao remover questao: " + e.getMessage());
        }

        return false;
    }

    private List<Alternativa> buscarAlternativas(Connection conn, int idQuestao) {
        List<Alternativa> alternativas = new ArrayList<>();
        String sql = "SELECT * FROM alternativa WHERE perguntas_idPerguntas = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idQuestao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Alternativa alternativa = new Alternativa();
                alternativa.setIdAlternativa(rs.getInt("idAlternativa"));
                alternativa.setAlternativa(rs.getString("alternativa"));
                alternativa.setAlternativaCorreta(rs.getBoolean("alternativaCorreta"));
                alternativa.setIdQuestao(rs.getInt("perguntas_idPerguntas"));
                alternativa.setAlternativaImagem(
                        ConversorImagemUrl.converter(rs.getString("alternativaImagem"))
                );
                alternativas.add(alternativa);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("[QuestaoDAO] Erro ao buscar alternativas: " + e.getMessage());
        }

        return alternativas;
    }

    private Questao mapearQuestao(ResultSet rs) throws SQLException {
        Questao questao = new Questao();
        questao.setIdQuestao(rs.getInt("idPerguntas"));
        questao.setEnunciado(rs.getString("enunciado"));
        questao.setImagemUrl(ConversorImagemUrl.converter(rs.getString("perguntaImagem"))); 
        questao.setDificuldade(rs.getString("dificuldade"));
        questao.setDica(rs.getString("dica"));
        questao.setTipo(rs.getString("tipo"));
        questao.setPersonagem(rs.getString("personagem"));
        return questao;
    }
}