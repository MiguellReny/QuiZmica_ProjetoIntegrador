package quizquimica.service;

import quizquimica.dao.AlunoDAO;
import quizquimica.dao.ProfessorDAO;
import quizquimica.dao.UsuarioDAO;
import quizquimica.model.Aluno;
import quizquimica.model.Usuario;
import quizquimica.util.CadastrarSenha;
import quizquimica.util.GeradorCredencial;
import quizquimica.util.ValidadorEmail;

public class AuthService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public Usuario login(String login, String senha) {

        // 1. Valida domínio do login
        if (!ValidadorEmail.emailValido(login)) {
            System.out.println("[AuthService] Login com domínio inválido: " + login);
            return null;
        }

        // 2. Gera hash e confere com o banco
        String senhaHash = CadastrarSenha.hashSenha(senha);
        if (!usuarioDAO.autenticar(login, senhaHash)) {
            System.out.println("[AuthService] Credenciais inválidas para: " + login);
            return null;
        }

        // 3. Identifica o tipo e retorna o objeto correto
        String tipo = usuarioDAO.buscarTipo(login);
        if ("professor".equals(tipo)) {
            return professorDAO.buscarPorLogin(login);
        }
        return alunoDAO.buscarPorLogin(login);
    }

    public String[] cadastrarAluno(String nome, String turma, String senha) {

        if (!CadastrarSenha.senhaValida(senha)) {
            System.out.println("[AuthService] Senha inválida — mínimo 6 caracteres.");
            return null;
        }

        String login = GeradorCredencial.gerarLoginAluno(nome, turma);
        String senhaHash = CadastrarSenha.hashSenha(senha);

        if (usuarioDAO.loginExiste(login)) {
            System.out.println("[AuthService] Login já existe: " + login);
            return null;
        }

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setLogin(login);
        aluno.setSenha(senhaHash);
        aluno.setTurma(turma);

        if (alunoDAO.inserir(aluno)) {
            System.out.println("[AuthService] Aluno cadastrado: " + login);
            return new String[]{login};
        }
        return null;
    }

    
    public boolean redefinirSenha(String loginAluno, String novaSenha) {
        if (!CadastrarSenha.senhaValida(novaSenha)) {
            System.out.println("[AuthService] Senha inválida — mínimo 6 caracteres.");
            return false;}
        String hash = CadastrarSenha.hashSenha(novaSenha);
        return usuarioDAO.atualizarSenha(loginAluno, hash);}
    }