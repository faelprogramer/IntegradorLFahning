package br.com.lFahning.model.dataAcessObject;

import br.com.lFahning.model.Alarme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Carvalhal
 */
public class AlarmeDAO extends DAO<Alarme> {

    public void insert(Connection connection, Alarme a) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO alarmes (data,hora,parametro,min,max,valor) VALUES (?,?,?,?,?,?)");
            prepararStmtInsert(pstmt, a);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePstmt(pstmt);
        }
    }

    public List<Alarme> getAll(Connection connection) throws SQLException {
        List<Alarme> alarmes = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            rs = execSelect(connection, stmt,
                    "select * from alarmes order by substring(data, 7, 4),"
                    + " substring(data, 4, 2), substring(data, 1, 2), hora");
            while (rs.next()) {
                alarmes.add(InstantAlarmeFromResultSet(rs));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRs(rs);
            closeStmt(stmt);
        }
        return alarmes;
    }

    public Alarme getAlarme(Connection connection, Alarme a) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(
                    "select * from alarmes where data=?,hora=? order by substring(data, 7, 4),"
                    + " substring(data, 4, 2), substring(data, 1, 2), hora");
            prepararStmtSelectAlarme(pstmt, a);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return InstantAlarmeFromResultSet(rs);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePstmt(pstmt);
            closeRs(rs);
        }
        return null;
    }
    public List<Alarme> getUltimosAlarmes(Connection connection, int qtUltimosRegistros) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Alarme> alarmes = new ArrayList<>();
        try {
            pstmt = connection.prepareStatement(
                    "select * from (select rownum as seq, a.* from alarmes a "
                            + "order by substring(a.data, 7, 4), substring(a.data, 4, 2), "
                            + "substring(a.data, 1, 2), a.hora) where seq >= ?");
            pstmt.setInt(1, qtUltimosRegistros);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                alarmes.add(InstantAlarmeFromResultSet(rs));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePstmt(pstmt);
            closeRs(rs);
        }
        return alarmes;
    }
    
    

    public int getQtAlarmes(Connection connection) throws SQLException {
        int qt = 0;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            rs = execSelect(connection, stmt, "select count(*) as qtd from alarmes");
            if (rs.next()) {
                qt = rs.getInt("qtd");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRs(rs);
            closeStmt(stmt);
        }
        return qt;
    }
    
    private void prepararStmtInsert(PreparedStatement pstmt, Alarme a) throws SQLException {
        int i = 0;
        pstmt.setString(++i, a.getData());
        pstmt.setString(++i, a.getHora());
        pstmt.setString(++i, a.getParametro());
        pstmt.setFloat(++i, a.getMin());
        pstmt.setFloat(++i, a.getMax());
        pstmt.setFloat(++i, a.getValor());
    }

    private void prepararStmtSelectAlarme(PreparedStatement pstmt, Alarme a) throws SQLException {
        int i = 0;
        pstmt.setString(++i, a.getData());
        pstmt.setString(++i, a.getHora());
    }

    private Alarme InstantAlarmeFromResultSet(ResultSet rs) throws SQLException {
        Alarme a = new Alarme();
        a.setData(rs.getString("data"));
        a.setHora(rs.getString("hora"));
        a.setMax(rs.getFloat("max"));
        a.setMin(rs.getFloat("min"));
        a.setParametro(rs.getString("parametro"));
        a.setStatus(rs.getString("status"));
        a.setValor(rs.getFloat("valor"));
        return a;
    }

}
