package br.com.lFahning.model.dataAcessObject;

import br.com.lFahning.model.Grafico;
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
public class GraficoDAO extends DAO<Grafico> {

    public void insert(Connection connection, Grafico g) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO grafico (data,hora,SPM1,SPM2,SPM3,SPM4,"
                    + "TOTSPM,TOTSTK,VAZENT,VAZSAIDA,FLUXORET,VOLTANQUE,PBENG,PCHOKE,"
                    + "PKILL,TAXAPENET,TORQUE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            prepararStmtInsert(pstmt, g);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePstmt(pstmt);
        }
    }

    public List<Grafico> getAll(Connection connection) throws SQLException {
        List<Grafico> graficos = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            rs = execSelect(connection, stmt,
                    "select * from grafico order by substring(data, 7, 4),"
                    + " substring(data, 4, 2), substring(data, 1, 2), hora");
            while (rs.next()) {
                graficos.add(InstantElementFromResultSet(rs));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRs(rs);
            closeStmt(stmt);
        }
        return graficos;
    }

    public Grafico getAlarme(Connection connection, Grafico g) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("select * from grafico where data=?,hora=? order by substring(data, 7, 4),"
                    + " substring(data, 4, 2), substring(data, 1, 2), hora");
            prepararStmtSelectElement(pstmt, g);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return InstantElementFromResultSet(rs);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePstmt(pstmt);
            closeRs(rs);
        }
        return null;
    }

    private void prepararStmtInsert(PreparedStatement pstmt, Grafico g) throws SQLException {
        int i = 0;
        pstmt.setString(++i, g.getData());
        pstmt.setString(++i, g.getHora());
        pstmt.setFloat(++i, g.getSpm1());
        pstmt.setFloat(++i, g.getSpm2());
        pstmt.setFloat(++i, g.getSpm3());
        pstmt.setFloat(++i, g.getSpm4());
        pstmt.setFloat(++i, g.getTotspm());
        pstmt.setFloat(++i, g.getTotstk());
        pstmt.setFloat(++i, g.getVazent());
        pstmt.setFloat(++i, g.getVazsaida());
        pstmt.setFloat(++i, g.getFluxoret());
        pstmt.setFloat(++i, g.getVoltanque());
        pstmt.setFloat(++i, g.getPbeng());
        pstmt.setFloat(++i, g.getPchoke());
        pstmt.setFloat(++i, g.getPkill());
        pstmt.setFloat(++i, g.getTaxapenet());
        pstmt.setFloat(++i, g.getTorque());
    }

    private void prepararStmtSelectElement(PreparedStatement pstmt, Grafico g) throws SQLException {
        int i = 0;
        pstmt.setString(++i, g.getData());
        pstmt.setString(++i, g.getHora());
    }

    private Grafico InstantElementFromResultSet(ResultSet rs) throws SQLException {
        Grafico g = new Grafico();
        g.setData(rs.getString("data"));
        g.setHora(rs.getString("hora"));
        g.setSpm1(rs.getFloat("spm1"));
        g.setSpm2(rs.getFloat("spm2"));
        g.setSpm3(rs.getFloat("spm3"));
        g.setSpm4(rs.getFloat("spm4"));
        g.setTotspm(rs.getFloat("TOTSPM"));
        g.setTotstk(rs.getFloat("TOTSTK"));
        g.setVazent(rs.getFloat("VAZENT"));
        g.setVazsaida(rs.getFloat("VAZSAIDA"));
        g.setFluxoret(rs.getFloat("FLUXORET"));
        g.setVoltanque(rs.getFloat("VOLTANQUE"));
        g.setPbeng(rs.getFloat("PBENG"));
        g.setPchoke(rs.getFloat("PCHOKE"));
        g.setPkill(rs.getFloat("PKILL"));
        g.setTaxapenet(rs.getFloat("TAXAPENET"));
        g.setTorque(rs.getFloat("TORQUE"));
        return g;
    }

}
