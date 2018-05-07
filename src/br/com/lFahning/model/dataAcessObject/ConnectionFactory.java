package br.com.lFahning.model.dataAcessObject;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rafael Carvalhal
 */
public class ConnectionFactory {

    public static Connection getConnectionMSAccess(String filename) throws SQLException {
        Connection connection = null;
        try {
            //Verifica funcionamento do driver de conexão
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //Verifica se o arquivo do banco informado existe.
            File file = new File(filename);
            if (!file.exists()) {
                throw new SQLException("Arquivo de banco de dados local não encontrado!\n"
                        + filename);
            }
            //Montando String de conexão
            String database = "jdbc:ucanaccess://" + filename.trim();
            //Inicia a conexão com o banco de dados
            connection = DriverManager.getConnection(database);
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver de conexão com banco de dados não encontrado!");
        }
        return connection;
    }

    public static Connection getConnectionMySQL(String host, int port, String user, String password, String database) throws SQLException {
        Connection connection;
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            //carregar driver (não necessário atualmente, mas testo a existência da biblioteca)
            Class.forName(driver);
            //inicia conexão
            connection = (Connection) DriverManager.getConnection(getUrlConnection(host, port, database), user, password);
        } catch (SQLException ex) {
            throw new SQLException("Não foi possível conectar ao banco de dados remoto!");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver de conexão '" + driver + "' inexistente!");
        }
        return connection;
    }
    
    //Método apenas para eviar muitas concatenações de string
    private static String getUrlConnection(String host, int port, String database) {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(database).append("?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false");
        return sb.toString();
    }
    
    /*
    public static Connection getConnectionMySQL() throws SQLException {
        Connection connection = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String stringConnection = "jdbc:mysql://192.168.15.10:3306/UMCK?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
            
            connection = DriverManager.getConnection(stringConnection, "admin", "admin");
            //connection = DriverManager.getConnection("jdbc:mysql://192.168.15.10/UMCK", "admin", "admin");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver de conexão com banco de dados não encontrado!");
        }
        return connection;
    }
    */
    
    /*
    private static void imprimirTabelasNaConsole(Connection connection) throws SQLException {
        //Faz a leitura dos metadados do Banco
        java.sql.DatabaseMetaData d = connection.getMetaData();
        //Consulta e mostra na console
        try (java.sql.ResultSet rs = d.getTables(null, null, "%", null)) {
            while (rs.next()) {
                System.out.println(rs.getString(3));
            }
            rs.close();
        }
    }
    */

    /*
    public static void main(String[] args) {
        try {
            Connection con;
            System.out.println("Conectando ao MSAccess...");
            con = getConnectionMSAccess("/Users/fael/Desktop/WCSIM20141229.MDB");
            System.out.println("Listando tabelas do MSAccess...");
            imprimirTabelasNaConsole(con);
            con.close();
            System.out.println("Conectado ao MSAccess!");
            System.out.println();
            System.out.println("Conectando ao MySQL...");
            getConnectionMySQL("192.168.15.10", 3306, "admin", "admin", "UMCK");
            System.out.println("Conectado ao MySQL!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println();
            ex.printStackTrace();
        }
    }
    */
    
}
