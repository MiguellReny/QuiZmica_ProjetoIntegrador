package quizquimica.model;

import quizquimica.util.ValidadorEmail;

public abstract class Usuario {
    private int    idUsuario;
    private String nome;
    private String login;
    private String senha;
    private String tipo;
    private String turma;

    public Usuario() {}

    public Usuario(int idUsuario, String nome, String login, String senha, String tipo, String turma) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.turma = turma;
        this.tipo = ValidadorEmail.identificarPerfil(login);
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLogin() { return login; }
    public void setLogin(String login) {
        this.login = login;
        this.tipo  = ValidadorEmail.identificarPerfil(login);
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }

    @Override
    public String toString() {
        return "Usuario{id=" + idUsuario + ", nome=" + nome + ", login=" + login + ", tipo=" + tipo + ", turma=" + turma + "}";
    }
}