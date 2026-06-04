package quizquimica.model;

public class Professor extends Usuario {

    public Professor() {
        super();
        setTipo("professor");
    }

    public Professor(int idUsuario,
                 String nome,
                 String login,
                 String senha,
                 String turma) {

        super(idUsuario,
            nome,
            login,
            senha,
            "professor",
            turma);
    }
    
    @Override
    public String toString() {
        return "Professor{id=" + getIdUsuario() + ", nome=" + getNome() + ", login=" + getLogin() + "}";
    }
}
