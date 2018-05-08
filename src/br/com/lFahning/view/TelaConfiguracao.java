
package br.com.lFahning.view;

import br.com.lFahning.controller.TelaConfiguracaolController;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Rafael Carvalhal
 */
public class TelaConfiguracao extends javax.swing.JFrame {

    private TelaConfiguracaolController controller;
    
    public TelaConfiguracao() {
        initComponents();
        addActions();
        setLocationRelativeTo(null);
        setInformacoesPadrao();
        setVisible(true);
        controller = new TelaConfiguracaolController(this);
        getRootPane().setDefaultButton(btn_iniciarIntegracao);
        btn_iniciarIntegracao.requestFocus();
    }
    
    private void addActions() {
        btn_selecionarArquivo.addActionListener((ActionEvent e) -> {
            controller.selecionarArquivo();
        });
        btn_iniciarIntegracao.addActionListener((ActionEvent e) -> {
            controller.iniciarIntegracao();
        });
    }
    
    public void desabilitarCamposDaTela() {
        btn_iniciarIntegracao.setEnabled(false);
        btn_selecionarArquivo.setEnabled(false);
        txt_arquivo.setEnabled(false);
        txt_banco.setEnabled(false);
        txt_porta.setEnabled(false);
        txt_senha.setEnabled(false);
        txt_servidor.setEnabled(false);
        txt_usuario.setEnabled(false);
    }

    //<editor-fold defaultstate="collapsed" desc=" Códigos gerados ">
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_principal = new javax.swing.JPanel();
        painel_bdLocal = new javax.swing.JPanel();
        btn_selecionarArquivo = new javax.swing.JButton();
        txt_arquivo = new javax.swing.JTextField();
        painel_bdRemoto = new javax.swing.JPanel();
        lbl_servidor = new javax.swing.JLabel();
        txt_servidor = new javax.swing.JTextField();
        txt_porta = new javax.swing.JTextField();
        txt_banco = new javax.swing.JTextField();
        lbl_porta = new javax.swing.JLabel();
        lbl_banco = new javax.swing.JLabel();
        lbl_usuario = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_senha = new javax.swing.JTextField();
        lbl_senha = new javax.swing.JLabel();
        btn_iniciarIntegracao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        painel_bdLocal.setBorder(javax.swing.BorderFactory.createTitledBorder("Definir banco de dados local"));

        btn_selecionarArquivo.setText("Selecionar Arquivo");
        btn_selecionarArquivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        txt_arquivo.setEditable(false);

        javax.swing.GroupLayout painel_bdLocalLayout = new javax.swing.GroupLayout(painel_bdLocal);
        painel_bdLocal.setLayout(painel_bdLocalLayout);
        painel_bdLocalLayout.setHorizontalGroup(
            painel_bdLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_bdLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_selecionarArquivo)
                .addGap(18, 18, 18)
                .addComponent(txt_arquivo)
                .addContainerGap())
        );
        painel_bdLocalLayout.setVerticalGroup(
            painel_bdLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_bdLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_bdLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_selecionarArquivo)
                    .addComponent(txt_arquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painel_bdRemoto.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do banco remoto"));

        lbl_servidor.setText("Servidor");

        lbl_porta.setText("Porta");

        lbl_banco.setText("Banco");

        lbl_usuario.setText("Usuário");

        lbl_senha.setText("Senha");

        javax.swing.GroupLayout painel_bdRemotoLayout = new javax.swing.GroupLayout(painel_bdRemoto);
        painel_bdRemoto.setLayout(painel_bdRemotoLayout);
        painel_bdRemotoLayout.setHorizontalGroup(
            painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_bdRemotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_usuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(txt_servidor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_servidor, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lbl_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painel_bdRemotoLayout.createSequentialGroup()
                        .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_porta)
                            .addComponent(lbl_porta)
                            .addComponent(txt_senha, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_banco)
                            .addComponent(txt_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_senha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painel_bdRemotoLayout.setVerticalGroup(
            painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painel_bdRemotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_servidor)
                    .addComponent(lbl_porta)
                    .addComponent(lbl_banco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_servidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_banco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_usuario)
                    .addComponent(lbl_senha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painel_bdRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_iniciarIntegracao.setText("Iniciar integração");
        btn_iniciarIntegracao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel_principalLayout = new javax.swing.GroupLayout(jPanel_principal);
        jPanel_principal.setLayout(jPanel_principalLayout);
        jPanel_principalLayout.setHorizontalGroup(
            jPanel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painel_bdLocal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painel_bdRemoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_principalLayout.createSequentialGroup()
                        .addComponent(btn_iniciarIntegracao)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_principalLayout.setVerticalGroup(
            jPanel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_principalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painel_bdLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painel_bdRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_iniciarIntegracao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel_principal, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Varáveis da tela ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_iniciarIntegracao;
    private javax.swing.JButton btn_selecionarArquivo;
    private javax.swing.JPanel jPanel_principal;
    private javax.swing.JLabel lbl_banco;
    private javax.swing.JLabel lbl_porta;
    private javax.swing.JLabel lbl_senha;
    private javax.swing.JLabel lbl_servidor;
    private javax.swing.JLabel lbl_usuario;
    private javax.swing.JPanel painel_bdLocal;
    private javax.swing.JPanel painel_bdRemoto;
    private javax.swing.JTextField txt_arquivo;
    private javax.swing.JTextField txt_banco;
    private javax.swing.JTextField txt_porta;
    private javax.swing.JTextField txt_senha;
    private javax.swing.JTextField txt_servidor;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables

    public JButton getBtn_iniciarIntegracao() {
        return btn_iniciarIntegracao;
    }

    public JButton getBtn_selecionarArquivo() {
        return btn_selecionarArquivo;
    }

    public JTextField getTxt_arquivo() {
        return txt_arquivo;
    }

    public JTextField getTxt_banco() {
        return txt_banco;
    }

    public JTextField getTxt_porta() {
        return txt_porta;
    }

    public JTextField getTxt_senha() {
        return txt_senha;
    }

    public JTextField getTxt_servidor() {
        return txt_servidor;
    }

    public JTextField getTxt_usuario() {
        return txt_usuario;
    }
    //</editor-fold>

    private void setInformacoesPadrao() {
        txt_arquivo.setText("/Users/fael/Desktop/WCSIM20141229.MDB");
        txt_banco.setText("UMCK");
        txt_porta.setText("3306");
        txt_senha.setText("admin");
        txt_servidor.setText("192.168.15.10");
        txt_usuario.setText("admin");
    }
    
}
