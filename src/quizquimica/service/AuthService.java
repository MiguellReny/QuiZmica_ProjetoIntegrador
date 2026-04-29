package quizquimica.service;

import quizquimica.dao.AlunoDAO;
import quizquimica.dao.ProfessorDAO;
import quizquimica.dao.UsuarioDAO;
import quizquimica.model.Aluno;
import quizquimica.model.Professor;
import quizquimica.model.Usuario;
import quizquimica.util.GeradorSenha;
import quizquimica.util.ValidadorEmail;

public class AuthService {

    private UsuarioDAO   usuarioDAO   = new UsuarioDAO();
    private AlunoDAO     alunoDAO     = new AlunoDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();

    // Autentica e retorna Aluno ou Professor, ou null se falhar
    public Usuario login(String login, String senha) {

        // 1. Valida domínio do login
        if (!ValidadorEmail.isEmailValido(login)) {
            System.out.println("[AuthService] Login invalido: " + login);
            return null;
        }

        // 2. Gera hash da senha
        String senhaHash = GeradorSenha.hashSenha(senha);

        // 3. Autentica no banco
        if (!usuarioDAO.autenticar(login, senhaHash)) {
            System.out.println("[AuthService] Credenciais incorretas.");
            return null;
        }

        // 4. Identifica tipo e retorna objeto correto
        String tipo = usuarioDAO.buscarTipo(login);

        if ("professor".equals(tipo)) {
            return professorDAO.buscarPorLogin(login);
        } else {
            return alunoDAO.buscarPorLogin(login);
        }
    }

    // Redefine senha de um aluno — retorna nova senha legível para o professor
    public String redefinirSenha(String loginAluno) {
        String novaSenha = GeradorSenha.gerarSenhaAleatoria();
        String hash      = GeradorSenha.hashSenha(novaSenha);
        boolean ok       = usuarioDAO.atualizarSenha(loginAluno, hash);
        return ok ? novaSenha : null;
    }
}
