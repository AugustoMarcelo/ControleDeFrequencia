/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filarmonica.view.financeiro;

import br.com.filarmonica.models.Pagamento;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marcelo Augusto
 */
public class PagamentosTableModel extends AbstractTableModel {
    
    List<Pagamento> pagamentos;
    List<String> colunas;
    
    public PagamentosTableModel(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
        this.colunas = Arrays.asList("DATA", "VALOR");
    }
    
    public PagamentosTableModel() {
        this.colunas = Arrays.asList("DATA", "VALOR");
    }

    @Override
    public int getRowCount() {
        return pagamentos != null ? pagamentos.size() : 0 ;
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }
    
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pagamento pagamento = pagamentos.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        switch (columnIndex) {
            case 0: return sdf.format(pagamento.getData());
            case 1: return "R$ "+pagamento.getValor();
        }
        return null;
    }
}
