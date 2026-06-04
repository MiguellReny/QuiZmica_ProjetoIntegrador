package quizquimica.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {

    private static String url;
    private static String user;
    private static String senha;

    static {
        try (InputStream input = ConexaoDB.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("[DB] config.properties não encontrado!");
            } else {
                Properties prop = new Properties();
                prop.load(input);
                url   = prop.getProperty("db.url");
                user  = prop.getProperty("db.usuario");
                senha = prop.getProperty("db.senha");
            }
        } catch (Exception e) {
            System.out.println("[DB] Erro ao carregar configurações: " + e.getMessage());
        }
    }

    private ConexaoDB() {}

    public static Connection getConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado: " + e.getMessage());
        }
        return DriverManager.getConnection(url, user, senha);
    }
}
