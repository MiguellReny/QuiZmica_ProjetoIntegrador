package quizquimica.dao;

import quizquimica.model.Professor;
import quizquimica.util.ConexaoDB;
import java.sql.*;

public class ProfessorDAO {

    public Professor buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuário WHERE Login = ? AND Tipo = 'professor'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professor(
                    rs.getInt("idUsuário"),
                    rs.getString("Nome"),
                    rs.getString("Login"),
                    rs.getString("Senha")
                );
            }
        } catch (SQLException e) {
            System.out.println("[ProfessorDAO] Erro ao buscar professor: " + e.getMessage());
        }
        return null;
    }
}
