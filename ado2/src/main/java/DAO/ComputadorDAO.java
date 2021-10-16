package DAO;

import Utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Computador;

public class ComputadorDAO {

    public static boolean create(Computador pc) throws SQLException {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement commandSQL = null;

        try {
            conexao = Conexao.conexaoOn();
            commandSQL = conexao.prepareStatement("INSERT INTO computador (marca, HD, processador) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            commandSQL.setString(1, pc.getMarca());
            commandSQL.setString(2, pc.getHD());
            commandSQL.setString(3, pc.getProcessador());

            int linhasAfetadas = commandSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
                ResultSet generatedKeys = commandSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pc.setComputador_id(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao obter o ID do computador.");
                }
            } else {
                retorno = false;
            }
        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("Erro: " + err.getMessage());
            retorno = false;
        } finally {
            try {
                if (commandSQL != null) {
                    commandSQL.close();
                }
                Conexao.conexaoOff();
            } catch (SQLException err) {
                System.out.println("Erro: " + err.getMessage());
            }
        }
        return retorno;
    }

    public static boolean update(Computador pc) throws SQLException {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement commandSQL = null;

        try {
            conexao = Conexao.conexaoOn();

            commandSQL = conexao.prepareStatement("UPDATE computador SET marca = ?, HD = ?, processador = ? WHERE computador_id = ? ");

            commandSQL.setString(1, pc.getMarca());
            commandSQL.setString(2, pc.getHD());
            commandSQL.setString(3, pc.getProcessador());
            commandSQL.setInt(4, pc.getComputador_id());

            int linhasAfetadas = commandSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("Erro: " + err.getMessage());
            retorno = false;
        } finally {
            try {
                if (commandSQL != null) {
                    commandSQL.close();
                }
                Conexao.conexaoOff();
            } catch (SQLException err) {
                System.out.println("Erro: " + err.getMessage());
            }
        }
        return retorno;
    }

    public static boolean delete(int computador_id) throws SQLException {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement commandSQL = null;

        try {
            conexao = Conexao.conexaoOn();

            commandSQL = conexao.prepareStatement("DELETE FROM computador WHERE computador_id = ?");

            commandSQL.setInt(1, computador_id);

            int linhasAfetadas = commandSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            } else {
                retorno = false;
            }

        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("Erro: " + err.getMessage());
            retorno = false;
        } finally {
            try {
                if (commandSQL != null) {
                    commandSQL.close();
                }
                Conexao.conexaoOff();
            } catch (SQLException err) {
                System.out.println("Erro: " + err.getMessage());
            }
        }
        return retorno;
    }

    public static ArrayList<Computador> getComputadores() {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement commandSQL = null;

        ArrayList<Computador> listComputadores = new ArrayList<Computador>();

        try {
            conexao = Conexao.conexaoOn();

            commandSQL = conexao.prepareStatement("SELECT * FROM computador;");

            rs = commandSQL.executeQuery();

            //Percorrer o resultSet
            while (rs.next()) {
                Computador pc = new Computador();
                pc.setComputador_id(rs.getInt("computador_id"));
                pc.setMarca(rs.getString("marca"));
                pc.setHD(rs.getString("HD"));
                pc.setProcessador(rs.getString("processador"));

                listComputadores.add(pc);
            }

        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("Erro: " + err.getMessage());
            listComputadores = null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (commandSQL != null) {
                    commandSQL.close();
                }
                Conexao.conexaoOff();
            } catch (SQLException err) {
                System.out.println("Erro: " + err.getMessage());
            }
        }
        return listComputadores;
    }
    
    public static ArrayList<Computador> getComputadores(String processador) {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement commandSQL = null;

        ArrayList<Computador> listComputadores = new ArrayList<Computador>();

        try {
            conexao = Conexao.conexaoOn();

            commandSQL = conexao.prepareStatement("SELECT * FROM computador WHERE processador LIKE ?;");
            commandSQL.setString(1, "%" + processador + "%");
            
            rs = commandSQL.executeQuery();

            //Percorrer o resultSet
            while (rs.next()) {
                Computador pc = new Computador();
                pc.setComputador_id(rs.getInt("computador_id"));
                pc.setMarca(rs.getString("marca"));
                pc.setHD(rs.getString("HD"));
                pc.setProcessador(rs.getString("processador"));

                listComputadores.add(pc);
            }

        } catch (SQLException | ClassNotFoundException err) {
            System.out.println("Erro: " + err.getMessage());
            listComputadores = null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (commandSQL != null) {
                    commandSQL.close();
                }
                Conexao.conexaoOff();
            } catch (SQLException err) {
                System.out.println("Erro: " + err.getMessage());
            }
        }
        return listComputadores;
    }
}

