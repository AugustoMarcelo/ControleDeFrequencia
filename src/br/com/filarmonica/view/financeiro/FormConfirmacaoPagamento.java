package br.com.filarmonica.view.financeiro;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author Marcelo Augusto
 */
public class FormConfirmacaoPagamento extends javax.swing.JInternalFrame {
    
    FormConfirmacaoPagamentoActionListener listener;
    
    public FormConfirmacaoPagamento() {
        initComponents();
        listener = new FormConfirmacaoPagamentoActionListener(this);
    }

    public JButton getButtonCancelar() {
        return buttonCancelar;
    }

    public JButton getButtonConfirmacao() {
        return buttonConfirmacao;
    }
    
    public void setFieldValues(int id, String nome) {
        getTextFieldIdMusico().setText(String.valueOf(id));
        getTextFieldNomeMusico().setText(nome);
    }
    
    public void setCenterPosition() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public JTextField getTextFieldIdMusico() {
        return textFieldIdMusico;
    }

    public void setTextFieldIdMusico(JTextField textFieldIdMusico) {
        this.textFieldIdMusico = textFieldIdMusico;
    }

    public JTextField getTextFieldNomeMusico() {
        return textFieldNomeMusico;
    }

    public void setTextFieldNomeMusico(JTextField textFieldNomeMusico) {
        this.textFieldNomeMusico = textFieldNomeMusico;
    }

    public JTextField getTextFieldValor() {
        return textFieldValor;
    }

    public void setTextFieldValor(JTextField textFieldValor) {
        this.textFieldValor = textFieldValor;
    }

    public JDateChooser getDataChooserData() {
        return dataChooserData;
    }

    public void setDataChooserData(JDateChooser dataChooserData) {
        this.dataChooserData = dataChooserData;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textFieldIdMusico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textFieldNomeMusico = new javax.swing.JTextField();
        textFieldValor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buttonCancelar = new javax.swing.JButton();
        buttonConfirmacao = new javax.swing.JButton();
        labelData = new javax.swing.JLabel();
        dataChooserData = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setTitle("Confirmação de Pagamento");

        jLabel1.setText("ID:");
        jLabel1.setEnabled(false);

        textFieldIdMusico.setEnabled(false);
        textFieldIdMusico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldIdMusicoActionPerformed(evt);
            }
        });

        jLabel2.setText("Músico:");
        jLabel2.setEnabled(false);

        textFieldNomeMusico.setEnabled(false);
        textFieldNomeMusico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldNomeMusicoActionPerformed(evt);
            }
        });

        textFieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldValorActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor:");

        buttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/cancel_16x16.png"))); // NOI18N
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        buttonConfirmacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/ok.png"))); // NOI18N
        buttonConfirmacao.setText("Confirmar");
        buttonConfirmacao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        labelData.setText("Data:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelData, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(buttonCancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonConfirmacao))
                        .addComponent(textFieldNomeMusico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldIdMusico, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dataChooserData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addComponent(textFieldValor, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFieldIdMusico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textFieldNomeMusico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dataChooserData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar)
                    .addComponent(buttonConfirmacao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldIdMusicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldIdMusicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldIdMusicoActionPerformed

    private void textFieldNomeMusicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldNomeMusicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldNomeMusicoActionPerformed

    private void textFieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldValorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonConfirmacao;
    private com.toedter.calendar.JDateChooser dataChooserData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelData;
    private javax.swing.JTextField textFieldIdMusico;
    private javax.swing.JTextField textFieldNomeMusico;
    private javax.swing.JTextField textFieldValor;
    // End of variables declaration//GEN-END:variables
}
