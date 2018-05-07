package br.com.lFahning.controller;

import br.com.lFahning.model.Alarme;
import br.com.lFahning.model.dataAcessObject.AlarmeDAO;
import br.com.lFahning.model.dataAcessObject.ConnectionFactory;
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
            iniciarThreadIntegracao();
            desabilitarCamposDaTela();
            tela.setState(JFrame.ICONIFIED);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(tela, "Ocorreu um erro na execução da integração!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tela, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void desabilitarCamposDaTela() {
        tela.getBtn_iniciarIntegracao().setEnabled(false);
        tela.getBtn_selecionarArquivo().setEnabled(false);
        tela.getTxt_arquivo().setEnabled(false);
        tela.getTxt_banco().setEnabled(false);
        tela.getTxt_porta().setEnabled(false);
        tela.getTxt_senha().setEnabled(false);
        tela.getTxt_servidor().setEnabled(false);
        tela.getTxt_usuario().setEnabled(false);
    }

    private void iniciarThreadIntegracao() throws InterruptedException, Exception {
        boolean inicarIntegracao = false;
        validarCampos();
        inicarIntegracao = true;
        if (inicarIntegracao) {
            int delay = 5000;
            ActionListener taskPerformer = (ActionEvent evt) -> {
                try {
                    int diferencaAlarmes = verificarQtDiferencaAlarmes();
                    if (diferencaAlarmes != 0) {
                        integrarDiferenca();
                    }
                    int diferencaAlarmes = verificarQtDiferencaAlarmes();
                    if (diferencaAlarmes != 0) {
                        integrarDiferenca();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(tela, "Ocorreu uma falha na integração!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            };
            new Timer(delay, taskPerformer).start();
        }
    }

    private int verificarQtDiferencaAlarmes() throws SQLException {
        List<Alarme> alarmesMySQL = consultarAlarmesMySQL();
        

        return 1;
    }

    private List<Alarme> consultarAlarmesMySQL() throws SQLException, NumberFormatException {
        Connection connectionMySQL = null;
        List<Alarme> alarmes;
        try {
            connectionMySQL = ConnectionFactory.getConnectionMySQL(tela.getTxt_servidor().getText(),
                    Integer.valueOf(tela.getTxt_servidor().getText()),
                    tela.getTxt_usuario().getText(), tela.getTxt_senha().getText(),
                    tela.getTxt_banco().getText());
            AlarmeDAO alarmeDAO = new AlarmeDAO();
            alarmes = alarmeDAO.getAll(connectionMySQL);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            connectionMySQL.close();
        }
        return alarmes;
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

    private void integrarDiferenca() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
