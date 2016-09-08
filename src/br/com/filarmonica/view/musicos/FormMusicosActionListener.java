package br.com.filarmonica.view.musicos;

import br.com.filarmonica.models.Musico;
import br.com.filarmonica.services.MusicoService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class FormMusicosActionListener implements ActionListener {

    FormMusicos formMusicos;
    MusicoService service;

    public FormMusicosActionListener(FormMusicos formMusicos) {
        this.formMusicos = formMusicos;
        service = new MusicoService();
        addListener();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {
            case "Adicionar":
                enableOrDisableFields(true);
                enableToSave();
                break;
            case "Limpar":
                clearFields();
                break;
            case "Salvar":
                if (checkInputFields()) {
                    if (save()) {
                        JOptionPane.showMessageDialog(null, "Músico salvo com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        clearFields();
                        enableOrDisableFields(false);
                        enableToNew();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar músico.", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    break;
                }

        }
    }

    public void addListener() {
        formMusicos.getButtonAdd().addActionListener(this);
        formMusicos.getButtonSave().addActionListener(this);
    }

    public boolean checkInputFields() {
        if (!formMusicos.getTextFieldNome().getText().isEmpty() && !(formMusicos.getComboInstrumentos().getSelectedItem().equals("Selecione"))) {
            return true;
        }
        return false;
    }

    public void clearFields() {
        formMusicos.getTextFieldId().setText(null);
        formMusicos.getTextFieldNome().setText(null);
        formMusicos.getTextFieldApelido().setText(null);
        formMusicos.getTextFieldTelefone().setText(null);
        formMusicos.getTextFieldEmail().setText(null);
        formMusicos.getTextFieldAnoIngresso().setText(null);
        formMusicos.getComboInstrumentos().setSelectedIndex(0);
    }

    public void enableOrDisableFields(boolean valor) {
        formMusicos.getTextFieldNome().setEnabled(valor);
        formMusicos.getTextFieldApelido().setEnabled(valor);
        formMusicos.getTextFieldAnoIngresso().setEnabled(valor);
        formMusicos.getTextFieldTelefone().setEnabled(valor);
        formMusicos.getTextFieldEmail().setEnabled(valor);
        formMusicos.getComboInstrumentos().setEnabled(valor);
    }

    public void enableToEdit() {
        formMusicos.getButtonAdd().setEnabled(false);
        formMusicos.getButtonSave().setEnabled(false);
        formMusicos.getButtonEdit().setEnabled(true);
        formMusicos.getButtonDelete().setEnabled(true);
    }

    public void enableToNew() {
        formMusicos.getButtonAdd().setEnabled(true);
        formMusicos.getButtonSave().setEnabled(false);
        formMusicos.getButtonEdit().setEnabled(false);
        formMusicos.getButtonDelete().setEnabled(false);
    }

    public void enableToSave() {
        formMusicos.getButtonAdd().setEnabled(false);
        formMusicos.getButtonSave().setEnabled(true);
        formMusicos.getButtonEdit().setEnabled(false);
        formMusicos.getButtonDelete().setEnabled(false);
    }

    public Musico formToModel() {
        Musico musico = new Musico();
        if (!"".equals(formMusicos.getTextFieldId().getText())) {
            musico.setId(Integer.parseInt(formMusicos.getTextFieldId().getText()));
        }
        musico.setNome(formMusicos.getTextFieldNome().getText());
        musico.setApelido(formMusicos.getTextFieldApelido().getText());
        musico.setTelefone(formMusicos.getTextFieldTelefone().getText());
        musico.setEmail(formMusicos.getTextFieldEmail().getText());
        musico.setInstrumento(formMusicos.getComboInstrumentos().getSelectedItem().toString());
        musico.setAnoIngresso(Integer.parseInt(formMusicos.getTextFieldAnoIngresso().getText()));
        return musico;
    }
    
    public boolean save() {
        return service.save(formToModel());
    }
}
