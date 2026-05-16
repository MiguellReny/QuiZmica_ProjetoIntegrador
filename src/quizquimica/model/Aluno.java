package quizquimica.model;

public class Aluno extends Usuario {

    public Aluno() {
        super();
        setTipo("aluno");
    }

    public Aluno(int idUsuario, String nome, String login, String senha, String turma) {
        super(idUsuario, nome, login, senha, "aluno", turma);
    }

    @Override
    public String toString() {
        return "Aluno{id=" + getIdUsuario() + ", nome=" + getNome() + ", turma=" + getTurma() + "}";
    }
}
