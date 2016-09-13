package br.com.filarmonica.view.frequencia;

import br.com.filarmonica.models.Frequencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class FrequenciaTableModel extends AbstractTableModel {

    private List<Frequencia> frequencias;
    private List<String> colunas;

    public FrequenciaTableModel(List<Frequencia> frequencias) {
        this.frequencias = frequencias;
        colunas = Arrays.asList("NOME", "PRESENÇA");
    }

    public FrequenciaTableModel(List<Frequencia> frequencias, List<String> colunas) {
        this.frequencias = frequencias;
        this.colunas = colunas;
    }
    
    public FrequenciaTableModel() {
        frequencias = new ArrayList<>();
        colunas = Arrays.asList("NOME", "PRESENÇA");
    }

    @Override
    public int getRowCount() {
        return frequencias.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Frequencia f = frequencias.get(rowIndex);
        switch(columnIndex) {
            case 0 : return f.getMusico().getNome();
            case 1 : return f.isPresenca()?"PRESENÇA":"FALTOU";
        }
        return null;
    }

    public List<Frequencia> getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(List<Frequencia> frequencias) {
        this.frequencias = frequencias;
    }

    public List<String> getColunas() {
        return colunas;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }
}
