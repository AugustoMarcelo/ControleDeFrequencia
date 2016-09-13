/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filarmonica.main;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Marcelo Augusto
 */
public class FormPrincipal extends javax.swing.JFrame {

    FormPrincipalActionListener listener;
    
    public FormPrincipal() {
        initComponents();
        listener = new FormPrincipalActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPanel = new javax.swing.JDesktopPane();
        labelConexao = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        menuItemMusicos = new javax.swing.JMenuItem();
        menuItemTocatas = new javax.swing.JMenuItem();
        menuFrequencia = new javax.swing.JMenu();
        menuItemLista = new javax.swing.JMenuItem();
        menuOpcoes = new javax.swing.JMenu();
        menuItemBackup = new javax.swing.JMenuItem();
        menuItemSobre = new javax.swing.JMenuItem();
        menuItemSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerência de Músicos e Tocatas");

        labelConexao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        labelConexao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConexao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/database-disconnect.png"))); // NOI18N
        labelConexao.setText("Sem conexão");
        desktopPanel.add(labelConexao);
        labelConexao.setBounds(890, 530, 120, 20);

        menuCadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/form-add.png"))); // NOI18N
        menuCadastros.setMnemonic('c');
        menuCadastros.setText("Cadastros");

        menuItemMusicos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        menuItemMusicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/add-musician.png"))); // NOI18N
        menuItemMusicos.setMnemonic('m');
        menuItemMusicos.setText("Músicos");
        menuItemMusicos.setActionCommand("Musicos");
        menuCadastros.add(menuItemMusicos);

        menuItemTocatas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menuItemTocatas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/agenda.png"))); // NOI18N
        menuItemTocatas.setMnemonic('t');
        menuItemTocatas.setText("Tocatas");
        menuCadastros.add(menuItemTocatas);

        menuBar.add(menuCadastros);

        menuFrequencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/list.png"))); // NOI18N
        menuFrequencia.setMnemonic('f');
        menuFrequencia.setText("Frequência");

        menuItemLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menuItemLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/check.png"))); // NOI18N
        menuItemLista.setMnemonic('l');
        menuItemLista.setText("Lista de Frequência");
        menuItemLista.setActionCommand("Lista");
        menuFrequencia.add(menuItemLista);

        menuBar.add(menuFrequencia);

        menuOpcoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/options.png"))); // NOI18N
        menuOpcoes.setMnemonic('o');
        menuOpcoes.setText("Opções");

        menuItemBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        menuItemBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/text-file.png"))); // NOI18N
        menuItemBackup.setMnemonic('b');
        menuItemBackup.setText("Backup (.txt)");
        menuOpcoes.add(menuItemBackup);

        menuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menuItemSobre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/info.png"))); // NOI18N
        menuItemSobre.setMnemonic('s');
        menuItemSobre.setText("Sobre");
        menuOpcoes.add(menuItemSobre);

        menuItemSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/exit.png"))); // NOI18N
        menuItemSair.setMnemonic('r');
        menuItemSair.setText("Sair");
        menuOpcoes.add(menuItemSair);

        menuBar.add(menuOpcoes);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1031, 618));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
            /*for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    public JLabel getLabelConexao() {
        return labelConexao;
    }

    public void setLabelConexao(JLabel labelConexao) {
        this.labelConexao = labelConexao;
    }
    
    public JDesktopPane getDesktopPanel() {
        return desktopPanel;
    }

    public void setDesktopPanel(JDesktopPane desktopPanel) {
        this.desktopPanel = desktopPanel;
    }

    public JMenu getMenuCadastros() {
        return menuCadastros;
    }

    public JMenu getMenuFrequencia() {
        return menuFrequencia;
    }

    public JMenuItem getMenuItemBackup() {
        return menuItemBackup;
    }

    public JMenuItem getMenuItemLista() {
        return menuItemLista;
    }

    public JMenuItem getMenuItemMusicos() {
        return menuItemMusicos;
    }

    public JMenuItem getMenuItemSair() {
        return menuItemSair;
    }

    public JMenuItem getMenuItemSobre() {
        return menuItemSobre;
    }

    public JMenuItem getMenuItemTocatas() {
        return menuItemTocatas;
    }

    public JMenu getMenuOpcoes() {
        return menuOpcoes;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPanel;
    private javax.swing.JLabel labelConexao;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenu menuFrequencia;
    private javax.swing.JMenuItem menuItemBackup;
    private javax.swing.JMenuItem menuItemLista;
    private javax.swing.JMenuItem menuItemMusicos;
    private javax.swing.JMenuItem menuItemSair;
    private javax.swing.JMenuItem menuItemSobre;
    private javax.swing.JMenuItem menuItemTocatas;
    private javax.swing.JMenu menuOpcoes;
    // End of variables declaration//GEN-END:variables

}
