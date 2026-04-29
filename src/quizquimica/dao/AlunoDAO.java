package quizquimica.dao;

import quizquimica.model.Aluno;
import quizquimica.util.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public Aluno buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuário WHERE Login = ? AND Tipo = 'aluno'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                    rs.getInt("idUsuário"),
                    rs.getString("Nome"),
                    rs.getString("Login"),
                    rs.getString("Senha"),
                    rs.getString("Turma")
                );
            }
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    public boolean inserir(Aluno aluno) {
        String sql = "INSERT INTO usuário (Nome, Login, Senha, Tipo, Turma) VALUES (?, ?, ?, 'aluno', ?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getLogin());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, aluno.getTurma());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao inserir aluno: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(String login) {
        String sql = "DELETE FROM usuário WHERE Login = ? AND Tipo = 'aluno'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao remover aluno: " + e.getMessage());
        }
        return false;
    }

    public List<Aluno> listarPorTurma(String turma) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM usuário WHERE Turma = ? AND Tipo = 'aluno'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alunos.add(new Aluno(
                    rs.getInt("idUsuário"),
                    rs.getString("Nome"),
                    rs.getString("Login"),
                    rs.getString("Senha"),
                    rs.getString("Turma")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }
}
