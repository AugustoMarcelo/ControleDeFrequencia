package br.com.filarmonica.view.tocatas;

import br.com.filarmonica.models.Tocata;
import br.com.filarmonica.services.TocataService;
import br.com.filarmonica.utilities.ShowMessage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class FormTocatasActionListener implements ActionListener, ListSelectionListener, KeyListener {

    FormTocatas formTocatas;
    TocataService service;
    TocatasTableModel tableModel;

    public FormTocatasActionListener(FormTocatas formTocatas) {
        this.formTocatas = formTocatas;
        service = new TocataService();
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
                boolean value = ShowMessage.question("Deseja deletar esta tocata?");
                if (value) {
                    if (delete(formToModel().getId())) {
                        ShowMessage.msgSuccess("Tocata deletada com sucesso.");
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
                        ShowMessage.msgSuccess("Tocata editada com sucesso.");
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
                        ShowMessage.msgSuccess("Tocata salva com sucesso.");
                        clearFields();
                        enableFields(false);
                        enableToNew();
                        startTable();
                        break;
                    } else {
                        ShowMessage.msgError("Erro ao salvar tocata.");
                        break;
                    }
                } else {
                    ShowMessage.msgWarning("Preencha os campos obrigatórios.");
                    break;
                }

        }
    }

    public void addListener() {
        formTocatas.getButtonAdd().addActionListener(this);
        formTocatas.getButtonCancel().addActionListener(this);
        formTocatas.getButtonClear().addActionListener(this);
        formTocatas.getButtonEdit().addActionListener(this);
        formTocatas.getButtonDelete().addActionListener(this);
        formTocatas.getButtonSave().addActionListener(this);
        formTocatas.getTextAreaComentarios().addKeyListener(this);
        formTocatas.getTextFieldSearch().addKeyListener(this);
    }

    /*-------------- ALINHANDO O CONTEÚDO DAS CÉLULAS DA TABELA -----------*/
    public void alignCell(TableColumn column, int align) {
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(align);
        column.setCellRenderer(defaultTableCellRenderer);

    }

    public boolean checkInputFields() {
        return !formTocatas.getTextFieldLocal().getText().isEmpty() && !"".equals(formTocatas.getDateChooserData().getDate()) && (formTocatas.getTextAreaComentarios().getText().length() + 1) < 255;
    }

    public void clearFields() {
        formTocatas.getTextFieldId().setText(null);
        formTocatas.getTextFieldLocal().setText(null);
        formTocatas.getTextAreaComentarios().setText(null);
        formTocatas.getDateChooserData().setDate(null);
        formTocatas.getFormattedFieldHora().setText(null);
    }

    public boolean delete(int id) {
        return service.delete(id);
    }

    public void enableFields(boolean valor) {
        formTocatas.getTextFieldLocal().setEnabled(valor);
        formTocatas.getTextAreaComentarios().setEnabled(valor);
        formTocatas.getDateChooserData().setEnabled(valor);
        formTocatas.getFormattedFieldHora().setEnabled(valor);
    }

    public void enableToEdit() {
        formTocatas.getButtonAdd().setEnabled(false);
        formTocatas.getButtonSave().setEnabled(false);
        formTocatas.getButtonEdit().setEnabled(true);
        formTocatas.getButtonDelete().setEnabled(true);
        formTocatas.getButtonCancel().setEnabled(true);
    }

    public void enableToNew() {
        formTocatas.getButtonAdd().setEnabled(true);
        formTocatas.getButtonSave().setEnabled(false);
        formTocatas.getButtonEdit().setEnabled(false);
        formTocatas.getButtonDelete().setEnabled(false);
        formTocatas.getButtonCancel().setEnabled(false);
    }

    public void enableToSave() {
        formTocatas.getButtonAdd().setEnabled(false);
        formTocatas.getButtonSave().setEnabled(true);
        formTocatas.getButtonEdit().setEnabled(false);
        formTocatas.getButtonDelete().setEnabled(false);
        formTocatas.getButtonCancel().setEnabled(true);
    }

    public Tocata formToModel() {
        Tocata t = new Tocata();
        if (!"".equals(formTocatas.getTextFieldId().getText())) {
            t.setId(Integer.parseInt(formTocatas.getTextFieldId().getText()));
        }
        t.setLocal(formTocatas.getTextFieldLocal().getText());
        t.setComentarios(formTocatas.getTextAreaComentarios().getText());
        t.setData(formTocatas.getDateChooserData().getDate());
        t.setHorario(formTocatas.getFormattedFieldHora().getText());
        return t;
    }

    public void modelToForm(Tocata t) {
        formTocatas.getTextFieldId().setText(String.valueOf(t.getId()));
        formTocatas.getTextFieldLocal().setText(t.getLocal());
        formTocatas.getTextAreaComentarios().setText(t.getComentarios());
        formTocatas.getFormattedFieldHora().setText(t.getHorario());
        formTocatas.getDateChooserData().setDate(t.getData());
    }

    public boolean save() {
        return service.save(formToModel());
    }

    public void startTable() {
        List<Tocata> tocatas = service.listTocatas();
        if (tocatas != null) {
            tableModel = new TocatasTableModel(tocatas);
            formTocatas.getTableTocatas().setModel(tableModel);
            formTocatas.getTableTocatas().getSelectionModel().addListSelectionListener(this);
            formTocatas.getTableTocatas().getColumnModel().getColumn(0).setPreferredWidth(5);
            formTocatas.getTableTocatas().getColumnModel().getColumn(1).setPreferredWidth(200);
            formTocatas.getTableTocatas().getColumnModel().getColumn(2).setPreferredWidth(10);
            formTocatas.getTableTocatas().getColumnModel().getColumn(3).setPreferredWidth(5);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(0), SwingConstants.CENTER);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(1), SwingConstants.CENTER);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(2), SwingConstants.CENTER);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(3), SwingConstants.CENTER);
        }
    }

    public void startTable(String local) {
        List<Tocata> tocatas = service.search(local);
        if (tocatas != null) {
            tableModel = new TocatasTableModel(tocatas);
            formTocatas.getTableTocatas().setModel(tableModel);
            formTocatas.getTableTocatas().getSelectionModel().addListSelectionListener(this);
            formTocatas.getTableTocatas().getColumnModel().getColumn(0).setPreferredWidth(5);
            formTocatas.getTableTocatas().getColumnModel().getColumn(1).setPreferredWidth(200);
            formTocatas.getTableTocatas().getColumnModel().getColumn(2).setPreferredWidth(10);
            formTocatas.getTableTocatas().getColumnModel().getColumn(3).setPreferredWidth(5);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(0), SwingConstants.CENTER);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(1), SwingConstants.CENTER);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(2), SwingConstants.CENTER);
            alignCell(formTocatas.getTableTocatas().getColumnModel().getColumn(3), SwingConstants.CENTER);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (formTocatas.getTableTocatas().getSelectedRow() >= 0) {
            Tocata t = tableModel.getTocatas().get(formTocatas.getTableTocatas().getSelectedRow());
            modelToForm(t);
            enableFields(true);
            enableToEdit();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getComponent().equals(formTocatas.getTextAreaComentarios())) {
            int length = formTocatas.getTextAreaComentarios().getText().length();
            formTocatas.getLabelMaxText().setText(length + "/255");
            if (length > 255) {
                formTocatas.getLabelMaxText().setForeground(Color.RED);
            } else {
                formTocatas.getLabelMaxText().setForeground(Color.BLACK);
            }
        } else {
            startTable(formTocatas.getTextFieldSearch().getText());
        }
    }
}
