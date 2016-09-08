/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filarmonica.main;

import br.com.filarmonica.view.frequencia.FormFrequencia;
import br.com.filarmonica.constants.Constantes;
import br.com.filarmonica.view.musicos.FormMusicos;
import br.com.filarmonica.view.tocatas.FormTocatas;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcelo Augusto
 */
public class FormPrincipalActionListener implements ActionListener {

    private FormPrincipal formPrincipal;

    public FormPrincipalActionListener(FormPrincipal formPrincipal) {
        this.formPrincipal = formPrincipal;
        addListener();
        changeApplicationIcon();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {

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
    }

    private void changeApplicationIcon() {
        URL url = this.formPrincipal.getClass().getResource("/br/com/filarmonica/images/icon-app.png");
        Image imageTitle = Toolkit.getDefaultToolkit().getImage(url);
        this.formPrincipal.setIconImage(imageTitle);
    }
}
