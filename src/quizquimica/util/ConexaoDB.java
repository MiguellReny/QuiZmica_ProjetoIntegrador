package quizquimica.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    private static Connection instancia = null;
    private static String url;
    private static String user;
    private static String senha;

    static {
        try (InputStream input = ConexaoDB.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                System.out.println("[DB] Arquivo config.properties nao encontrado!");
            } else {
                Properties prop = new Properties();
                prop.load(input);

                url = prop.getProperty("db.url");
                user = prop.getProperty("db.usuario");
                senha = prop.getProperty("db.senha");
            }

        } catch (Exception e) {
            System.out.println("[DB] Erro ao carregar configuracoes: " + e.getMessage());
        }
    }

    private ConexaoDB() {}

    public static Connection getConexao() {
        try {
            if (instancia == null || instancia.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instancia = DriverManager.getConnection(url, user, senha);
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