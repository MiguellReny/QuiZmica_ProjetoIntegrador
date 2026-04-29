package quizquimica.model;

public class Professor extends Usuario {

    public Professor() {
        super();
        setTipo("professor");
    }

    public Professor(int idUsuario, String nome, String login, String senha) {
        super(idUsuario, nome, login, senha, "professor", null);
    }

    @Override
    public String toString() {
        return "Professor{id=" + getIdUsuario() + ", nome=" + getNome() + ", login=" + getLogin() + "}";
    }
}
