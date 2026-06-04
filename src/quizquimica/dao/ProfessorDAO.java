package quizquimica.dao;

import quizquimica.model.Professor;
import quizquimica.util.ConexaoDB;
import java.sql.*;

public class ProfessorDAO {

    public Professor buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND tipo = 'professor'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Professor(
                    rs.getInt("idUsuario"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("turma")
                );
            }
        } catch (SQLException e) {
            System.out.println("[ProfessorDAO] Erro ao buscar professor: " + e.getMessage());
        }
        return null;
    }

    public boolean inserir(Professor professor) {
        String sql = "INSERT INTO usuario (nome, login, senha, tipo) VALUES (?, ?, ?, 'professor')";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getLogin());
            ps.setString(3, professor.getSenha());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[ProfessorDAO] Erro ao inserir professor: " + e.getMessage());
        }
        return false;
    }
}