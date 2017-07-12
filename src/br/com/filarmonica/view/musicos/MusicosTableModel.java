package br.com.filarmonica.view.musicos;

import br.com.filarmonica.models.Musico;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MusicosTableModel extends AbstractTableModel {

    private List<Musico> musicos;
    private List<String> colunas;
    
    /*--------------------- MÉTODOS CONSTRUTORES ----------------------------*/
    
    public MusicosTableModel(List<Musico> musicos) {
        this.musicos = musicos;
        colunas = Arrays.asList("ID", "NOME", "INSTRUMENTO");
    }
    
    public MusicosTableModel(List<Musico> musicos, List<String> colunas) {
        this.musicos = musicos;
        this.colunas = colunas;
    }
    
    /*---------- MÉTODOS ABSTRATOS DA CLASSE AbstractTableModel -------------*/
    @Override
    public int getRowCount() {
        return musicos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Musico musico = musicos.get(rowIndex);
        switch(columnIndex) {
            case 0: return musico.getId() < 10 ? "0"+musico.getId() : musico.getId();
            case 1: return musico.getNome();
            case 2: return musico.getInstrumento();
        }
        return null;
    }

    public List<Musico> getMusicos() {
        return musicos;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }

    
}
