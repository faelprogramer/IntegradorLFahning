package br.com.lFahning.controller;

import br.com.lFahning.model.Alarme;
import br.com.lFahning.model.Grafico;
import br.com.lFahning.model.dataAcessObject.AlarmeDAO;
import br.com.lFahning.model.dataAcessObject.ConnectionFactory;
import br.com.lFahning.model.dataAcessObject.GraficoDAO;
import br.com.lFahning.view.TelaConfiguracao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Rafael Carvalhal
 */
public class TelaConfiguracaolController {

    private TelaConfiguracao tela;
    private final int CONEXAO_MYSQL = 1;
    private final int CONEXAO_ACCESS = 2;

    public TelaConfiguracaolController(TelaConfiguracao tela) {
        this.tela = tela;
    }

    public void selecionarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilterMDB());

        int returnVal = fileChooser.showOpenDialog(tela);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            tela.getTxt_arquivo().setText(file.getAbsolutePath());
        } else {
            tela.getTxt_arquivo().setText("");
            JOptionPane.showMessageDialog(tela, "Operação Cancelada!");
        }
    }

    public void iniciarIntegracao() {
        try {
            validarCampos();
            tela.desabilitarCamposDaTela();
            System.out.println("Validou campos");
            iniciarTarefaIntegracao();
            System.out.println("Iniciou integração");
            tela.setState(JFrame.ICONIFIED);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(tela, "Ocorreu um erro na execução da integração!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void iniciarTarefaIntegracao() throws InterruptedException, Exception {
        int delay = 10000;
        ActionListener taskPerformer = (ActionEvent evt) -> {
            try {
                int diferencaAlarmes = getQtDiferencaAlarmes();
                System.out.println("Diferença Alarmes: " + diferencaAlarmes);
                if (diferencaAlarmes > 0) {
                    System.out.println("Vai integrar alarmes: " + diferencaAlarmes);
                    integrarDiferencaAlarmes(diferencaAlarmes);
                    System.out.println("integrou alarmes: " + diferencaAlarmes);
                }
                int diferencaGraficos = getQtDiferencaGraficos();
                System.out.println("Diferença Gráficos: " + diferencaGraficos);
                if (diferencaGraficos > 0) {
                    System.out.println("Vai integrar gráficos: " + diferencaGraficos);
                    integrarDiferencaGraficos(diferencaGraficos);
                    System.out.println("integrou gráficos: " + diferencaGraficos);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(tela, "Ocorreu uma falha na integração!\n"
                        + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    private int getQtDiferencaAlarmes() throws SQLException {
        int qtdAlarmesMysql = consultarQtAlarmes(CONEXAO_MYSQL);
        int qtdAlarmesAccess = consultarQtAlarmes(CONEXAO_ACCESS);
        return qtdAlarmesAccess - qtdAlarmesMysql;
    }

    private int consultarQtAlarmes(int banco) throws SQLException {
        Connection connection = null;
        int qtd = 0;
        try {
            if (banco == CONEXAO_MYSQL) {
                connection = getConnectionMySQL();
            }
            if (banco == CONEXAO_ACCESS) {
                connection = getConnectionAccess();
            }
            qtd = new AlarmeDAO().getQtAlarmes(connection);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection(connection);
        }
        return qtd;
    }

    private int consultarQtGraficos(int banco) throws SQLException {
        Connection connection = null;
        int qtd = 0;
        try {
            if (banco == CONEXAO_MYSQL) {
                connection = getConnectionMySQL();
            }
            if (banco == CONEXAO_ACCESS) {
                connection = getConnectionAccess();
            }
            qtd = new GraficoDAO().getQtGraficos(connection);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection(connection);
        }
        return qtd;
    }

    private List<Alarme> consultarUltimosAlarmesAccess(int qt) throws SQLException, NumberFormatException {
        Connection connection = null;
        List<Alarme> alarmes;
        try {
            connection = getConnectionAccess();
            AlarmeDAO alarmeDAO = new AlarmeDAO();
            alarmes = alarmeDAO.getUltimosAlarmes(connection, qt);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection(connection);
        }
        return alarmes;
    }

    private List<Grafico> consultarUltimosGraficosAccess(int qt) throws SQLException {
        Connection connection = null;
        List<Grafico> graficos;
        try {
            connection = getConnectionAccess();
            GraficoDAO graficoDAO = new GraficoDAO();
            graficos = graficoDAO.getUltimosGraficos(connection, qt);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection(connection);
        }
        return graficos;
    }

    private void validarCampos() throws Exception {
        if (tela.getTxt_arquivo().getText().isEmpty()) {
            throw new Exception("O arquivo do banco de dados local não foi selecionado!");
        }
        if (tela.getTxt_banco().getText().isEmpty()
                || tela.getTxt_porta().getText().isEmpty()
                || tela.getTxt_senha().getText().isEmpty()
                || tela.getTxt_servidor().getText().isEmpty()
                || tela.getTxt_usuario().getText().isEmpty()) {
            throw new Exception("Faltam informações do servidor remoto!");
        }
    }

    private void integrarDiferencaAlarmes(int diferenca) throws SQLException {
        Connection connection = null;
        try {
            List<Alarme> alarmes = consultarUltimosAlarmesAccess(diferenca);
            connection = getConnectionMySQL();
            AlarmeDAO alarmeDAO = new AlarmeDAO();
            for (Alarme alarme : alarmes) {
                alarmeDAO.insert(connection, alarme);
            }
        } catch (SQLException | NumberFormatException ex) {
            throw ex;
        } finally {
            closeConnection(connection);
        }
    }

    private void integrarDiferencaGraficos(int diferenca) throws SQLException {
        Connection connection = null;
        try {
            List<Grafico> graficos = consultarUltimosGraficosAccess(diferenca);
            connection = getConnectionMySQL();
            GraficoDAO graficoDAO = new GraficoDAO();
            for (Grafico grafico : graficos) {
                graficoDAO.insert(connection, grafico);
            }
        } catch (SQLException | NumberFormatException ex) {
            throw ex;
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    private int getQtDiferencaGraficos() throws SQLException {
        int qtdGraficosMysql = consultarQtGraficos(CONEXAO_MYSQL);
        int qtdGraficosAccess = consultarQtGraficos(CONEXAO_ACCESS);
        return qtdGraficosAccess - qtdGraficosMysql;
    }

    private Connection getConnectionAccess() throws SQLException {
        return ConnectionFactory.getConnectionMSAccess(tela.getTxt_arquivo().getText());
    }

    private Connection getConnectionMySQL() throws SQLException {
        return ConnectionFactory.getConnectionMySQL(tela.getTxt_servidor().getText(),
                Integer.valueOf(tela.getTxt_porta().getText()),
                tela.getTxt_usuario().getText(), tela.getTxt_senha().getText(),
                tela.getTxt_banco().getText());
    }

}

class FileFilterMDB extends FileFilter {

    @Override
    public boolean accept(File f) {
        // permitir apenas arquivos do tipo "mdb"
        return f.getAbsolutePath().endsWith(".mdb") || f.getAbsolutePath().endsWith(".MDB");
    }

    @Override
    public String getDescription() {
        return "Banco de dados Access (*.mdb)";
    }

}
