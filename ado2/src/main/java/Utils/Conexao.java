package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static String status = "Não conectado";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    private static String server = "localhost";
    private static String database = "lojainformatica";

    private static String login = "root";
    private static String senha = "";

    private static String URL = "";

    private static Connection conexao;

    public Conexao() {
    }

    public static Connection conexaoOn() throws SQLException, ClassNotFoundException {

        URL = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

        if (conexao == null) {
            try {
                Class.forName(driver);
                conexao = DriverManager.getConnection(URL, login, senha);
                if (conexao != null) {
                    status = "Conexão realizada com sucesso!";
                } else {
                    status = "Não foi possivel realizar a conexão";
                }
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");
            } catch (SQLException e) {
                throw new SQLException("Erro ao estabelecer a conexão.");
            }
        } else {
            try {
                if (conexao.isClosed()) {
                    conexao = DriverManager.getConnection(URL, login, senha);
                }
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conexão.");
            }
        }
        return conexao;
    }

    public static boolean conexaoOff() throws SQLException {
        try {
            if (conexao != null) {
                if (!conexao.isClosed()) {
                    conexao.close();
                }
            }
            status = "Não conectado";
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static String getConexao() {
        return status;
    }

}
