package br.com.lFahning.model.dataAcessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Carlos Rafael
 */
public abstract class DAO<E> {
    
    protected ResultSet execSelect(Connection connection, Statement stmt, String sql) throws SQLException {
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        return rs;
    }
    
    protected void closeRs(ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }

    protected void closePstmt(PreparedStatement pstmt) throws SQLException {
        if (pstmt != null && !pstmt.isClosed()) {
            pstmt.close();
        }
    }

    protected void closeStmt(Statement stmt) throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
    }
    
}