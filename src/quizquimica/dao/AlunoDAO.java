package quizquimica.dao;

import quizquimica.model.Aluno;
import quizquimica.util.ConexaoDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public Aluno buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND tipo = 'aluno'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Aluno(
                    rs.getInt("idUsuario"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("turma")
                );
            }
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    public boolean inserir(Aluno aluno) {
        String sql = "INSERT INTO usuario (nome, login, senha, tipo, turma) VALUES (?, ?, ?, 'aluno', ?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getLogin());
            ps.setString(3, aluno.getSenha());
            ps.setString(4, aluno.getTurma());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao inserir aluno: " + e.getMessage());
        }
        return false;
    }

    public boolean remover(String login) {
        String sql = "DELETE FROM usuario WHERE login = ? AND tipo = 'aluno'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao remover aluno: " + e.getMessage());
        }
        return false;
    }

    public List<Aluno> listarPorTurma(String turma) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE turma = ? AND tipo = 'aluno'";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, turma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alunos.add(new Aluno(
                    rs.getInt("idUsuario"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("turma")
                ));
            }
        } catch (SQLException e) {
            System.out.println("[AlunoDAO] Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }
}