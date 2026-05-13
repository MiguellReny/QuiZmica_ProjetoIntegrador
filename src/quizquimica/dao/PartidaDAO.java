package quizquimica.dao;

import quizquimica.model.Partida;
import quizquimica.util.ConexaoDB;
import quizquimica.util.Constantes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    public boolean salvar(Partida partida) {
        String sql = "INSERT INTO partida (pontuacao, data, nivel, dicasUsadas, usuario_idUsuario) VALUES (?, ?, ?, ?, ?)";
        Connection conn = ConexaoDB.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, partida.getPontuacao());
            ps.setDate(2, Date.valueOf(partida.getData()));
            ps.setString(3, partida.getNivel());
            ps.setInt(4, partida.getDicasUsadas());
            ps.setInt(5, partida.getIdUsuario());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao salvar partida: " + e.getMessage());
            return false;
        }
    }

    public List<Partida> buscarPorAluno(int idUsuario) {
        String sql = "SELECT * FROM partida WHERE usuario_idUsuario = ?";
        List<Partida> lista = new ArrayList<>();
        Connection conn = ConexaoDB.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao buscar partidas: " + e.getMessage());
        }
        return lista;
    }

    public double calcularMedia(int idUsuario) {
        String sql = "SELECT AVG(pontuacao) FROM partida WHERE usuario_idUsuario = ?";
        Connection conn = ConexaoDB.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao calcular media: " + e.getMessage());
        }
        return 0;
    }

    public String buscarNivelAtual(int idUsuario) {
        String sql = "SELECT nivel, pontuacao FROM partida WHERE usuario_idUsuario = ? ORDER BY idPartida DESC LIMIT 1";
        Connection conn = ConexaoDB.getConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nivel = rs.getString("nivel");
                int pontuacao = rs.getInt("pontuacao");
                int totalPontosPossiveis = Constantes.questoes * Constantes.pontoF;
                double aproveitamento = (double) pontuacao / totalPontosPossiveis;
                if (aproveitamento >= Constantes.pontuacaoMin) {
                    if (nivel.equals(Constantes.nivelFacil)) return Constantes.nivelMedio;
                    if (nivel.equals(Constantes.nivelMedio)) return Constantes.nivelDificil;
                }
                return nivel;
            }
        } catch (SQLException e) {
            System.out.println("[PartidaDAO] Erro ao buscar nivel: " + e.getMessage());
        }
        return Constantes.nivelFacil;
    }
}