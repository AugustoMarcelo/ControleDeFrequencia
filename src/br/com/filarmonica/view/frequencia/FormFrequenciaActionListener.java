package br.com.filarmonica.view.frequencia;

import br.com.filarmonica.constants.Constantes;
import br.com.filarmonica.models.Frequencia;
import br.com.filarmonica.models.Musico;
import br.com.filarmonica.models.Tocata;
import br.com.filarmonica.services.FrequenciaService;
import br.com.filarmonica.services.MusicoService;
import br.com.filarmonica.services.TocataService;
import br.com.filarmonica.utilities.ShowMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public final class FormFrequenciaActionListener implements ActionListener, ItemListener {

    private final FormFrequencia formFrequencia;
    private final MusicoService musicoService;
    private final TocataService tocataService;
    private final FrequenciaService frequenciaService;
    private FrequenciaTableModel tableModel;
    private Map<Integer, Musico> mapMusicos;
    private Map<Integer, Tocata> mapTocatas;
    private List<Frequencia> frequencias;

    public FormFrequenciaActionListener(FormFrequencia formFrequencia) {
        this.formFrequencia = formFrequencia;
        musicoService = new MusicoService();
        tocataService = new TocataService();
        frequenciaService = new FrequenciaService();
        mapMusicos = new HashMap<>();
        mapTocatas = new HashMap<>();
        frequencias = new ArrayList<>();
        addListener();
        startComboMusicos();
        startComboTocatas();
        startTable();
    }

    public void addListener() {
        formFrequencia.getButtonFinalizar().addActionListener(this);
        formFrequencia.getButtonAddTo().addActionListener(this);
        formFrequencia.getButtonReleaseCombo().addActionListener(this);
        formFrequencia.getComboTocatas().addItemListener(this);
    }

    public void AlignCell(TableColumn column, int align) {
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(align);
        column.setCellRenderer(center);
    }

    public void startComboMusicos() {
        List<Musico> musicos = musicoService.listMusicos();
        formFrequencia.getComboMusicos().removeAllItems();
        formFrequencia.getComboMusicos().addItem("Selecione...");
        for (Musico m : musicos) {
            formFrequencia.getComboMusicos().addItem(m.getNome() + " (" + m.getInstrumento() + ")");
            mapMusicos.put(m.getId(), new Musico(m.getNome(), m.getApelido(), m.getTelefone(), m.getEmail(), m.getInstrumento(), m.getAnoIngresso()));
        }
        formFrequencia.getComboMusicos().setSelectedIndex(0);
        /*for(Integer key : mapMusicos.keySet()) {
            List<String> infoMusico = mapMusicos.get(key);
            System.out.println(String.format("[%d] => %s", key, infoMusico));
        }*/
    }

    public void startComboTocatas() {
        List<Tocata> tocatas = tocataService.listTocatas();
        formFrequencia.getComboTocatas().removeAllItems();
        formFrequencia.getComboTocatas().addItem("Selecione...");
        for (Tocata t : tocatas) {
            formFrequencia.getComboTocatas().addItem(t.getLocal() + " (" + formatter(t.getData()) + ")");
            mapTocatas.put(t.getId(), new Tocata(t.getComentarios(), t.getData(), t.getHorario(), t.getLocal()));
        }
        formFrequencia.getComboTocatas().setSelectedIndex(0);
    }

    private String formatter(Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FormatDate.getValor());
        return sdf.format(data);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "AddTo":
                addToList();
                break;
            case "Finalizar":
                /*for(Frequencia f : frequencias) {
                    System.out.println(String.format("id_musico > %d \t id_tocata > %d \t presença > %s", f.getMusico().getId(), f.getTocata().getId(), (f.isPresenca()?1:0)));
                }*/
                if (saveAll()) {
                    ShowMessage.msgSuccess("Lista de frequência salva.");
                    clearFields();
                } else {
                    ShowMessage.msgError("Erro ao inserir lista.");
                }
                break;
            case "Liberar":
                releaseCombo(true);
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            if (formFrequencia.getComboTocatas().getSelectedIndex() != 0) {
                //LÓGICA PARA RECUPERAR A FREQUÊNCIA DE ACORDO COM A TOCATA SELECIONADA NO COMBO
//                String stringDados = Arrays.toString(formFrequencia.getComboTocatas().getSelectedItem().toString().split("\\("));
//                String[] dados = stringDados.split(",");
//                String local = removeCharacters(dados[0]);
//                String data = removeCharacters(dados[1]);
//
//                for (Integer key : mapTocatas.keySet()) {
//                    Tocata tocata = mapTocatas.get(key);
//                    if (tocata.getLocal().equals(local) && formatter(tocata.getData()).equals(data)) {
//                        tocata.setId(key);
//                    }
//                }
//                frequencias = frequenciaService.listFrequencias();
                formFrequencia.getComboTocatas().setEnabled(false);
                releaseCombo(false);
            }
        }
    }

    public void addToList() {
        if (checkInputFields()) {
            Frequencia f = new Frequencia();
            String stringDados = Arrays.toString(formFrequencia.getComboTocatas().getSelectedItem().toString().split("\\("));
            String[] dados = stringDados.split(",");
            String local = removeCharacters(dados[0]);
            String data = removeCharacters(dados[1]);

            for (Integer key : mapTocatas.keySet()) {
                Tocata tocata = mapTocatas.get(key);
                if (tocata.getLocal().equals(local) && formatter(tocata.getData()).equals(data)) {
                    tocata.setId(key);
                    f.setTocata(tocata);
                }
            }

            stringDados = Arrays.toString(formFrequencia.getComboMusicos().getSelectedItem().toString().split("\\("));
            dados = stringDados.split(",");
            String nome = removeCharacters(dados[0]);
            String instrumento = removeCharacters(dados[1]);

            for (Integer key : mapMusicos.keySet()) {
                Musico musico = mapMusicos.get(key);
                if (musico.getNome().equals(nome) && musico.getInstrumento().equals(instrumento)) {
                    musico.setId(key);
                    f.setMusico(musico);
                }
            }

            boolean presenca = formFrequencia.getRadioButtonPresenca().isSelected();
            f.setPresenca(presenca);

            if (!frequencias.contains(f)) {
                formFrequencia.getComboMusicos().removeItemAt(formFrequencia.getComboMusicos().getSelectedIndex());
                frequencias.add(f);
                startTable(frequencias);
                calcValueFaults(frequencias);
            } else {
                ShowMessage.msgWarning("O músico selecionado já está na lista de frequência!");
            }
        } else {
            ShowMessage.msgWarning("Preencha todos os campos obrigatórios");
        }
    }

    public boolean checkInputFields() {
        return !(formFrequencia.getComboTocatas().getSelectedIndex() == 0) && !(formFrequencia.getComboMusicos().getSelectedIndex() == 0) && (formFrequencia.getRadioButtonFalta().isSelected() || formFrequencia.getRadioButtonPresenca().isSelected());
    }
    
    public void calcValueFaults(List<Frequencia> frequencias) {
        int value = 0;
        for(Frequencia f : frequencias) {
            if(!f.isPresenca()) {
                value+= 10;
            }
        }
        formFrequencia.getLabelValueFaults().setText(String.format("R$ %d,00", value));
    }

    public void clearFields() {
        formFrequencia.getComboTocatas().setSelectedIndex(0);
        formFrequencia.getComboMusicos().setSelectedIndex(0);
        formFrequencia.getTableFrequencia().setModel(new FrequenciaTableModel());
    }

    public boolean saveAll() {
        return frequenciaService.insert(frequencias);
    }

    public void startTable() {
        tableModel = new FrequenciaTableModel();
        formFrequencia.getTableFrequencia().setModel(tableModel);
        AlignCell(formFrequencia.getTableFrequencia().getColumnModel().getColumn(0), SwingConstants.CENTER);
        AlignCell(formFrequencia.getTableFrequencia().getColumnModel().getColumn(1), SwingConstants.CENTER);
    }

    public void startTable(List<Frequencia> frequencias) {
        tableModel = new FrequenciaTableModel(frequencias);
        formFrequencia.getTableFrequencia().setModel(tableModel);
        AlignCell(formFrequencia.getTableFrequencia().getColumnModel().getColumn(0), SwingConstants.CENTER);
        AlignCell(formFrequencia.getTableFrequencia().getColumnModel().getColumn(1), SwingConstants.CENTER);
    }

    public void releaseCombo(boolean valor) {
        formFrequencia.getComboTocatas().setEnabled(valor);
        formFrequencia.getButtonReleaseCombo().setEnabled(!valor);
    }

    private String removeCharacters(String var) {
        String aux = var.replaceAll("^\\s+", "");
        aux = aux.replaceAll("\\s+$", "");
        if (var.contains("[")) {
            aux = aux.replaceAll("\\[", "");
        }
        if (var.contains("]")) {
            aux = aux.replaceAll("\\]", "");
        }
        if (var.contains(")")) {
            aux = aux.replaceAll("\\)", "");
        }
        return aux;
    }

}
