package quizquimica.dao;

import quizquimica.model.Partida;
import quizquimica.util.ConexaoDB;
import quizquimica.util.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    public int salvar(Partida partida) {
        String sql = "INSERT INTO partida (pontuacao, data, nivel, dicasUsadas, usuario_idUsuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, partida.getPontuacao());
            ps.setDate(2, Date.valueOf(partida.getData()));
            ps.setString(3, partida.getNivel());
            ps.setInt(4, partida.getDicasUsadas());
            ps.setInt(5, partida.getIdUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao salvar partida: " + e.getMessage());
        }
        return -1;
    }

    public boolean atualizar(Partida partida) {
        String sql = "UPDATE partida SET pontuacao = ?, dicasUsadas = ? WHERE idPartida = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, partida.getPontuacao());
            ps.setInt(2, partida.getDicasUsadas());
            ps.setInt(3, partida.getIdPartida());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao atualizar partida: " + e.getMessage());
            return false;
        }
    }

    public List<Partida> buscarPorAluno(int idUsuario) {
        String sql = "SELECT * FROM partida WHERE usuario_idUsuario = ?";
        List<Partida> lista = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Partida partida = new Partida();
                    partida.setIdPartida(rs.getInt("idPartida"));
                    partida.setIdUsuario(rs.getInt("usuario_idUsuario"));
                    partida.setNivel(rs.getString("nivel"));
                    partida.setPontuacao(rs.getInt("pontuacao"));
                    partida.setData(rs.getDate("data").toLocalDate());
                    partida.setDicasUsadas(rs.getInt("dicasUsadas"));
                    lista.add(partida);
                }
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao buscar partidas: " + e.getMessage());
        }
        return lista;
    }

    public double calcularMedia(int idUsuario) {

        List<Partida> partidas = buscarPorAluno(idUsuario);

        if (partidas.isEmpty()) {
            return 0;
        }

        double soma = 0;

        for (Partida partida : partidas) {

            int maximo = calcularMaximo(partida.getNivel());

            if (maximo > 0) {
                soma += (partida.getPontuacao() * 100.0) / maximo;
            }
        }

        return soma / partidas.size();
    }

    public String buscarNivelAtual(int idUsuario) {
        String sql = "SELECT nivel, pontuacao FROM partida WHERE usuario_idUsuario = ? ORDER BY idPartida DESC LIMIT 1";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nivel      = rs.getString("nivel");
                    int pontuacao     = rs.getInt("pontuacao");
                    int totalMaximo   = calcularMaximo(nivel);
                    double aproveitamento = (double) pontuacao / totalMaximo;
                    if (aproveitamento >= Constantes.pontuacaoMin) {
                        if (nivel.equals(Constantes.nivelFacil))  return Constantes.nivelMedio;
                        if (nivel.equals(Constantes.nivelMedio))  return Constantes.nivelDificil;
                    }
                    return nivel;
                }
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao buscar nivel: " + e.getMessage());
        }
        return Constantes.nivelFacil;
    }

    public double aproveitamentoNoNivel(int idUsuario, String nivel) {
        String sql = "SELECT MAX(pontuacao) AS pontuacao FROM partida " +
                     "WHERE usuario_idUsuario = ? AND nivel = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, nivel);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int pontuacao = rs.getInt("pontuacao");
                    int maximo    = calcularMaximo(nivel);
                    return maximo > 0 ? (double) pontuacao / maximo : 0.0;
                }
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao buscar aproveitamento: " + e.getMessage());
        }
        return 0.0;
    }

    public int contarPartidasAprovadas(int idUsuario, String nivel) {
        int minimo = (int) (calcularMaximo(nivel) * Constantes.pontuacaoMin);
        String sql = "SELECT COUNT(*) FROM partida " +
                     "WHERE usuario_idUsuario = ? AND nivel = ? AND pontuacao >= ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, nivel);
            ps.setInt(3, minimo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao contar partidas aprovadas: " + e.getMessage());
        }
        return 0;
    }

    public double aproveitamentoAnteriorNoNivel(int idUsuario, String nivel) {

        String sql =
            "SELECT MAX(pontuacao) AS pontuacao " +
            "FROM partida " +
            "WHERE usuario_idUsuario = ? " +
            "AND nivel = ? " +
            "AND idPartida <> (" +
            "   SELECT MAX(idPartida) " +
            "   FROM partida " +
            "   WHERE usuario_idUsuario = ? " +
            "   AND nivel = ?" +
            ")";

        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setString(2, nivel);
            ps.setInt(3, idUsuario);
            ps.setString(4, nivel);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int pontuacao = rs.getInt("pontuacao");
                    int maximo = calcularMaximo(nivel);

                    return maximo > 0
                            ? (double) pontuacao / maximo
                            : 0.0;
                }
            }

        } catch (SQLException e) {
            System.out.println(
                "[PartidaDAO] Erro ao buscar aproveitamento anterior: "
                + e.getMessage()
            );
        }

        return 0.0;
    }

    public int contarTotalPartidas() {
        String sql = "SELECT COUNT(*) FROM partida";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao contar total de partidas: " + e.getMessage());
        }
        return 0;
    }

    public double calcularMediaGeralAcertos() {
        String sql = "SELECT " +
                     "  SUM(CASE WHEN a.alternativaCorreta = 1 THEN 1 ELSE 0 END) AS acertos, " +
                     "  COUNT(*) AS total " +
                     "FROM pergunta_partida pp " +
                     "JOIN alternativa a ON pp.idAlternativa = a.idAlternativa";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int acertos = rs.getInt("acertos");
                int total   = rs.getInt("total");
                return total > 0 ? (acertos * 100.0 / total) : 0.0;
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao calcular média geral de acertos: " + e.getMessage());
        }
        return 0.0;
    }

    public int calcularMaximo(String nivel) {
        if (nivel.equals(Constantes.nivelFacil)) {
            return (Constantes.facilFaceis   * Constantes.pontoF)
                 + (Constantes.facilMedias   * Constantes.pontoM)
                 + (Constantes.facilDificeis * Constantes.pontoD);
        }
        if (nivel.equals(Constantes.nivelMedio)) {
            return (Constantes.medioFaceis   * Constantes.pontoF)
                 + (Constantes.medioMedias   * Constantes.pontoM)
                 + (Constantes.medioDificeis * Constantes.pontoD);
        }
        return (Constantes.dificilFaceis   * Constantes.pontoF)
             + (Constantes.dificilMedias   * Constantes.pontoM)
             + (Constantes.dificilDificeis * Constantes.pontoD);
    }
}