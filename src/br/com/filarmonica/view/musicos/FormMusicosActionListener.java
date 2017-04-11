package br.com.filarmonica.view.musicos;

import br.com.filarmonica.models.Musico;
import br.com.filarmonica.services.MusicoService;
import br.com.filarmonica.utilities.AlignCell;
import br.com.filarmonica.utilities.ShowMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormMusicosActionListener implements ActionListener, ListSelectionListener {

    FormMusicos formMusicos;
    MusicoService service;
    MusicosTableModel tableModel;

    public FormMusicosActionListener(FormMusicos formMusicos) {
        this.formMusicos = formMusicos;
        service = new MusicoService();
        addListener();
        startTable();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {
            case "Adicionar":
                enableFields(true);
                enableToSave();
                break;
            case "Cancelar":
                enableToNew();
                enableFields(false);
                clearFields();
                break;
            case "Deletar":
                boolean value = ShowMessage.question("Deseja deletar este músico?");
                if (value) {
                    if (delete(formToModel().getId())) {
                        ShowMessage.msgSuccess("Músico deletado com sucesso.");
                        startTable();
                        enableToNew();
                        enableFields(false);
                        clearFields();
                        break;
                    }
                }
                break;
            case "Editar":
                if (checkInputFields()) {
                    if (save()) {
                        ShowMessage.msgSuccess("Músico editado com sucesso.");
                        clearFields();
                        enableFields(false);
                        enableToNew();
                        startTable();
                        break;
                    } else {
                        ShowMessage.msgError("Erro ao salvar músico.");
                        break;
                    }
                }
            case "Limpar":
                clearFields();
                break;
            case "Salvar":
                if (checkInputFields()) {
                    if (save()) {
                        ShowMessage.msgSuccess("Músico salvo com sucesso.");
                        clearFields();
                        enableFields(false);
                        enableToNew();
                        startTable();
                        break;
                    } else {
                        ShowMessage.msgError("Erro ao salvar músico.");
                        break;
                    }
                } else {
                    ShowMessage.msgWarning("Preencha os campos obrigatórios.");
                    break;
                }

        }
    }

    public void addListener() {
        formMusicos.getButtonAdd().addActionListener(this);
        formMusicos.getButtonClear().addActionListener(this);
        formMusicos.getButtonDelete().addActionListener(this);
        formMusicos.getButtonEdit().addActionListener(this);
        formMusicos.getButtonSave().addActionListener(this);
        formMusicos.getButtonCancel().addActionListener(this);
        formMusicos.getTextFieldSearch().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                startTable(formMusicos.getTextFieldSearch().getText());
            }
        });
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

    public boolean delete(int id) {
        return service.delete(id);
    }

    public void enableFields(boolean valor) {
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
        formMusicos.getButtonCancel().setEnabled(true);
    }

    public void enableToNew() {
        formMusicos.getButtonAdd().setEnabled(true);
        formMusicos.getButtonSave().setEnabled(false);
        formMusicos.getButtonEdit().setEnabled(false);
        formMusicos.getButtonDelete().setEnabled(false);
        formMusicos.getButtonCancel().setEnabled(false);
    }

    public void enableToSave() {
        formMusicos.getButtonAdd().setEnabled(false);
        formMusicos.getButtonSave().setEnabled(true);
        formMusicos.getButtonEdit().setEnabled(false);
        formMusicos.getButtonDelete().setEnabled(false);
        formMusicos.getButtonCancel().setEnabled(true);
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

    public void modelToForm(Musico m) {
        formMusicos.getTextFieldId().setText(String.valueOf(m.getId()));
        formMusicos.getTextFieldNome().setText(m.getNome());
        formMusicos.getTextFieldApelido().setText(m.getApelido());
        formMusicos.getTextFieldTelefone().setText(m.getTelefone());
        formMusicos.getTextFieldEmail().setText(m.getEmail());
        formMusicos.getComboInstrumentos().setSelectedItem(m.getInstrumento());
        formMusicos.getTextFieldAnoIngresso().setText(String.valueOf(m.getAnoIngresso()));
    }

    public boolean save() {
        return service.save(formToModel());
    }

    public void startTable() {
        List<Musico> musicos = service.listMusicos();
        if (musicos != null) {
            tableModel = new MusicosTableModel(musicos);
            formMusicos.getTableMusicos().setModel(tableModel);
            formMusicos.getTableMusicos().getSelectionModel().addListSelectionListener(this);
            /*--- SETANDO A LARGURA DAS COLUNAS-----*/
            formMusicos.getTableMusicos().getColumnModel().getColumn(0).setPreferredWidth(5);
            formMusicos.getTableMusicos().getColumnModel().getColumn(1).setPreferredWidth(210);
            formMusicos.getTableMusicos().getColumnModel().getColumn(2).setPreferredWidth(20);
            /*--- ALINHANDO AS CÉLULAS AO CENTRO ----*/
            AlignCell.center(formMusicos.getTableMusicos().getColumnModel().getColumn(0));
            AlignCell.center(formMusicos.getTableMusicos().getColumnModel().getColumn(1));
            AlignCell.center(formMusicos.getTableMusicos().getColumnModel().getColumn(2));
        }
    }

    public void startTable(String nomeMusico) {
        List<Musico> musicos = service.search(nomeMusico);
        tableModel = new MusicosTableModel(musicos);
        formMusicos.getTableMusicos().setModel(tableModel);
        formMusicos.getTableMusicos().getSelectionModel().addListSelectionListener(this);
        /*--- SETANDO A LARGURA DAS COLUNAS-----*/
        formMusicos.getTableMusicos().getColumnModel().getColumn(0).setPreferredWidth(5);
        formMusicos.getTableMusicos().getColumnModel().getColumn(1).setPreferredWidth(210);
        formMusicos.getTableMusicos().getColumnModel().getColumn(2).setPreferredWidth(20);
        /*--- ALINHANDO AS CÉLULAS AO CENTRO ----*/
        AlignCell.center(formMusicos.getTableMusicos().getColumnModel().getColumn(0));
        AlignCell.center(formMusicos.getTableMusicos().getColumnModel().getColumn(1));
        AlignCell.center(formMusicos.getTableMusicos().getColumnModel().getColumn(2));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (formMusicos.getTableMusicos().getSelectedRow() >= 0) {
            Musico m = tableModel.getMusicos().get(formMusicos.getTableMusicos().getSelectedRow());
            modelToForm(m);
            enableFields(true);
            enableToEdit();
        }
    }
}
