package quizquimica.dao;

import quizquimica.util.ConexaoDB;
import java.sql.*;

public class UsuarioDAO {

    // Autentica pelo login e senha
    public boolean autenticar(String login, String senhaHash) {
        String sql = "SELECT idUsuario FROM usuario WHERE login = ? AND senha = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, senhaHash);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao autenticar: " + e.getMessage());
        }
        return false;
    }

    // Retorna o tipo do usuário ("aluno" ou "professor")
    public String buscarTipo(String login) {
        String sql = "SELECT tipo FROM usuario WHERE login = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("tipo");
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao buscar tipo: " + e.getMessage());
        }
        return null;
    }

    // Verifica se um login já existe no banco
    public boolean loginExiste(String login) {
        String sql = "SELECT idUsuario FROM usuario WHERE login = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao verificar login: " + e.getMessage());
        }
        return false;
    }

    // Verifica se o usuário já aceitou os termos de uso
    public boolean termoAceito(String login) {
        String sql = "SELECT termo_aceito FROM usuario WHERE login = ?";
        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getBoolean("termo_aceito");
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao verificar termo aceito: " + e.getMessage());
        }
        return false;
    }

    // Marca que o usuário aceitou os termos de uso
    public boolean marcarTermoAceito(String login) {
        String sql = "UPDATE usuario SET termo_aceito = 1 WHERE login = ?";
        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao marcar termo aceito: " + e.getMessage());
        }
        return false;
    }

    // Atualiza senha (professor redefine para aluno)
    public boolean atualizarSenha(String login, String novaSenhaHash) {
        String sql = "UPDATE usuario SET senha = ? WHERE login = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novaSenhaHash);
            ps.setString(2, login);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao atualizar senha: " + e.getMessage());
        }
        return false;
    }

    // Verifica se ainda é o primeiro acesso
    public boolean primeiroLogin(String login) {
        String sql = "SELECT primeiro_login FROM usuario WHERE login = ?";

        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("primeiro_login");
            }

        } catch (SQLException e) {
            System.out.println("[UsuarioDAO] Erro ao verificar primeiro login: "
                    + e.getMessage());
        }

        return false;
    }

    // Marca que o usuário já redefiniu a senha
    public boolean marcarPrimeiroLoginConcluido(String login) {

        String sql =
            "UPDATE usuario SET primeiro_login = 0 WHERE login = ?";

        try (Connection conn = ConexaoDB.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                "[UsuarioDAO] Erro ao atualizar primeiro login: "
                + e.getMessage()
            );
        }

        return false;
    }
}