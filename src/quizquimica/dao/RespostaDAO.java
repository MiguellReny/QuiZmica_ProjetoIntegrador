package quizquimica.dao;

import quizquimica.util.ConexaoDB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import quizquimica.dao.RespostaDAO;

public class RespostaDAO {

    public boolean salvar(int idPartida, int idPergunta, int idAlternativa) {
        String sql = "INSERT INTO pergunta_partida (idPartida, idPerguntas, idAlternativa) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPartida);
            ps.setInt(2, idPergunta);
            ps.setInt(3, idAlternativa);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[RespostaDAO] Erro ao salvar resposta: " + e.getMessage());
            return false;
        }
    }

    public int contarAcertosPorAluno(int idPartida) {
        String sql = "SELECT COUNT(*) FROM pergunta_partida pp " +
                     "JOIN alternativa a ON pp.idAlternativa = a.idAlternativa " +
                     "WHERE pp.idPartida = ? AND a.alternativaCorreta = 1";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPartida);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[RespostaDAO] Erro ao contar acertos: " + e.getMessage());
        }
        return 0;
    }

    public int contarErrosPorAluno(int idPartida) {
        String sql = "SELECT COUNT(*) FROM pergunta_partida pp " +
                     "JOIN alternativa a ON pp.idAlternativa = a.idAlternativa " +
                     "WHERE pp.idPartida = ? AND a.alternativaCorreta = 0";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPartida);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[RespostaDAO] Erro ao contar erros: " + e.getMessage());
        }
        return 0;
    }

    public Map<Integer, Integer> questoesMaisErradas() {
        String sql = "SELECT pp.idPerguntas, COUNT(*) as totalErros " +
                     "FROM pergunta_partida pp " +
                     "JOIN alternativa a ON pp.idAlternativa = a.idAlternativa " +
                     "WHERE a.alternativaCorreta = 0 " +
                     "GROUP BY pp.idPerguntas " +
                     "ORDER BY totalErros DESC";
        Map<Integer, Integer> resultado = new HashMap<>();
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                resultado.put(rs.getInt("idPerguntas"), rs.getInt("totalErros"));
            }
        } catch (SQLException e) {
            System.out.println("[RespostaDAO] Erro ao buscar questoes mais erradas: " + e.getMessage());
        }
        return resultado;
    }

    public int contarAcertos(int idUsuario) {

        String sql =
            "SELECT COUNT(*) " +
            "FROM pergunta_partida pp " +
            "INNER JOIN alternativa a " +
            "ON pp.idAlternativa = a.idAlternativa " +
            "INNER JOIN partida p " +
            "ON pp.idPartida = p.idPartida " +
            "WHERE p.usuario_idUsuario = ? " +
            "AND a.alternativaCorreta = 1";

        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("[RespostaDAO] Erro ao contar acertos: " + e.getMessage());
        }

        return 0;
    }

   public int contarErros(int idUsuario) {

        String sql =
            "SELECT COUNT(*) " +
            "FROM pergunta_partida pp " +
            "INNER JOIN alternativa a " +
            "ON pp.idAlternativa = a.idAlternativa " +
            "INNER JOIN partida p " +
            "ON pp.idPartida = p.idPartida " +
            "WHERE p.usuario_idUsuario = ? " +
            "AND a.alternativaCorreta = 0";

        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("[RespostaDAO] Erro ao contar erros: " + e.getMessage());
        }

        return 0;
    }
}
