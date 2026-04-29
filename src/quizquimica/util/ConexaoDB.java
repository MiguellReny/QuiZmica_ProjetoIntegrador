package quizquimica.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL    = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "QuiZmica";
    private static final String SENHA   = "etecquimica10";

    private static Connection instancia = null;

    private ConexaoDB() {}

    public static Connection getConexao() {
        try {
            if (instancia == null || instancia.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instancia = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println("[DB] Conectado ao MySQL com sucesso.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("[DB] Driver MySQL nao encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[DB] Erro ao conectar: " + e.getMessage());
        }
        return instancia;
    }

    public static void fecharConexao() {
        try {
            if (instancia != null && !instancia.isClosed()) {
                instancia.close();
                System.out.println("[DB] Conexao encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("[DB] Erro ao fechar conexao: " + e.getMessage());
        }
    }
}
