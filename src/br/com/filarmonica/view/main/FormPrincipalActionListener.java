package br.com.filarmonica.view.main;

import br.com.filarmonica.constants.Constantes;
import br.com.filarmonica.view.financeiro.FormFinancas;
import br.com.filarmonica.view.frequencia.FormFrequencia;
import br.com.filarmonica.view.musicos.FormMusicos;
import br.com.filarmonica.view.tocatas.FormTocatas;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JOptionPane;

public class FormPrincipalActionListener implements ActionListener {

    private FormPrincipal formPrincipal;
    private PrincipalService service;
    
    public FormPrincipalActionListener(FormPrincipal formPrincipal) {
        this.formPrincipal = formPrincipal;
        formPrincipal.setResizable(false);
        service = new PrincipalService();
        addListener();
        changeApplicationIcon();
        checkConnection();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {
            case "Caixa":
                FormFinancas formFinancas = new FormFinancas();
                formPrincipal.getDesktopPanel().add(formFinancas);
                formFinancas.setVisible(true);
                formFinancas.setCenterPosition();
                break;
                
            case "Lista":
                FormFrequencia formFrequencia = new FormFrequencia();
                formPrincipal.getDesktopPanel().add(formFrequencia);
                formFrequencia.setVisible(true);
                break;

            case "Musicos":
                FormMusicos formMusicos = new FormMusicos();
                formPrincipal.getDesktopPanel().add(formMusicos);
                formMusicos.setVisible(true);
                break;

            case "Sair":
                int confirma = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Saindo...", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;

            case "Sobre":
                JOptionPane.showMessageDialog(null, "Projeto desenvolvido para uso interno da Filarmônica Recreio Caicoense", Constantes.Version.getNome() + ": " + Constantes.Version.getValor(), JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Tocatas":
                FormTocatas formTocatas = new FormTocatas();
                formPrincipal.getDesktopPanel().add(formTocatas);
                formTocatas.setVisible(true);
                break;
        }
    }

    public void addListener() {
        formPrincipal.getMenuItemMusicos().addActionListener(this);
        formPrincipal.getMenuItemTocatas().addActionListener(this);
        formPrincipal.getMenuItemLista().addActionListener(this);
        formPrincipal.getMenuItemSobre().addActionListener(this);
        formPrincipal.getMenuItemSair().addActionListener(this);
        formPrincipal.getMenuItemControleCaixa().addActionListener(this);
    }

    private void changeApplicationIcon() {
        URL url = this.formPrincipal.getClass().getResource("/br/com/filarmonica/images/folder.png");
        Image imageTitle = Toolkit.getDefaultToolkit().getImage(url);
        this.formPrincipal.setIconImage(imageTitle);
    }
    
    public void checkConnection() {
        if(service.checkConnection() != null) {
            formPrincipal.getLabelConexao().setText("Conectado");
            formPrincipal.getLabelConexao().setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/filarmonica/images/database-connect.png")));
        }
    }
}
