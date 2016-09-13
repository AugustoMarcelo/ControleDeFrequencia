package br.com.filarmonica.view.tocatas;

import br.com.filarmonica.models.Tocata;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TocatasTableModel extends AbstractTableModel {

    List<Tocata> tocatas;
    List<String> colunas;
    
    public TocatasTableModel(List<Tocata> tocatas) {
        this.tocatas = tocatas;
        colunas = Arrays.asList("ID", "LOCAL", "DATA", "HORARIO");
    }
    
    public TocatasTableModel(List<Tocata> tocatas, List<String> colunas) {
        this.tocatas = tocatas;
        this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
        return tocatas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tocata tocata = tocatas.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        switch(columnIndex) {
            case 0: return tocata.getId();
            case 1: return tocata.getLocal();
            case 2: return sdf.format(tocata.getData());
            case 3: return tocata.getHorario();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }

    public List<Tocata> getTocatas() {
        return tocatas;
    }
}
