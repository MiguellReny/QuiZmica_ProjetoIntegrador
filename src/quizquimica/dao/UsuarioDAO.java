package quizquimica.dao;

import quizquimica.util.ConexaoDB;
import java.sql.*;

public class UsuarioDAO {

    // Autentica pelo Login e Senha
    public boolean autenticar(String login, String senhaHash) {
        String sql = "SELECT idUsuário FROM usuário WHERE Login = ? AND Senha = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, senhaHash);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao autenticar: " + e.getMessage());
        }
        return false;
    }

    // Retorna o Tipo do usuário ("aluno" ou "professor")
    public String buscarTipo(String login) {
        String sql = "SELECT Tipo FROM usuário WHERE Login = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("Tipo");
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao buscar tipo: " + e.getMessage());
        }
        return null;
    }

    // Atualiza senha (professor redefine para aluno)
    public boolean atualizarSenha(String login, String novaSenhaHash) {
        String sql = "UPDATE usuário SET Senha = ? WHERE Login = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novaSenhaHash);
            stmt.setString(2, login);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao atualizar senha: " + e.getMessage());
        }
        return false;
    }
}
